package com.axtron.tau.view.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.nullpointerworks.util.pattern.Iterator;

/**
 * Simple button group for JButtons
 */
public class JButtonGroup implements ActionListener
{
	private List<JButton> group;
	private ActionListener al = null;
	
	public JButtonGroup()
	{
		this(null);
	}
	
	public JButtonGroup(ActionListener al)
	{
		group = new ArrayList<JButton>();
		setActionListener(al);
	}
	
	public synchronized void add(JButton btn)
	{
		if (group.contains(btn)) return;
		btn.addActionListener(this);
		group.add(btn);
	}
	
	public synchronized void remove(JButton btn)
	{
		if (group.contains(btn)) group.remove(btn);
	}
	
	public synchronized void clear()
	{
		group.clear();
	}
	
	public synchronized Iterator<JButton> getButtonIterator()
	{
		return new Iterator<JButton>(group);
	}
	
	public synchronized void setActionListener(ActionListener al)
	{
		this.al=al;
	}
	
	@Override
	public synchronized void actionPerformed(ActionEvent e)
	{
		if (al!=null) al.actionPerformed(e);
	}
}
