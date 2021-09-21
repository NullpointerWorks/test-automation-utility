package com.axtron.tau.util;

public class Version 
{
	private final int major;
	private final int minor;
	private final int debug;
	private final int build;
	private final String message;
	
	public Version(int m, int n, int d, int b, String t)
	{
		major = m;
		minor = n;
		debug = d;
		build = b;
		message = t;
	}
	
	public Version(int m, int n, int d, String t)
	{
		this(m,n,d,0,t);
	}
	
	public Version(int m, int n, int d)
	{
		this(m,n,d,0,"");
	}
	
	public String getStringShort()
	{
		return major+"."+minor;
	}
	
	public String getString()
	{
		return major+"."+minor+"."+debug;
	}
	
	public String getStringExtended()
	{
		return major+"."+minor+"."+debug+"."+build;
	}
	
	public String getStringFull()
	{
		if (message.length()==0) return getString();
		return major+"."+minor+"."+debug+" "+message;
	}
}
