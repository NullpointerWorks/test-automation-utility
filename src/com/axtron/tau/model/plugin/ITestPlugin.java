package com.axtron.tau.model.plugin;

import javax.swing.JPanel;

import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.axtron.tau.util.Relation;
import com.axtron.tau.util.Version;
import com.nullpointerworks.jd2xx.ID2XX;

public interface ITestPlugin 
{
	IFirmata getFirmata();
	ID2XX getD2XX();
	ILogger getLogger();
	String getMainboardName();
	
	/**
	 * 
	 * @return
	 */
	String getName();
	
	/**
	 * 
	 * @return
	 */
	String getDescription();
	
	/**
	 * 
	 * @return
	 */
	Version getVersion();
	
	/**
	 * 
	 * @return
	 */
	Relation getRelation();
	
	/**
	 * 
	 * @return
	 */
	JPanel getTestInterface();
}
