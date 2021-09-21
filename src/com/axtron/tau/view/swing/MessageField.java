package com.axtron.tau.view.swing;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class MessageField extends JTextPane
{
	private static final long serialVersionUID = 1702760469040587066L;

	public MessageField()
	{
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		StyledDocument doc = getStyledDocument();
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		setEditable(false);
		setFocusable(false);
	}
	
	public MessageField(String string) 
	{
		this();
		setText(string);
	}
}
