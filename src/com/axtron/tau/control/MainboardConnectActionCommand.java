package com.axtron.tau.control;

import javax.swing.JOptionPane;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.control.interfaces.Command;
import com.axtron.tau.model.TestManager;
import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.model.plugin.TestPlugin;
import com.axtron.tau.view.InfiniteLoadView;
import com.axtron.tau.view.StartInterface;

public class MainboardConnectActionCommand implements ActionCommand 
{
	private InfiniteLoadView load;
	private final StartInterface vStart;
	private final TestManager mTtestmanager;
	private final Command cStartTest;
	
	public MainboardConnectActionCommand(StartInterface view, TestManager testmanager, Command startTest) 
	{
		vStart = view;
		mTtestmanager = testmanager;
		cStartTest = startTest;
	}
	
	@Override
	public void onExecute() 
	{
		/*
		 * do error check
		 */
		TestPlugin pl = mTtestmanager.getSelectedTestPlugin();
		if (pl==null)
		{
			JOptionPane.showMessageDialog(null, "Select a testing procedure.", "Select Procedure", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		IMainboard mb = mTtestmanager.getSelectedMainboard();
		if (mb==null)
		{
			JOptionPane.showMessageDialog(null, "Select a mainboard to connect with.", "Select Mainboard", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		/*
		 * connect if not already connected
		 */
		if (!mb.isConnected())
		{
			load = new InfiniteLoadView("Connecting");
			load.setMessage("Connecting to mainboard...");
			Thread t = new Thread(()->
			{
				mb.connect();
				sleep(200);
				load.setVisible(false);
			});
			t.start();
			load.setVisible(true);
			
			if (!mb.isConnected())
			{
				JOptionPane.showMessageDialog(null, "Failed to connect to the selected mainboard.", "Unable To Connect", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			if (!mb.disconnect())
			{
				JOptionPane.showMessageDialog(null, "Failed to properly disconnect from the mainboard.", "Disconnect Failure", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		/*
		 * after check
		 */
		if (mb.isConnected())
		{
			vStart.setConnectButton(true);
			cStartTest.onExecute();
			vStart.refreshTestPluginGroup();
		}
		else
		{
			vStart.setConnectButton(false);
		}
		vStart.refreshInterface();
	}
	
	private void sleep(int i) 
	{
		try {Thread.sleep(i);} 
		catch (InterruptedException e) 
		{e.printStackTrace();}
	}
}
