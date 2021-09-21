package com.axtron.tau.model.config.xml;

import com.nullpointerworks.util.StringUtil;

import exp.nullpointerworks.xml.Element;

public class PluginFolderConfig implements ConfigItem
{
	private String DEFAULT = "plugins/";

	public PluginFolderConfig()
	{
		
	}
	
	public String name()
	{
		return "PluginFolder";
	}
	
	@Override
	public Element make() 
	{
		var el = new Element("PluginFolder");
		el.setText(DEFAULT);
		return el;
	}
	
	@Override
	public void verify(Element root) 
	{
		Element base = root.getChild("PluginFolder");
		if (base == null) 
		{
			root.addChild( make() );
		}
		else 
		{
			String text = base.getText();
			if ( !StringUtil.isInteger(text) )
			{
				base.setText(DEFAULT);
			}
			base.setText(text);
		}
	}
}
