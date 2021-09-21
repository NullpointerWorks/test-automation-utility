package com.axtron.tau.model.plugin;

import javax.swing.JPanel;

import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.axtron.tau.util.Relation;
import com.axtron.tau.util.Version;
import com.nullpointerworks.jd2xx.ID2XX;

/*
 * adapter class to set IO devices while keeping the methods hidden for the end user
 */
public class TestPluginWrapper implements ITestPlugin
{
	private TestPlugin testplug;
	
	public TestPluginWrapper(TestPlugin tp)
	{
		testplug = tp;
	}
	
	public void setFirmata(IFirmata f) {testplug.setFirmata(f);}
	public void setD2XX(ID2XX d) {testplug.setD2XX(d);}
	public void setLogger(ILogger l) {testplug.setLogger(l);}
	public void setMainboardName(String m) {testplug.setMainboardName(m.toUpperCase());}
	
	@Override
	public IFirmata getFirmata() 
	{
		return testplug.getFirmata();
	}

	@Override
	public ID2XX getD2XX() 
	{
		return testplug.getD2XX();
	}

	@Override
	public ILogger getLogger() 
	{
		return testplug.getLogger();
	}

	@Override
	public String getMainboardName() 
	{
		return testplug.getMainboardName();
	}

	@Override
	public String getName() 
	{
		return testplug.getName();
	}

	@Override
	public String getDescription() 
	{
		return testplug.getDescription();
	}

	@Override
	public Version getVersion() 
	{
		return testplug.getVersion();
	}

	@Override
	public Relation getRelation() 
	{
		return testplug.getRelation();
	}

	@Override
	public JPanel getTestInterface() 
	{
		return testplug.getTestInterface();
	}
}
