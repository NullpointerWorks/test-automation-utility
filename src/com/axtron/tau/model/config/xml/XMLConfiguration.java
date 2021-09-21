package com.axtron.tau.model.config.xml;

import java.util.ArrayList;
import java.util.List;

import com.axtron.tau.model.config.Configuration;
import com.axtron.tau.util.PathBuilder;
import com.axtron.tau.util.XMLLoader;

import exp.nullpointerworks.xml.Document;
import exp.nullpointerworks.xml.Element;
import exp.nullpointerworks.xml.Encoding;
import exp.nullpointerworks.xml.Version;
import exp.nullpointerworks.xml.prolog.XMLProlog;

/**
 * XML configuration
 */
public class XMLConfiguration implements Configuration
{
	private List<ConfigItem> items;
	private Document doc = null;
	private Element root = null;
	private final PathBuilder cnfgPath;
	private final XMLLoader loader;
	
	public XMLConfiguration(PathBuilder app_url)
	{
		loader = new XMLLoader();
		
		items = new ArrayList<ConfigItem>();
		items.add( new PluginFolderConfig() );
		items.add( new LibraryFolderConfig() );
		
		cnfgPath = app_url.getCopy();
		cnfgPath.setFileName("config.xml");
		
		if (!loader.existsXML(cnfgPath))
		{
			doc = makeDefault(cnfgPath);
		}
		else
		{
			doc = verifyFile(cnfgPath);
		}
		root = doc.getRootElement();
	}

	@Override
	public void setConfiguration(Configuration cnfg) 
	{
		Element root = doc.getRootElement();
		
		Element plugdir = root.getChild("PluginFolder");
		plugdir.setText( cnfg.getPluginFolder() );
		
		Element libdir = root.getChild("LibraryFolder");
		libdir.setText( cnfg.getLibraryFolder() );
		
		for (ConfigItem item : items)
		{
			item.verify(root);
		}
		
		loader.saveXML(doc, cnfgPath);
	}
	
	// ===================================================================
	
	private Document makeDefault(PathBuilder cnf)
	{
		Element root = makeDefaultRoot(cnf.getFolderPath());
		Document doc = new Document();
		doc.setProlog( new XMLProlog(Version.V10, Encoding.UTF8) );
		doc.setRootElement(root);
		loader.saveXML(doc,cnf);
		return doc;
	}
	
	private Document verifyFile(PathBuilder cnf)
	{
		doc = loader.loadXML(cnf);
		root = doc.getRootElement();
		
		if (root == null)
		{
			Element root = makeDefaultRoot(cnf.getFolderPath());
			doc.setProlog( new XMLProlog(Version.V10, Encoding.UTF8) );
			doc.setRootElement(root);
		}
		else
		{
			for (ConfigItem item : items)
			{
				item.verify(root);
			}
		}
		
		loader.saveXML(doc,cnf);
		return doc;
	}
	
	private Element makeDefaultRoot(String path)
	{
		Element root = new Element("Configuration");
		for (ConfigItem item : items)
		{
			root.addChild( item.make() );
		}
		return root;
	}
	
	// ===================================================================
	
	public String getPluginFolder()
	{
		Element el = root.getChild("PluginFolder");
		return el.getText();
	}
	
	@Override
	public String getLibraryFolder() 
	{
		Element el = root.getChild("LibraryFolder");
		return el.getText();
	}
}
