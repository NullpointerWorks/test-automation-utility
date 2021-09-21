package com.axtron.tau.control.customdialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.model.MainboardManager;
import com.axtron.tau.model.mainboard.DevicePort;
import com.axtron.tau.model.mainboard.Mainboard;
import com.axtron.tau.view.CustomConnectionJDialog;
import com.axtron.tau.view.StartInterface;

public class CustomConnectActionCommand implements ActionCommand 
{
	private final StartInterface vStart;
	private final MainboardManager mMainboardManager;
	
	public CustomConnectActionCommand(StartInterface view, MainboardManager mmbm)
	{
		vStart = view;
		mMainboardManager = mmbm;
	}
	
	@Override
	public void onExecute() 
	{
		List<DevicePort> mDeviceList = new ArrayList<DevicePort>();
		Mainboard mMainboard = new Mainboard();
		
		CustomConnectionJDialog vDialog = new CustomConnectionJDialog();
		
		ActionCommand cRefresh = new RefreshCustomDevicesCommand(vDialog, mDeviceList);
		ActionCommand cCancel = new CancelCustomDevicesCommand(vDialog);
		ActionCommand cAccept = new AcceptCustomDevicesCommand(vDialog, vStart, mMainboardManager, mMainboard);
		ActionCommand cSelectFirmate = new SelectFirmataCommand(vDialog, mDeviceList, mMainboard);
		ActionCommand cSelectSerial = new SelectSerialCommand(vDialog, mDeviceList, mMainboard);
		
		vDialog.setRefreshCommand(cRefresh);
		vDialog.setCancelCommand(cCancel);
		vDialog.setAcceptCommand(cAccept);
		vDialog.setFirmataSelectCommand(cSelectFirmate);
		vDialog.setSerialSelectCommand(cSelectSerial);
		vDialog.addWindowListener( new WindowAdapter() 
		{
			public void windowOpened(WindowEvent e) 
			{
				cRefresh.onExecute();
			}
		} );
		
		vDialog.setVisible(true);
	}
}
