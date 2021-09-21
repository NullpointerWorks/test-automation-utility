package com.axtron.tau.model.log;

public class ConsoleLogger implements ILogger
{
	@Override
	public String print(String msg) {return prt(msg);}
	
	@Override
	public String println(String msg) {return prtln(msg);}
	
	@Override
	public String print(LogResult lr, String msg) {return prt(lr.toString() +" "+ msg);}
	
	@Override
	public String println(LogResult lr, String msg) {return prtln(lr.toString() +" "+ msg);}
	
	private String prt(String msg)
	{
		System.out.print(msg);
		return msg;
	}
	
	private String prtln(String msg)
	{
		System.out.println(msg);
		return msg;
	}
}
