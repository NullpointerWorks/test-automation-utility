package com.axtron.tau.control;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.MainboardManager;
import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.view.StartInterface;
import com.nullpointerworks.util.pattern.Iterator;

public class RefreshMBActionCommand implements ActionCommand
{
	private final StartInterface vStart;
	private final MainboardManager mMainboardManager;
	
	public RefreshMBActionCommand(StartInterface view, MainboardManager mmanager) 
	{
		vStart = view;
		mMainboardManager = mmanager;
	}

	@Override
	public void onExecute() 
	{
		vStart.clearMainboardNames();
		vStart.addMainboardName( "<Select Mainboard>" );
		
		mMainboardManager.refresh();
		Iterator<IMainboard> it = mMainboardManager.getMainboards();
		
		while (it.hasNext())
		{
			IMainboard mb = it.getNext();
			vStart.addMainboardName( mb.getName() );
		}
	}
}
