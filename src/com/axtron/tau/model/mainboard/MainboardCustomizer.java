package com.axtron.tau.model.mainboard;

public class MainboardCustomizer
{
	private final Mainboard mb;
	
	public MainboardCustomizer(Mainboard custom)
	{
		mb = custom;
	}

	public void setName(String name) 
	{
		mb.setName(name);
	}

	public void setFirmataDevice(DevicePort dp) 
	{
		mb.setMainboardFirmata(dp);
	}

	public void setSerialDevice(DevicePort dp)
	{
		mb.setMainboardSerial(dp);
	}
}
