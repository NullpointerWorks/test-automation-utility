package com.axtron.tau.model.plugin;

import javax.swing.JPanel;

import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.axtron.tau.util.Relation;
import com.axtron.tau.util.Version;
import com.nullpointerworks.jd2xx.ID2XX;

/**
 * 
 * @author Michiel
 */
public abstract class TestPlugin implements ITestPlugin
{
	private IFirmata firmata;
	private ID2XX ftdi;
	private ILogger log;
	private String mbname;
	
	void setFirmata(IFirmata f) {firmata = f;}
	void setD2XX(ID2XX d) {ftdi = d;}
	void setLogger(ILogger l) {log = l;}
	void setMainboardName(String m) {mbname = m;}
	
	public final IFirmata getFirmata() {return firmata;}
	public final ID2XX getD2XX() {return ftdi;}
	public final ILogger getLogger() {return log;}
	public final String getMainboardName() {return mbname;}
	
	/**
	 * 
	 * @return
	 */
	public abstract String getName();
	
	/**
	 * 
	 * @return
	 */
	public abstract String getDescription();
	
	/**
	 * 
	 * @return
	 */
	public abstract Version getVersion();
	
	/**
	 * 
	 * @return
	 */
	public abstract Relation getRelation();
	
	/**
	 * 
	 * @return
	 */
	public abstract JPanel getTestInterface();
	
}
