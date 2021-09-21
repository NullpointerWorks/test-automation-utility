package com.axtron.tau.model.classloader;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class UnmanagedClassLoader extends ClassLoader implements SuperClassLoader
{
    private Map<String, Class<?>> loaded;
	
	public UnmanagedClassLoader()
	{
		super();
        loaded = new HashMap<String, Class<?>>();
	}
	
	public UnmanagedClassLoader(ClassLoader parent)
	{
		super(parent);
        loaded = new HashMap<String, Class<?>>();
	}
	
	/*
	 * called by loadClass to locate a class already loaded
	 */
	@Override
    public final Class<?> findClass(String classname) throws ClassNotFoundException
    {
		if (loaded.containsKey(classname))	
        {
            return loaded.get(classname);
        }
        return null;
    }
	
	/*
	 * called when requesting a class
	 */
	@Override
    public final Class<?> loadClass(String classname) throws ClassNotFoundException
    {
		Class<?> clazz = super.loadClass(classname);
		
		if (clazz == null)
		{
			clazz = findClass(classname);
		}
		
		if (clazz == null)
		{
			throw new ClassNotFoundException(classname);
		}
		
		return clazz;
    }
	
	/**
     * Return a {@code List} of classes that extend the given superclass. This list will be empty if none of the loaded classes extend the superclass.
     * @param superclass - the expected superclass of the loaded classes
     * @return a {@code List} of classes that extend the given superclass
     * @since 1.0.0
     */
	@Override
    public final List<Class<?>> findBySuperClass(Class<?> superclass)
    {
        List<Class<?>> list = new ArrayList<Class<?>>();
        Iterator<Entry<String, Class<?>>> it = loaded.entrySet().iterator();
        while (it.hasNext())
        {
            Entry<String, Class<?>> pair = (Entry<String, Class<?>>) it.next();
            Class<?> c = pair.getValue();
            if (c.getSuperclass() == superclass)
            {
            	list.add(c);
            }
        }
        return list;
    }
    
    /**
     * Opens a local jar file and loads the content of all {@code class} files. 
     * @param path - path to the *.jar file
     * @throws IOException if an I/O error has occurred
     * @since 1.0.0
     */
	@Override
	public final void loadJavaArchive(String path) throws IOException
    {
        if (!path.endsWith(".jar")) return;
        
        URL[] urls 					= { new URL("jar:file:" + path+"!/") };
        URLClassLoader cl 			= new URLClassLoader(urls, this);
        JarFile jf 					= new JarFile(path);
        Enumeration<JarEntry> e 	= jf.entries();
        
        while (e.hasMoreElements()) 
        {
            JarEntry je = e.nextElement();
            if (je.isDirectory()) continue;
            if (!isExtensionAllowed(je.getName())) continue;
            
            // a class path would look like; "path/to/class/MyClassName.class"
            // all classes are stored like;  "path.to.class.MyClassName"
            String fileName 	= je.getName();
            String className 	= fileName.substring(0, fileName.length()-6 );
            className 			= className.replace('/', '.');
            
            // skip module-info and package-info files
            if (className.contains("-info")) continue;
            Class<?> c = null;
            
            try
            {
                c = cl.loadClass(className);
            }
            catch (ClassNotFoundException ex)
            {
                System.err.println("ClassManager: Cannot find the *.class file for \""+className+"\".");
                continue;
            }
            
            resolveClass(c);
            if (!loaded.containsKey(className)) 
            {
            	loaded.put(className, c);
            }
        }
        
        cl.close();
        jf.close();
    }
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	private boolean isExtensionAllowed(String name) 
	{
		if (name.endsWith(".class")) return true;
		return false;
	}

	/**
	 * 
	 * @param jf
	 * @param je
	 * @return
	 * @throws IOException
	 */
	protected final byte[] loadClassZipData(JarFile jf, JarEntry je) throws IOException 
    {
		InputStream stream = jf.getInputStream(je);
        return streamToByteArray(stream);
    }
	
	/**
	 * 
	 * @param name
	 * @return
	 * @throws IOException
	 */
	protected final byte[] loadClassFileData(String name) throws IOException 
    {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(name);
        return streamToByteArray(stream);
    }
	
	/**
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	protected final byte[] streamToByteArray(InputStream stream) throws IOException 
    {
        int size = stream.available();
        byte buff[] = new byte[size];
        DataInputStream in = new DataInputStream(stream);
        in.readFully(buff);
        in.close();
        return buff;
    }
}
