package com.axtron.tau.control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import com.axtron.tau.Resources;
import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.control.interfaces.Command;
import com.axtron.tau.model.TestManager;
import com.axtron.tau.model.config.Configuration;
import com.axtron.tau.model.plugin.LibraryLoader;
import com.axtron.tau.model.plugin.PluginLoader;
import com.axtron.tau.model.plugin.TestPlugin;
import com.axtron.tau.view.InfiniteLoadView;
import com.axtron.tau.view.StartInterface;

public class LoadPluginsCommand implements Command 
{
	private InfiniteLoadView vInitLoad;
	private final Configuration mConfiguration;
	private final LibraryLoader mLibraryLoader;
	private final PluginLoader mPluginLoader;
	private final TestManager mTestManager;
	private final StartInterface vStart;
	
	public LoadPluginsCommand(Configuration c, PluginLoader pl, LibraryLoader ll, TestManager tm, StartInterface v)
	{
		mConfiguration = c;
		mPluginLoader = pl;
		mLibraryLoader = ll;
		mTestManager = tm;
		vStart = v;
	}
	
	@Override
	public void onExecute() 
	{
		vInitLoad = new InfiniteLoadView("Loading");
		vInitLoad.setMessage("Loading Plug-ins");
		Thread t = new Thread( ()->{execute();} );
		t.start();
		vInitLoad.setVisible(true);
	}
	
	private void execute()
	{
		String libsPath = mConfiguration.getLibraryFolder();
		File libsDir = new File(libsPath);
		if (!libsDir.exists()) libsDir.mkdirs();
		loadLibrary(libsDir);
		
		String plugPath = mConfiguration.getPluginFolder();
		File plugDir = new File(plugPath);
		if (!plugDir.exists()) plugDir.mkdirs();
		loadPlugin(plugDir);
		
		List<TestPlugin> plugins = mPluginLoader.getPlugins();
		for (TestPlugin pl : plugins)
		{
			ActionCommand cPluginSelect = new PluginSelectActionCommand(mTestManager, vStart, pl);
			BufferedImage image = Resources.getImages().getRelation(pl.getRelation());
			vStart.addTestPluginTile(image, pl.getName(), cPluginSelect);
		}
		
		sleep(200); // gives time to show the pop-up, but also makes the pop-up less intrusive
		vInitLoad.setVisible(false);
	}
	
	private void loadLibrary(File dir)
	{
		File[] files = dir.listFiles();
		for (File f : files)
		{
			if (f.isDirectory())
			{
				loadLibrary(f);
			}
			else
			{
				String path = f.getAbsolutePath();
				if (path.endsWith(".jar"))
				{
					mLibraryLoader.loadJar(path);
				}
			}
		}
	}
	
	private void loadPlugin(File dir)
	{
		File[] files = dir.listFiles();
		for (File f : files)
		{
			if (f.isDirectory())
			{
				loadPlugin(f);
			}
			else
			{
				String path = f.getAbsolutePath();
				if (path.endsWith(".jar"))
				{
					mPluginLoader.loadJar(path);
				}
			}
		}
		mPluginLoader.findPlugins();
	}
	
	private void sleep(int i) 
	{
		try {Thread.sleep(i);} 
		catch (InterruptedException e) 
		{e.printStackTrace();}
	}
}
