package com.axtron.tau.control.customdialog;

import java.io.IOException;
import java.util.List;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.mainboard.DevicePort;
import com.axtron.tau.view.CustomConnectionJDialog;

import jd2xx.JD2XX;

public class RefreshCustomDevicesCommand implements ActionCommand 
{
	private final CustomConnectionJDialog vDialog;
	private final List<DevicePort> mDeviceList;
	
	public RefreshCustomDevicesCommand(CustomConnectionJDialog dialog, List<DevicePort> deviceList)
	{
		vDialog = dialog;
		mDeviceList = deviceList;
	}
	
	@Override
	public void onExecute() 
	{
		mDeviceList.clear();
		vDialog.clearFirmataNames();
		vDialog.clearSerialNames();
		
		vDialog.addFirmataName( "<No Firmata Device>" );
		vDialog.addSerialName( "<No Serial Device>" );
		
		// open D2XX and find all FTDI devices
		JD2XX jd = new JD2XX();
		Object[] locs = null;
		Object[] desc = null;
		try
		{
			locs = jd.listDevicesByLocation();
			desc = jd.listDevicesByDescription();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		if (locs==null) return;
		if (desc==null) return;
		
		// look for FTDI devices by name
		for (int i=0; i<desc.length; i++)
		{
			String dev = (String)desc[i];
			Integer loc = (Integer)locs[i];
			dev = dev.toUpperCase();
			
			int comport = -1;
			try
			{
				comport = getComPort(loc);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
				continue;
			}
			
			dev = dev + " (COM"+comport+")";
			vDialog.addFirmataName( dev );
			vDialog.addSerialName( dev );
			mDeviceList.add( new DevicePort(loc, dev) );
		}
	}
	
	private int getComPort(int loc) throws IOException
	{
		JD2XX d2xx = new JD2XX();
		d2xx.openByLocation(loc);
		int com = d2xx.getComPortNumber();
		d2xx.close();
		return com;
	}
}
