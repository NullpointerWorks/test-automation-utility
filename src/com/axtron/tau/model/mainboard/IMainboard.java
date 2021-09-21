package com.axtron.tau.model.mainboard;

import com.axtron.tau.model.mainboard.firmata.IFirmata;
import com.nullpointerworks.jd2xx.ID2XX;

public interface IMainboard 
{
	String getName();
	IFirmata getFirmata();
	ID2XX getFTDI();
	
	boolean testConnection();
	boolean isConnected();
	boolean connect();
	boolean disconnect();
}
