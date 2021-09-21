package com.axtron.tau.view;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.view.awt.AbsoluteLayout;
import com.axtron.tau.view.swing.GhostText;

public class CustomConnectionJDialog extends JDialog 
{
	private static final long serialVersionUID = -6173711708276963767L;
	
	private final Border grayBorder = BorderFactory.createLineBorder( Color.LIGHT_GRAY, 1);
	private final Border redBorder = BorderFactory.createLineBorder( Color.RED, 1);
	
	private JButton jbtnRefresh;
	private JButton jbtnAccept;
	private JButton jbtnCancel;
	private JComboBox<String> jcbFirmata;
	private JComboBox<String> jcbSerial;
	private JTextField jtfNameInput;
	
	public CustomConnectionJDialog()
	{
		JLabel jlblFirmata = new JLabel("Firmata");
		jlblFirmata.setSize(70, 20);
		jlblFirmata.setPreferredSize(jlblFirmata.getSize());
		jlblFirmata.setLocation(15, 20);
		
		jcbFirmata = new JComboBox<String>();
		jcbFirmata.setSize(220, 20);
		jcbFirmata.setPreferredSize(jcbFirmata.getSize());
		jcbFirmata.setLocation(85, 20);
		
		JLabel jlblSerial = new JLabel("Serial");
		jlblSerial.setSize(70, 20);
		jlblSerial.setPreferredSize(jlblSerial.getSize());
		jlblSerial.setLocation(15, 50);
		
		jcbSerial = new JComboBox<String>();
		jcbSerial.setSize(220, 20);
		jcbSerial.setPreferredSize(jcbSerial.getSize());
		jcbSerial.setLocation(85, 50);
		
		jbtnRefresh = new JButton("Refresh");
		jbtnRefresh.setSize(100, 20);
		jbtnRefresh.setPreferredSize(jbtnRefresh.getSize());
		jbtnRefresh.setLocation(85, 80);
		
		JLabel jlblConnName = new JLabel("Name");
		jlblConnName.setSize(70, 20);
		jlblConnName.setPreferredSize(jlblConnName.getSize());
		jlblConnName.setLocation(15, 120);
		
		jtfNameInput = new JTextField();
		jtfNameInput.setLocation(85, 120);
		jtfNameInput.setSize(220, 20);
		jtfNameInput.setPreferredSize( jtfNameInput.getSize() );
		jtfNameInput.setBorder( grayBorder );
		new GhostText(jtfNameInput, "Connection Name");
		jtfNameInput.addKeyListener( new KeyAdapter() 
		{
			@Override
			public void keyReleased(KeyEvent e) 
			{
				int code = e.getKeyCode();
				if (code == KeyEvent.VK_ENTER)
				{
					jbtnAccept.doClick();
				}
			}
		});
		
		jbtnCancel = new JButton("Cancel");
		jbtnCancel.setSize(100, 25);
		jbtnCancel.setPreferredSize(jbtnCancel.getSize());
		jbtnCancel.setLocation(15,160);
		
		jbtnAccept = new JButton("Accept");
		jbtnAccept.setSize(100, 25);
		jbtnAccept.setPreferredSize(jbtnAccept.getSize());
		jbtnAccept.setLocation(205,160);
		
		JPanel jpDevice = new JPanel();
		jpDevice.setLayout( new AbsoluteLayout() );
		jpDevice.setLocation(0, 0);
		jpDevice.setSize(320, 115);
		jpDevice.setPreferredSize(jpDevice.getSize());
		jpDevice.setBorder( BorderFactory.createTitledBorder("FTDI Devices") );
		jpDevice.add(jlblFirmata);
		jpDevice.add(jcbFirmata);
		jpDevice.add(jlblSerial);
		jpDevice.add(jcbSerial);
		jpDevice.add(jbtnRefresh);
		
		JPanel jpInterface = new JPanel();
		jpInterface.setLayout( new AbsoluteLayout() );
		jpInterface.setSize(320, 200);
		jpInterface.setPreferredSize(jpInterface.getSize());
		jpInterface.add(jpDevice);
		jpInterface.add(jlblConnName);
		jpInterface.add(jtfNameInput);
		jpInterface.add(jbtnCancel);
		jpInterface.add(jbtnAccept);
		
		this.setModalityType( ModalityType.APPLICATION_MODAL );
		this.setTitle("Customized Mainboard");
		this.setResizable(false);
		this.add(jpInterface);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void setFirmataSelectCommand(ActionCommand ac) {jcbFirmata.addActionListener(ac);}
	public void setSerialSelectCommand(ActionCommand ac) {jcbSerial.addActionListener(ac);}
	public void setRefreshCommand(ActionCommand ac) {jbtnRefresh.addActionListener(ac);}
	public void setCancelCommand(ActionCommand ac) {jbtnCancel.addActionListener(ac);}
	public void setAcceptCommand(ActionCommand ac) {jbtnAccept.addActionListener(ac);}
	
	public String getNameFieldText() 
	{
		String name = jtfNameInput.getText().trim();
		if (name.equalsIgnoreCase("Connection Name")) name = ""; // remove ghost text
		name = name.replace("\t", "_"); // no tabs
		name = name.replace(" ", "_"); // no spaces
		return name;
	}
	
	public void setFieldError(boolean e)
	{
		if (e) jtfNameInput.setBorder( redBorder );
		else jtfNameInput.setBorder( grayBorder );
		jtfNameInput.repaint();
	}
	
	public void addFirmataName(String name)	{jcbFirmata.addItem(name);}
	public void clearFirmataNames() 		{jcbFirmata.removeAllItems();}
	public String getFirmataSelection() 	{return (String) jcbFirmata.getSelectedItem();}
	
	public void addSerialName(String name) 	{jcbSerial.addItem(name);}
	public void clearSerialNames() 			{jcbSerial.removeAllItems();}
	public String getSerialSelection() 		{return (String) jcbSerial.getSelectedItem();}
}
