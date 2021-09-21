package com.axtron.tau.model.plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.axtron.tau.model.classloader.SuperClassLoader;

public final class PluginLoader
{
	private final SuperClassLoader loader;
	private List<TestPlugin> plugins;
	
	public PluginLoader(SuperClassLoader cl)
	{
		loader = cl;
		plugins = new ArrayList<TestPlugin>();
	}
	
	/**
	 * 
	 * @param path
	 */
	public synchronized void loadJar(String path)
	{
		try 
		{
			loader.loadJavaArchive(path);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public synchronized void findPlugins() 
	{
		plugins.clear();
		
		// find plugin classes
		List<Class<?>> classes = loader.findBySuperClass(TestPlugin.class);
		TestPlugin o = null;
		for (Class<?> clazz : classes)
		{
			@SuppressWarnings("unchecked")
			Class<? extends TestPlugin> pl = (Class<? extends TestPlugin>) clazz;
			
			try
			{
				o = pl.getDeclaredConstructor().newInstance(); // Java 9+ reflection
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				continue;
			}
			
			if (o == null) continue;
			
			addPlugin(o);
		}
	}
	
	/**
	 * 
	 */
	public synchronized void addPlugin(TestPlugin pl)
	{
		if (!plugins.contains(pl)) 
		{
			plugins.add(pl);
		}
	}
	
	/**
	 * Returns all available plug-ins.
	 */
	public synchronized List<TestPlugin> getPlugins()
	{
		return plugins;
	}
}
