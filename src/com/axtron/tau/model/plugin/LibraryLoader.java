package com.axtron.tau.model.plugin;

import java.io.IOException;

import com.axtron.tau.model.classloader.SuperClassLoader;

public final class LibraryLoader
{
	private final SuperClassLoader loader;
	
	public LibraryLoader(SuperClassLoader cl)
	{
		loader = cl;
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
}
