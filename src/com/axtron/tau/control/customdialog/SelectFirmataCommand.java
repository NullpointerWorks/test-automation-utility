package com.axtron.tau.control.customdialog;

import java.util.List;

import javax.swing.JOptionPane;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.mainboard.DevicePort;
import com.axtron.tau.model.mainboard.Mainboard;
import com.axtron.tau.model.mainboard.MainboardCustomizer;
import com.axtron.tau.view.CustomConnectionJDialog;

public class SelectFirmataCommand implements ActionCommand 
{
	private final CustomConnectionJDialog vDialog;
	private final List<DevicePort> mDeviceList;
	private final Mainboard mMainboard;
	
	public SelectFirmataCommand(CustomConnectionJDialog dialog, List<DevicePort> dl, Mainboard mb)
	{
		vDialog = dialog;
		mDeviceList = dl;
		mMainboard = mb;
	}
	
	@Override
	public void onExecute() 
	{
		String firmata = vDialog.getFirmataSelection();
		if (firmata==null) return;
		if (firmata.startsWith("<")) return;
		
		DevicePort dp = null;
		for (DevicePort port : mDeviceList)
		{
			if (port.getDescription().equalsIgnoreCase(firmata))
			{
				dp = port;
				break;
			}
		}
		
		if (dp == null)
		{
			JOptionPane.showMessageDialog(null, "Port is no longer available.", "Firmata COM Port", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		MainboardCustomizer mbcustom = new MainboardCustomizer(mMainboard);
		mbcustom.setFirmataDevice(dp);
	}
}
