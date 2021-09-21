package com.axtron.tau.model.log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChronoConsoleLogger implements ILogger
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
		String s = getTime()+" "+msg;
		System.out.print(s);
		return s;
	}
	
	private String prtln(String msg)
	{
		String s = getTime()+" "+msg;
		System.out.println(s);
		return s;
	}
	
	private String getTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // "yyyy/MM/dd HH:mm:ss"
	    Date date = new Date();
	    return dateFormat.format(date);
	}
}
