package com.axtron.tau.control.interfaces;

public interface RunnableCommand extends Command, Runnable 
{
	@Override
	public default void run()
	{
		onExecute();
	}
}
