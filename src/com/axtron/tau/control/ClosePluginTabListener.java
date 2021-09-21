package com.axtron.tau.control;

import javax.swing.JOptionPane;

import com.axtron.tau.model.TestManager;
import com.axtron.tau.model.TestSetup;
import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.view.swing.ClosableTabHeader;
import com.axtron.tau.view.swing.EditorListener;

public class ClosePluginTabListener implements EditorListener 
{
	private final TestManager mTestmanager;
	private final TestSetup mTestSetup;
	
	public ClosePluginTabListener(TestManager testmanager, TestSetup ref)
	{
		mTestmanager = testmanager;
		mTestSetup = ref;
	}
	
	@Override
	public void onTabHeaderLink(ClosableTabHeader t) {}

	@Override
	public void onTabOpening() {}
	
	@Override
	public void onTabClosing() 
	{
		IMainboard mb 	= mTestSetup.getMainboard();
		if (mb.disconnect())
		{
			mTestmanager.removeSetup(mTestSetup);
		}
		else
		{
			JOptionPane.showMessageDialog(null, 
				"Failed to properly disconnect from the mainboard. You may need to restart the application.", 
				"Disconnection Fail", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void onModification() {}
	
	@Override
	public void onSaveAction() {}
}
