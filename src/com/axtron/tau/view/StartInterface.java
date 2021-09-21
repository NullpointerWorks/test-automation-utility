package com.axtron.tau.view;

import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.view.awt.AbsoluteLayout;
import com.axtron.tau.view.swing.JButtonGroup;
import com.nullpointerworks.util.pattern.Iterator;

public class StartInterface extends JPanel
{
	private static final long serialVersionUID = -3551787719857837540L;
	private final Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
	private final Font courier = new Font("courier new", Font.TRUETYPE_FONT, 12);
	private final int tileSize = 150;
	private final Color gray = new Color(190,190,190);
	
	private JButtonGroup jbgPluginButtonGroup;
	private JComboBox<String> jcbMainboard;
	private JButton jbCustom;
	private JButton jbRefresh;
	private JButton jbConnect;
	private JTextArea jtaConsole;
	private JPanel jpPluginPanel;
	private JScrollPane jspPluginSelectScrollArea;
	
	public StartInterface()
	{
		jbgPluginButtonGroup = new JButtonGroup();
		jbgPluginButtonGroup.setActionListener( (e)->
		{
			refreshTestPluginGroup();
			JButton s = (JButton)e.getSource();
			s.setBorder( BorderFactory.createLineBorder(new Color(0,220,0), 2) );
		});
		
		JLabel jlSelectMain = new JLabel();
		jlSelectMain.setText("Mainboard");
		jlSelectMain.setSize(230, 20);
		jlSelectMain.setPreferredSize(jlSelectMain.getSize());
		jlSelectMain.setLocation(10,0);
		
		jcbMainboard = new JComboBox<String>();
		jcbMainboard.setSize(220, 20);
		jcbMainboard.setPreferredSize(jcbMainboard.getSize());
		jcbMainboard.setLocation(15, 25);
		
		jbRefresh = new JButton();
		jbRefresh.setText("Refresh");
		jbRefresh.setSize(105, 20);
		jbRefresh.setPreferredSize(jbRefresh.getSize());
		jbRefresh.setLocation(15, 55);
		
		jbCustom = new JButton();
		jbCustom.setText("Custom");
		jbCustom.setSize(105, 20);
		jbCustom.setPreferredSize(jbCustom.getSize());
		jbCustom.setLocation(130, 55);
		
		jbConnect = new JButton();
		jbConnect.setText("Connect");
		jbConnect.setSize(220, 30);
		jbConnect.setPreferredSize(jbConnect.getSize());
		jbConnect.setLocation(15,85);
		
		jtaConsole = new JTextArea();
		jtaConsole.setEditable(false);
		jtaConsole.setFont(courier);
		jtaConsole.setLineWrap(true);
		jtaConsole.setWrapStyleWord(true);
		JScrollPane jspTextArea = new JScrollPane(jtaConsole);
		jspTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jspTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jspTextArea.getVerticalScrollBar().setUnitIncrement(16);
        jspTextArea.setBorder(lineBorder);
        jspTextArea.setSize(250,300);
        
        JPanel jpMainBoardSelect = new JPanel();
        jpMainBoardSelect.setLayout( new AbsoluteLayout() );
        jpMainBoardSelect.setSize(250, 130);
        jpMainBoardSelect.setPreferredSize( jpMainBoardSelect.getSize() );
        jpMainBoardSelect.setBorder( BorderFactory.createTitledBorder("Mainboard") );
        jpMainBoardSelect.add(jcbMainboard);
        jpMainBoardSelect.add(jbCustom);
        jpMainBoardSelect.add(jbRefresh);
        jpMainBoardSelect.add(jbConnect);
        
		JPanel jpConnect = new JPanel();
		jpConnect.setSize(250, 135);
		jpConnect.setPreferredSize(jpConnect.getSize());
		jpConnect.setBackground( new Color(240,240,240) );
		jpConnect.add(jpMainBoardSelect);
		
		JPanel jpSidePanel = new JPanel();
		jpSidePanel.setLayout( new BorderLayout() );
		jpSidePanel.setSize(250, 600); // height gets adjusted by parent
		jpSidePanel.setPreferredSize(jpSidePanel.getSize());
		jpSidePanel.setBackground(Color.WHITE);
		jpSidePanel.add(jpConnect ,BorderLayout.NORTH);
		jpSidePanel.add(jspTextArea, BorderLayout.CENTER);
		
		jpPluginPanel = new JPanel();
		jpPluginPanel.setLayout( new AbsoluteLayout() );
		jpPluginPanel.setSize(600,600);
		jpPluginPanel.setBackground( Color.LIGHT_GRAY );
		jspPluginSelectScrollArea = new JScrollPane(jpPluginPanel);
		jspPluginSelectScrollArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jspPluginSelectScrollArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspPluginSelectScrollArea.getVerticalScrollBar().setUnitIncrement(16);
		jspPluginSelectScrollArea.setBorder(lineBorder);
		jspPluginSelectScrollArea.addComponentListener(new ComponentAdapter() 
		{
            public void componentResized(ComponentEvent e) 
            {
                resizePluginPanel();
            }
        });
		
		this.setName("Start");
		this.setLayout(new BorderLayout());
		this.add(jspPluginSelectScrollArea, BorderLayout.CENTER);
		this.add(jpSidePanel, BorderLayout.EAST);
	}
	
	/*
	 * recalculate tiling positions for plugins
	 */
	private synchronized void resizePluginPanel()
	{
		int w = jpPluginPanel.getWidth();
		Component[] comps = jpPluginPanel.getComponents();
		int count = comps.length;
		int tsize = tileSize+10;
        int padding = (w%tsize) >> 1;
		int cols = w / tsize;
        if (cols==0) return;
        
        int rows = (count / cols) + (((count % cols)>0)?1:0);
		int i = 0;
		for (int y=0; y<rows; y++)
		{
			int yy = 10+y*tsize;
			for (int x=0; x<cols; x++)
			{
				int xx = padding+x*tsize;
				
				Component comp = comps[i];
				comp.setLocation(xx, yy);
				i++;
				
				if (i >= count) break;
			}
			
			if (i >= count) break;
		}
		jpPluginPanel.validate();
	}
	
	public void setCustomCommand(ActionCommand ac) {jbCustom.addActionListener(ac);}
	public void setRefreshCommand(ActionCommand ac) {jbRefresh.addActionListener(ac);}
	public void setConnectCommand(ActionCommand ac) {jbConnect.addActionListener(ac);}
	public void setSelectCommand(ActionCommand ac) {jcbMainboard.addActionListener(ac);}
	
	public void addTestPluginTile(BufferedImage image, String label, ActionCommand ac)
	{
		JButton jbPlugAction = new JButton();
		jbPlugAction.addActionListener(ac);
		jbPlugAction.setHorizontalTextPosition(JButton.CENTER);
		jbPlugAction.setVerticalTextPosition(JButton.BOTTOM);
		jbPlugAction.setIcon( new ImageIcon(image) );
		jbPlugAction.setText(label);
		jbPlugAction.setBorder( BorderFactory.createLineBorder(new Color(190,190,190), 2) );
		
		JPanel p = new JPanel();
		p.setLayout( new BorderLayout() );
		p.setSize(tileSize, tileSize);
		p.setPreferredSize(p.getSize());
		p.add(jbPlugAction);
		
		jbgPluginButtonGroup.add(jbPlugAction);
		jpPluginPanel.add(p);
		resizePluginPanel();
	}
	
	public void refreshInterface()
	{
		jbRefresh.doClick();
		refreshTestPluginGroup();
	}
	
	public void refreshTestPluginGroup()
	{
		Iterator<JButton> btns = jbgPluginButtonGroup.getButtonIterator();
		while(btns.hasNext())
		{
			JButton s = btns.getNext();
			s.setBorder( BorderFactory.createLineBorder(gray, 2) );
		}
		setDescriptionText("");
	}
	
	public void setDescriptionText(String text)
	{
		jtaConsole.setText(text);
	}
	
	public void setConnectButton(boolean t)
	{
		if (t)
		{
			jbConnect.setText("Disconnect");
		}
		else
		{
			jbConnect.setText("Connect");
		}
	}
	
	public void addMainboardName(String name)
	{
		addMainboardName(name, false);
	}
	
	public void addMainboardName(String name, boolean focus)
	{
		int c = jcbMainboard.getItemCount();
		jcbMainboard.addItem(name);
		if (focus) 
		{
			jcbMainboard.setSelectedIndex(c);
			jbConnect.grabFocus();
		}
	}
	
	public void clearMainboardNames() 
	{
		jcbMainboard.removeAllItems();
	}
	
	public String getMainboardSelection()
	{
		return (String) jcbMainboard.getSelectedItem();
	}
}
