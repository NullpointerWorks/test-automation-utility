package com.axtron.tau.model.config.xml;

import com.nullpointerworks.util.StringUtil;

import exp.nullpointerworks.xml.Element;

public class LibraryFolderConfig implements ConfigItem
{
	private String DEFAULT = "libraries/";

	public LibraryFolderConfig()
	{
		
	}
	
	public String name()
	{
		return "LibraryFolder";
	}
	
	@Override
	public Element make() 
	{
		var el = new Element("LibraryFolder");
		el.setText(DEFAULT);
		return el;
	}
	
	@Override
	public void verify(Element root) 
	{
		Element base = root.getChild("LibraryFolder");
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
