package com.axtron.tau.model.mainboard.firmata;

import java.io.IOException;

import org.firmata4j.IODeviceEventListener;
import org.firmata4j.Pin;
import org.firmata4j.Pin.Mode;

public interface IFirmata
{
	boolean connect(int location) throws IOException;
	
	void addEventListener(IODeviceEventListener firmataConnection);
	
	void start() throws IOException;
	
	void ensureInitializationIsDone() throws InterruptedException;
	
	void stop() throws IOException;
	
	Pin getPin(int pin);
	
	void setMode(final int pin, Mode mode) throws IOException;
	
	void setPin(final int pin, int state) throws IOException;
	
	long getValue(final int pin);

	
}
