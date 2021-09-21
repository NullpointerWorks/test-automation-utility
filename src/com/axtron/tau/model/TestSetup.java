package com.axtron.tau.model;

import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.model.plugin.TestPlugin;

public class TestSetup 
{
	private final IMainboard mainboard;
	private final TestPlugin plugin;
	
	public TestSetup(IMainboard mb, TestPlugin pl)
	{
		mainboard = mb;
		plugin = pl;
	}
	
	public IMainboard getMainboard() {return mainboard;}
	public TestPlugin getTestPlugin() {return plugin;}
	
	public boolean isValid()
	{
		if (mainboard==null) return false;
		if (plugin==null) return false;
		return true;
	}
}
