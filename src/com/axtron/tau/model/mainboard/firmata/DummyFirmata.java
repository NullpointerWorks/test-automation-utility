package com.axtron.tau.model.mainboard.firmata;

import java.io.IOException;

import org.firmata4j.IODeviceEventListener;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;

public class DummyFirmata implements IFirmata
{
	public DummyFirmata()
	{
		
	}
	
	@Override
	public boolean connect(int location) throws IOException
	{
		return false;
	}

	@Override
	public void addEventListener(IODeviceEventListener firmataConnection)
	{
		
	}

	@Override
	public void start() throws IOException
	{
		
	}

	@Override
	public void ensureInitializationIsDone() throws InterruptedException
	{
		
	}

	@Override
	public void stop() throws IOException
	{
		
	}

	@Override
	public Pin getPin(int pin)
	{
		return null;
	}

	@Override
	public void setMode(int pin, Mode mode) throws IOException
	{
		
	}

	@Override
	public void setPin(int pin, int state) throws IOException
	{
		
	}

	@Override
	public long getValue(int pin)
	{
		return 0;
	}
}
