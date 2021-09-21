package com.axtron.tau.control.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public interface ActionCommand extends RunnableCommand, ActionListener 
{
	// create new thread instead of using this method's thread
	// Swing UI threads hang until method completion due to concurrency issues
	@Override
	public default void actionPerformed(ActionEvent e)
	{
		Thread t = new Thread(this);
		t.start();
	}
}
