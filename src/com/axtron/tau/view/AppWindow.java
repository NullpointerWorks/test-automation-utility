package com.axtron.tau.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.axtron.tau.Application;
import com.axtron.tau.Resources;
import com.axtron.tau.view.swing.ClosableJTabbedPane;
import com.axtron.tau.view.swing.ClosableTabHeader;
import com.axtron.tau.view.swing.StatusBarJPanel;

public class AppWindow 
{
	private JFrame jfFrame;
	private ClosableJTabbedPane ctpTabs;
	private StatusBarJPanel jpStatusBar;
	
	public AppWindow(String title, int width, int height)
	{
		ctpTabs = new ClosableJTabbedPane();
		
		JLabel jlVersionText = new JLabel();
		jlVersionText.setSize(140,19);
		jlVersionText.setText( "Version: "+Application.getVersion().getStringFull() );
		
		jpStatusBar = new StatusBarJPanel();
		jpStatusBar.setSize(width,20);
		jpStatusBar.setPreferredSize( jpStatusBar.getSize() );
		jpStatusBar.addStatusBarElement(jlVersionText);
		
		JPanel jpInterface = new JPanel();
		jpInterface.setLayout( new BorderLayout() );
		jpInterface.setSize(width, height);
		jpInterface.setPreferredSize(jpInterface.getSize());
		jpInterface.add(ctpTabs, BorderLayout.CENTER);
		jpInterface.add(jpStatusBar, BorderLayout.SOUTH);
		
		jfFrame = new JFrame();
		jfFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfFrame.setTitle(title);
		jfFrame.setResizable(true);
		jfFrame.add(jpInterface);
		jfFrame.pack();
		jfFrame.setLocationRelativeTo(null);
		
		List<Image> icons = new ArrayList<Image>();
        icons.add( Resources.getImages().getApplicationIcon16() );
        icons.add( Resources.getImages().getApplicationIcon32() );
        icons.add( Resources.getImages().getApplicationIcon48() );
        icons.add( Resources.getImages().getApplicationIcon64() );
        jfFrame.setIconImages(icons);
	}
	
	public ClosableTabHeader addClosableTab(String title, Component comp) 
	{
		ClosableTabHeader header = ctpTabs.addClosableTab(title, null, comp);
		if (header!=null) 
		{
			int index = ctpTabs.getTabCount();
			ctpTabs.setSelectedIndex(index-1);
		}
		return header;
	}
	
	public void addPermanentTab(String title, Component comp) 
	{
		int index = ctpTabs.getComponentCount();
		ctpTabs.addPermanentTab(title, comp);
		ctpTabs.setSelectedIndex(index);
	}
	
	public void setVisible(boolean b) 
	{
		jfFrame.setVisible(b);
    }
}
