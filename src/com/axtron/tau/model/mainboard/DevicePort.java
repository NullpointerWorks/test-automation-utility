package com.axtron.tau.model.mainboard;

public class DevicePort
{
	private final Integer loc;
	private final String desc;

	public DevicePort(Integer l, String d)
	{
		loc=l;
		desc=d;
	}
	
	public Integer getPortLocation()
	{
		return loc;
	}
	
	public String getDescription()
	{
		return desc;
	}
	
	// not the recommended purpose to override toString()
	@Override
	public String toString()
	{
		return getDescription();
	}
}
