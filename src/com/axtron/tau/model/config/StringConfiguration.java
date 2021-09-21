package com.axtron.tau.model.config;

public class StringConfiguration implements Configuration
{
	private String sPluginFolder = "";
	private String sLibraryFolder = "";
	
	public StringConfiguration() {}
	
	@Override
	public void setConfiguration(Configuration cnfg) 
	{
		sPluginFolder = cnfg.getPluginFolder();
		sLibraryFolder = cnfg.getLibraryFolder();
	}
	
	public void setPluginFolder(String str) {sPluginFolder = str;}
	public String getPluginFolder() {return sPluginFolder;}
	
	public void setLibraryFolder(String str) {sLibraryFolder = str;}
	public String getLibraryFolder() {return sLibraryFolder;}
	
	
	
}
