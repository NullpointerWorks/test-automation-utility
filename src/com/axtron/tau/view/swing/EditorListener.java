package com.axtron.tau.view.swing;

public interface EditorListener 
{
	void onTabHeaderLink(ClosableTabHeader t);
	
	void onTabOpening();
	void onTabClosing();

	void onModification();
	
	void onSaveAction();
}
