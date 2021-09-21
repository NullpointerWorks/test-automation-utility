package com.axtron.tau.model.log;

public interface ILoggerFactory
{
	public ILogger getLogger(LoggerType type);
}
