package com.axtron.tau.model.mainboard.firmata;

import java.io.IOException;

/**
 * adapter class for Firmata connections
 */
public final class FirmataConnection
{
	private boolean isConnected;
	private IFirmata device;
	
	public FirmataConnection(IFirmata device)
	{
		isConnected = false;
		this.device = device;
	}
	
	/**
	 * 
	 */
	public IFirmata getDevice()
	{
		return device;
	}
	
	// ==================================================================
	
	/**
	 * Connect using JD2XX port location
	 */
	public boolean connect(int location)
	{
		isConnected = false;
		try
		{
			isConnected = device.connect(location);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return isConnected;
	}
	
	/**
	 * firmata disconnect
	 */
	public boolean disconnect()
	{
		// stop communication to the device
		try 
		{
			device.stop();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
		isConnected = false;
		return true;
	}
	
	/**
	 * @return true if a device is connected
	 */
	public boolean isConnected()
	{
		//return device.isReady();
		return isConnected;
	}
}
