package com.axtron.tau.model.mainboard.firmata.sim;

import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.firmata4j.IODevice;
import org.firmata4j.Pin;
import org.firmata4j.PinEventListener;

/**
 * simulation pin
 */
public class SimulationPin implements Pin
{
	private final IODevice device;
	private final byte index;
	private volatile Mode pinMode;
    private volatile long value;
    private final Set<Mode> supportedModes = Collections.synchronizedSet(EnumSet.noneOf(Mode.class));
    private final Set<PinEventListener> listeners = Collections.synchronizedSet(new HashSet<PinEventListener>());
	
	public SimulationPin(IODevice device, byte index)
	{
		this.device = device;
		this.index = index;
		pinMode = Mode.OUTPUT;
	}
	
	@Override
	public IODevice getDevice()
	{
		return device;
	}
	
	@Override
	public byte getIndex()
	{
		return index;
	}

	@Override
	public Mode getMode()
	{
		return pinMode;
	}

	@Override
	public void setMode(Mode mode) throws IOException, IllegalArgumentException
	{
		pinMode = mode;
	}

	@Override
	public void setServoMode(int minPulse, int maxPulse) throws IOException, IllegalArgumentException
	{
		pinMode = Mode.SERVO;
		value = -1;
	}
	
	@Override
	public boolean supports(Mode mode)
	{
		return supportedModes.contains(mode);
	}

	@Override
	public Set<Mode> getSupportedModes()
	{
		return supportedModes;
	}
	
	@Override
	public long getValue()
	{
		return value;
	}

	@Override
	public void setValue(long value) throws IOException, IllegalStateException
	{
		this.value = value;
	}
	
	@Override
	public void addEventListener(PinEventListener listener)
	{
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	@Override
	public void removeEventListener(PinEventListener listener)
	{
		if (listeners.contains(listener))
			listeners.remove(listener);
	}

	@Override
	public void removeAllEventListeners()
	{
		listeners.clear();
	}
}
