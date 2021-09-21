package com.axtron.tau.model.log;

/**
 * 
 */
public interface ILogger
{
	/**
	 * 
	 */
	String print(String msg);
	
	/**
	 * 
	 */
	String println(String msg);
	
	/**
	 * 
	 */
	String print(LogResult lr, String msg);
	
	/**
	 * 
	 */
	String println(LogResult lr, String msg);
}
