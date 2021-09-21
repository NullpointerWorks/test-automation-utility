package com.axtron.tau.model.log;

public class NopLogger implements ILogger
{
	@Override
	public String print(String msg){return "";}
	
	@Override
	public String println(String msg){return "";}
	
	@Override
	public String print(LogResult lr, String msg){return "";}
	
	@Override
	public String println(LogResult lr, String msg){return "";}
}
