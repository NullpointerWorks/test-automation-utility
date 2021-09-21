package com.axtron.tau.model.classloader;

import java.io.IOException;
import java.util.List;

public interface SuperClassLoader 
{
	public void loadJavaArchive(String jarPath) throws IOException;
	public List<Class<?>> findBySuperClass(Class<?> clazz);
	public Class<?> findClass(final String name) throws ClassNotFoundException;
	public Class<?> loadClass(final String name) throws ClassNotFoundException;
}
