package com.axtron.tau.model.mainboard.firmata.sim;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.firmata4j.IODeviceEventListener;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;

import com.axtron.tau.model.mainboard.firmata.IFirmata;

public class SimulationFirmata implements IFirmata
{
	private boolean isStarted;
	private Map<Integer, Pin> pins;
	private List<IODeviceEventListener> listeners;
	
	public SimulationFirmata()
	{
		pins = new HashMap<Integer, Pin>();
		listeners = new ArrayList<IODeviceEventListener>();
		isStarted = false;
	}
	
	@Override
	public boolean connect(int location) throws IOException
	{
		return true;
	}

	@Override
	public void addEventListener(IODeviceEventListener firmataConnection)
	{
		listeners.add(firmataConnection);
	}

	@Override
	public void start() throws IOException
	{
		isStarted = true;
	}

	@Override
	public void ensureInitializationIsDone() throws InterruptedException
	{
		
	}

	@Override
	public void stop() throws IOException
	{
		isStarted = false;
	}

	@Override
	public Pin getPin(int pin)
	{
		if (!isStarted) return null;
		if (pins.containsKey(pin))
		{
			return pins.get(pin);
		}
		
		Pin simpin = new SimulationPin(null, (byte)(pin&0xff));
		pins.put(pin, simpin);
		return simpin;
	}
	
	@Override
	public void setMode(int pin, Mode mode) throws IOException
	{
		if (!isStarted) throw new IOException();
		Pin simpin = new SimulationPin(null, (byte)(pin&0xff));
		pins.put(pin, simpin);
	}

	@Override
	public void setPin(int pin, int state) throws IOException
	{
		if (!isStarted) throw new IOException();
		if (pins.containsKey(pin))
		{
			Pin simpin = pins.get(pin);
			simpin.setValue(state);
		}
	}
	
	@Override
	public long getValue(int pin)
	{
		if (!isStarted) return 0;
		if (pins.containsKey(pin))
		{
			Pin simpin = pins.get(pin);
			return simpin.getValue();
		}
		return -1;
	}
}
