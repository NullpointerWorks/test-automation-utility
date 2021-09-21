package com.axtron.tau.control.customdialog;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.view.CustomConnectionJDialog;

public class CancelCustomDevicesCommand implements ActionCommand 
{
	private final CustomConnectionJDialog vDialog;
	public CancelCustomDevicesCommand(CustomConnectionJDialog dialog)
	{
		vDialog = dialog;
	}
	
	@Override
	public void onExecute() 
	{
		vDialog.setVisible(false);
		vDialog.dispose();
	}
}
