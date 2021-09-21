package com.axtron.tau.model.mainboard.firmata;

import java.io.IOException;

import org.firmata4j.IODevice;
import org.firmata4j.IODeviceEventListener;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;
import org.firmata4j.firmata.FirmataDevice;

import com.nullpointerworks.jd2xx.ID2XX;

import jd2xx.JD2XX;

/**
 * A wrapper around the firmata implementation. This wouldn't normally be necessary, but
 * the {@link FirmataDevice} implementation contains constructors that take a parameter. 
 * 
 * This wrapper acts as a Firmata object that initializes unopened and that I can connect 
 * on command.
 * 
 * @author Michiel
 */
public class StandardFirmata implements IFirmata
{
	private IODevice device;
	
	public StandardFirmata()
	{
		
	}
	
	/**
	 * 
	 */
	public Pin getPin(final int pin)
	{
		return device.getPin(pin);
	}
	
	/**
	 * 
	 * @throws IOException 
	 */
	public void setMode(final int pin, Mode mode) throws IOException
	{
		var p = device.getPin(pin);
		p.setMode(Pin.Mode.OUTPUT);
	}
	
	/**
	 * @throws IOException 
	 */
	public void setPin(final int pin, int state) throws IOException
	{
		Pin p = device.getPin(pin);
		p.setValue(state);
			
		try
		{
			Thread.sleep(10);
		}
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public long getValue(final int pin)
	{
		Pin p = device.getPin(pin);
		return p.getValue();
	}
	
	/**
	 * Connect using JD2XX port location
	 */
	public boolean connect(int location)
	{
		ID2XX jd = new JD2XX();
		String com = "";
		
		try
		{
			jd.openByLocation(location);
			com = "COM"+jd.getComPortNumber();
			jd.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		
		// create new device. if already open, close it
		if (device!=null)
		{
			try
			{
				device.stop();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		device = new FirmataDevice(com);
		
		try 
		{
			device.start();
		}
		catch (IOException e) 
		{
			e.printStackTrace();
			return false;
		} // initiate communication to the device
		
		try 
		{
			device.ensureInitializationIsDone();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
			return false;
		} // wait for initialization is done
		
		return true;
	}
	
	/*
	 * ============================================================
	 * device delegates
	 * ============================================================
	 */
	
	@Override
	public void addEventListener(IODeviceEventListener el)
	{
		device.addEventListener(el);
	}

	@Override
	public void start() throws IOException
	{
		device.start();
	}

	@Override
	public void ensureInitializationIsDone() throws InterruptedException
	{
		device.ensureInitializationIsDone();
	}

	@Override
	public void stop() throws IOException
	{
		device.stop();
	}
}
