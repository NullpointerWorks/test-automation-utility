package com.axtron.tau.model.mainboard;

import com.axtron.tau.model.mainboard.d2xx.D2XXConnection;
import com.axtron.tau.model.mainboard.d2xx.DummyD2XX;
import com.axtron.tau.model.mainboard.firmata.DummyFirmata;
import com.axtron.tau.model.mainboard.firmata.FirmataConnection;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.axtron.tau.model.mainboard.firmata.StandardFirmata;
import com.nullpointerworks.jd2xx.ID2XX;

import jd2xx.JD2XX;

/**
 * 
 */
public class Mainboard implements IMainboard
{
	private String name;
	private FirmataConnection firm;
	private D2XXConnection ftdi;
	private int firmLocation;
	private int ftdiLocation;
	
	/**
	 * 
	 */
	public Mainboard()
	{
		this(-1, -1, "Custom Mainboard");
	}
	
	/**
	 * 
	 */
	public Mainboard(int firmLOC, int ftdiLOC, String name)
	{
		this( new DevicePort(firmLOC, "firm"), new DevicePort(ftdiLOC, "ftdi"), name);
	}
	
	/**
	 * 
	 */
	public Mainboard(DevicePort firm, DevicePort ftdi, String name)
	{
		this.name = name;
		setMainboardFirmata(firm);
		setMainboardSerial(ftdi);
	}
	
	void setName(String n)
	{
		name = n;
	}
	
	void setMainboardFirmata(DevicePort dp)
	{
		firmLocation = dp.getPortLocation();
		if (firmLocation >= 0)
		{
			firm = new FirmataConnection( new StandardFirmata() );
		}
		else
		{
			firm = new FirmataConnection( new DummyFirmata() );
		}
	}
	
	void setMainboardSerial(DevicePort dp)
	{
		ftdiLocation = dp.getPortLocation();
		if (ftdiLocation >= 0)
		{
			ftdi = new D2XXConnection( new JD2XX() );
		}
		else
		{
			ftdi = new D2XXConnection( new DummyD2XX() );
		}
	}
	
	/**
	 * 
	 */
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public String toString()
	{
		return getName();
	}
	
	/**
	 * 
	 */
	@Override
	public IFirmata getFirmata()
	{
		return firm.getDevice();
	}
	
	/**
	 * 
	 */
	@Override
	public ID2XX getFTDI()
	{
		return ftdi.getDevice();
	}
	
	/**
	 * 
	 */
	@Override
	public boolean testConnection()
	{
		boolean hasFirm = !(firmLocation < 0);
		boolean hasFTDI = !(ftdiLocation < 0);
		
		if (hasFirm)
		{
			var ftdi = new D2XXConnection( new JD2XX() );
			if (ftdi.connect(firmLocation))
				ftdi.disconnect();
			else 
				return false;
		}
		
		if (hasFTDI)
		{
			var ftdi = new D2XXConnection( new JD2XX() );
			if (ftdi.connect(firmLocation))
				ftdi.disconnect();
			else 
				return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean isConnected()
	{
		boolean hasFirm = !(firmLocation < 0);
		boolean hasFTDI = !(ftdiLocation < 0);
		
		if (hasFirm && !hasFTDI)
		{
			return firm.isConnected();
		}
		
		if (!hasFirm && hasFTDI)
		{
			return ftdi.isConnected();
		}
		
		if (hasFirm && hasFTDI)
		{
			hasFirm = firm.isConnected();
			hasFTDI = ftdi.isConnected();
			if (hasFirm && hasFTDI)
			{
				return true;
			}
			else
			{
				disconnect();
				return false;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 */
	public boolean connect()
	{
		boolean hasFirm = !(firmLocation < 0);
		boolean hasFTDI = !(ftdiLocation < 0);
		
		if (hasFirm && !hasFTDI)
		{
			if (!firm.connect(firmLocation)) 
				return false;
		}
		
		if (!hasFirm && hasFTDI)
		{
			if (!ftdi.connect(ftdiLocation)) 
				return false;
		}
		
		if (hasFirm && hasFTDI)
		{
			if (!firm.connect(firmLocation)) 
				return false;
			
			if (!ftdi.connect(ftdiLocation)) 
			{
				firm.disconnect();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 
	 */
	@Override
	public boolean disconnect()
	{
		boolean isOK = true;
		if (firm.isConnected())
		{
			isOK = isOK && firm.disconnect();
		}
		
		if (ftdi.isConnected())
		{
			isOK = isOK && ftdi.disconnect();
		}
		
		return isOK;
	}
}
