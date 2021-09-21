package com.axtron.tau.control;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.TestManager;
import com.axtron.tau.model.plugin.TestPlugin;
import com.axtron.tau.view.StartInterface;

public class PluginSelectActionCommand implements ActionCommand 
{
	private TestManager tm;
	private StartInterface start;
	private TestPlugin plugin;
	
	public PluginSelectActionCommand(TestManager testmanager, 
									 StartInterface mstart,
									 TestPlugin pl) 
	{
		tm = testmanager;
		start = mstart;
		plugin = pl;
	}
	
	@Override
	public void onExecute() 
	{
		start.setDescriptionText( plugin.getDescription() );
		tm.selectPlugin(plugin);
	}
}
