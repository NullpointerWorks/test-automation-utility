package com.axtron.tau.control;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.MainboardManager;
import com.axtron.tau.model.TestManager;
import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.view.StartInterface;

public class MBSelectActionCommand implements ActionCommand 
{
	private final StartInterface vStart;
	private final MainboardManager mMainboardManager;
	private final TestManager mTtestmanager;
	
	public MBSelectActionCommand(StartInterface view, MainboardManager mmanager, TestManager testmanager) 
	{
		vStart = view;
		mMainboardManager = mmanager;
		mTtestmanager = testmanager;
	}
	
	@Override
	public void onExecute() 
	{
		String selected = vStart.getMainboardSelection();
		IMainboard mb = mMainboardManager.findMainboardByName(selected);
		
		if (mb == null)
		{
			vStart.setConnectButton(false);
			return;
		}
		
		if (mb.isConnected())
		{
			vStart.setConnectButton(true);
		}
		else
		{
			vStart.setConnectButton(false);
			mTtestmanager.selectMainboard(mb);
		}
	}
}
