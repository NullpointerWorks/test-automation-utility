package com.axtron.tau.control.customdialog;

import javax.swing.JOptionPane;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.MainboardManager;
import com.axtron.tau.model.mainboard.Mainboard;
import com.axtron.tau.model.mainboard.MainboardCustomizer;
import com.axtron.tau.view.CustomConnectionJDialog;
import com.axtron.tau.view.StartInterface;

public class AcceptCustomDevicesCommand implements ActionCommand 
{
	private final StartInterface vStart;
	private final CustomConnectionJDialog vDialog;
	private final MainboardManager mMainboardManager;
	private final Mainboard mainboard;
	
	public AcceptCustomDevicesCommand(	CustomConnectionJDialog dialog, 
										StartInterface start, 
										MainboardManager mbm,
										Mainboard mb)
	{
		vDialog = dialog;
		vStart = start;
		mMainboardManager = mbm;
		mainboard = mb;
	}
	
	@Override
	public void onExecute() 
	{
		String name = vDialog.getNameFieldText();
		if (name.equalsIgnoreCase(""))
		{
			vDialog.setFieldError(true);
			JOptionPane.showMessageDialog(null, "Please enter a name for this configuration.", "Configuration Name", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		MainboardCustomizer mbcustom = new MainboardCustomizer(mainboard);
		mbcustom.setName(name);
		
		mMainboardManager.addMainboard(mainboard);
		vStart.addMainboardName( mainboard.getName(), true );
		
		vDialog.setVisible(false);
		vDialog.dispose();
	}
}
