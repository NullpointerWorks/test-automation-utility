package com.axtron.tau.model.log;

/**
 * 
 */
public class LoggerFactory implements ILoggerFactory
{
	@Override
	public ILogger getLogger(LoggerType type)
	{
		switch(type)
		{
		default:
		case NOP:
			return new NopLogger();
		case CONSOLE:
			return new ConsoleLogger();
		case CHRONO:
			return new ChronoConsoleLogger();
		}
	}
}
