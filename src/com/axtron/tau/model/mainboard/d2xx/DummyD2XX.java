package com.axtron.tau.model.mainboard.d2xx;

import java.io.IOException;
import java.util.TooManyListenersException;

import com.nullpointerworks.jd2xx.ID2XX;

import jd2xx.JD2XX.DeviceInfo;
import jd2xx.JD2XX.ProgramData;
import jd2xx.JD2XXEventListener;

public class DummyD2XX implements ID2XX
{

	@Override
	public int getLibraryVersion()
	{
		return -1;
	}

	@Override
	public int createDeviceInfoList()
	{
		return -1;
	}

	@Override
	public DeviceInfo getDeviceInfoDetail(int dn) throws IOException
	{
		return null;
	}

	@Override
	public void open(int deviceNumber) throws IOException
	{
		
	}

	@Override
	public void close() throws IOException
	{
		
	}

	@Override
	public Object[] listDevices(int flags) throws IOException
	{
		return new Object[] {};
	}

	@Override
	public void openEx(String name, int flags) throws IOException
	{
		
	}

	@Override
	public void openEx(int location, int flags) throws IOException
	{
		
	}

	@Override
	public int read(byte[] bytes, int offset, int length) throws IOException
	{
		return -1;
	}

	@Override
	public int write(byte[] bytes, int offset, int length) throws IOException
	{
		return -1;
	}

	@Override
	public void setBaudRate(int baudRate) throws IOException
	{
		
	}

	@Override
	public void setDivisor(int divisor) throws IOException
	{
		
	}

	@Override
	public void setDataCharacteristics(int wordLength, int stopBits, int parity) throws IOException
	{
		
	}

	@Override
	public void setFlowControl(int flowControl, int xonChar, int xoffChar) throws IOException
	{
		
	}

	@Override
	public void resetDevice() throws IOException
	{
		
	}

	@Override
	public void setDtr() throws IOException
	{
		
	}

	@Override
	public void clrDtr() throws IOException
	{
		
	}

	@Override
	public void setRts() throws IOException
	{
		
	}

	@Override
	public void clrRts() throws IOException
	{
		
	}

	@Override
	public int getModemStatus() throws IOException
	{
		return -1;
	}

	@Override
	public void setChars(int eventChar, boolean eventCharEn, int errorChar, boolean errorCharEn) throws IOException
	{
		
	}

	@Override
	public void purge(int mask) throws IOException
	{
		
	}

	@Override
	public void setTimeouts(int readTimeout, int writeTimeout) throws IOException
	{
		
	}

	@Override
	public int getQueueStatus() throws IOException
	{
		return -1;
	}

	@Override
	public void setBreakOn() throws IOException
	{
		
	}

	@Override
	public void setBreakOff() throws IOException
	{
		
	}

	@Override
	public int[] getStatus() throws IOException
	{
		return new int[] {-1};
	}

	@Override
	public void setEventNotification(int mask, int handle) throws IOException
	{
		
	}

	@Override
	public void setWaitMask(int mask) throws IOException
	{
		
	}

	@Override
	public int waitOnMask() throws IOException
	{
		return -1;
	}

	@Override
	public int getEventStatus() throws IOException
	{
		return -1;
	}

	@Override
	public short readEE(int wordOffset) throws IOException
	{
		return -1;
	}

	@Override
	public void writeEE(int wordOffset, short value) throws IOException
	{
		
	}

	@Override
	public void eraseEE() throws IOException
	{
		
	}

	@Override
	public void eeProgram(ProgramData data) throws IOException
	{
		
	}

	@Override
	public void eeProgramEx(ProgramData data, 
			String manufacturer, 
			String manufacturerId, 
			String description,
			String serialNumber) 
					throws IOException
	{
		
	}

	@Override
	public ProgramData eeRead() throws IOException
	{
		return null;
	}

	@Override
	public ProgramData eeReadEx(String manufacturer, 
			String manufacturerId, 
			String description, 
			String serialNumber)
					throws IOException
	{
		return null;
	}

	@Override
	public int eeUASize() throws IOException
	{
		return 0;
	}

	@Override
	public void eeUAWrite(byte[] uaData) throws IOException
	{
		
	}

	@Override
	public byte[] eeUARead(int numBytes) throws IOException
	{
		return new byte[] {};
	}

	@Override
	public void setLatencyTimer(int time) throws IOException
	{
		
	}

	@Override
	public int getLatencyTimer() throws IOException
	{
		return -1;
	}

	@Override
	public void setBitMode(int mask, int mode) throws IOException
	{
		
	}

	@Override
	public int getBitMode() throws IOException
	{
		return -1;
	}

	@Override
	public void setUSBParameters(int inputSize, int outputSize) throws IOException
	{
		
	}

	@Override
	public void setDeadmanTimeout(int timeout) throws IOException
	{
		
	}

	@Override
	public DeviceInfo getDeviceInfo() throws IOException
	{
		return null;
	}

	@Override
	public void stopInTask() throws IOException
	{
		
	}

	@Override
	public void restartInTask() throws IOException
	{
		
	}

	@Override
	public void setResetPipeRetryCount(int count) throws IOException
	{
		
	}

	@Override
	public void resetPort() throws IOException
	{
		
	}

	@Override
	public void cyclePort() throws IOException
	{
		
	}

	@Override
	public int getDriverVersion()
	{
		return -1;
	}

	@Override
	public void rescan()
	{
		
	}

	@Override
	public void reload(int vid, int pid)
	{
		
	}

	@Override
	public int getComPortNumber() throws IOException
	{
		return -1;
	}

	@Override
	public int eeReadConfig(int address) throws IOException
	{
		return -1;
	}

	@Override
	public void eeWriteConfig(int address, int value) throws IOException
	{
		
	}

	@Override
	public int eeReadEcc(int option) throws IOException
	{
		return -1;
	}

	@Override
	public int getQueueStatusEx() throws IOException
	{
		return -1;
	}

	@Override
	public void registerEvent(int m) throws IOException
	{
		
	}

	@Override
	public void signalEvent()
	{
		
	}

	@Override
	public int waitEvent()
	{
		return -1;
	}

	@Override
	public void openBySerialNumber(String name) throws IOException
	{
		
	}

	@Override
	public void openByDescription(String name) throws IOException
	{
		
	}

	@Override
	public void openByLocation(int location) throws IOException
	{
		
	}

	@Override
	public Object[] listDevicesBySerialNumber() throws IOException
	{
		return new Object[] {};
	}

	@Override
	public Object[] listDevicesByDescription() throws IOException
	{
		return new Object[] {};
	}

	@Override
	public Object[] listDevicesByLocation() throws IOException
	{
		return new Object[] {};
	}

	@Override
	public byte[] read(int s) throws IOException
	{
		return new byte[] {};
	}

	@Override
	public int read(byte[] b) throws IOException
	{
		return -1;
	}

	@Override
	public int read() throws IOException
	{
		return -1;
	}

	@Override
	public int write(int b) throws IOException
	{
		return -1;
	}

	@Override
	public int write(byte[] b) throws IOException
	{
		return -1;
	}

	@Override
	public void addEventListener(JD2XXEventListener el) throws TooManyListenersException
	{
		
	}

	@Override
	public void removeEventListener()
	{
		
	}

	@Override
	public void dispatchEvent(int et)
	{
		
	}

	@Override
	public void notifyOnEvent(int m, boolean v) throws IOException
	{
		
	}

	@Override
	public void notifyOnRxchar(boolean v) throws IOException
	{
		
	}

	@Override
	public void notifyOnModemStatus(boolean v) throws IOException
	{
		
	}
}
