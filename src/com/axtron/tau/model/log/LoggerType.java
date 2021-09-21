package com.axtron.tau.model.log;

public enum LoggerType
{
	/**
	 * Does nothing when a log method is called.
	 */
	NOP,
	
	/**
	 * Prints the log request to the console.
	 */
	CONSOLE,
	
	/**
	 * Prints the log request to the console and adds a time-stamp.
	 */
	CHRONO
}
