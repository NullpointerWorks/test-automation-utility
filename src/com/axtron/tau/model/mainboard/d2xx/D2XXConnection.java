package com.axtron.tau.model.mainboard.d2xx;

import java.io.IOException;

import com.nullpointerworks.jd2xx.ID2XX;

/**
 * adapter class for D2XX connections
 */
public final class D2XXConnection
{
	private boolean isConnected;
	private ID2XX jd;
	
	public D2XXConnection(ID2XX device)
	{
		isConnected = false;
		jd = device;
	}
	
	public ID2XX getDevice()
	{
		return jd;
	}
	
	public boolean connect(int loc)
	{
		try
		{
			jd.openByLocation(loc);
			isConnected = true;
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			isConnected = false;
		}
		return isConnected;
	}
	
	public boolean disconnect()
	{
		if (isConnected)
		{
			try
			{
				jd.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		isConnected = false;
		return true;
	}
	
	/**
	 * @return true if a device is connected
	 */
	public boolean isConnected()
	{
		return isConnected;
	}
}
