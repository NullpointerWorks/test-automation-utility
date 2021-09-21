package com.axtron.tau.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.axtron.tau.Resources;
import com.axtron.tau.view.awt.AbsoluteLayout;
import com.axtron.tau.view.swing.MessageField;

/**
 * Known issues:
 * When running procedure code inside a Swing thread of this window (for example a WindowListener), the animation won't show up.
 * 
 * @author Michiel
 */
public class InfiniteLoadView extends JDialog
{
	private static final long serialVersionUID = 6761344283747639113L;
	private static final Font verdana = new Font("Verdana", Font.PLAIN, 24);
	
	private MessageField mfMessage;
	
	public InfiniteLoadView(String title)
	{
		mfMessage = new MessageField();
		mfMessage.setFont( verdana );
		mfMessage.setLocation(0, 50);
		mfMessage.setSize(400, 50);
		
		JLabel jlPreloader = new JLabel(); 
		jlPreloader.setLocation(130, 130);
		jlPreloader.setSize(140, 21);
		jlPreloader.setIcon( Resources.getIcons().getPreloadBar() );
		
		JPanel jpInterface = new JPanel();
		jpInterface.setLayout( new AbsoluteLayout() );
		jpInterface.setBackground( Color.WHITE );
		jpInterface.setSize(400, 200);
		jpInterface.setPreferredSize(jpInterface.getSize());
		jpInterface.add(mfMessage);
		jpInterface.add(jlPreloader);
		
		this.setModalityType( ModalityType.APPLICATION_MODAL );
		this.setTitle(title);
		this.setResizable(false);
		this.add(jpInterface);
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	public void setMessage(String txt)
	{
		mfMessage.setText(txt);
	}
	
	public void setMessage(int size, String txt)
	{
		mfMessage.setFont( new Font("Verdana", Font.PLAIN, size) );
		mfMessage.setText( txt );
	}
	
	public void setMessage(Font font, String txt)
	{
		mfMessage.setFont(font);
		mfMessage.setText(txt);
	}
}
