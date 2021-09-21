package com.axtron.tau.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.axtron.tau.model.mainboard.DevicePort;
import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.model.mainboard.Mainboard;
import com.nullpointerworks.util.pattern.Iterator;

import jd2xx.JD2XX;

/**
 * responsible for detecting COM ports and mainboards
 */
public class MainboardManager
{
	private List<IMainboard> mainboards;
	
	public MainboardManager()
	{
		mainboards = new ArrayList<IMainboard>();
	}
	
	public void addMainboard(IMainboard mb)
	{
		mainboards.add(mb);
	}
	
	public void addMainboard(DevicePort firm, DevicePort ftdi, String name)
	{
		addMainboard(firm.getPortLocation(), ftdi.getPortLocation(), name);
	}
	
	private void addMainboard(int firmLOC, int ftdiLOC, String name)
	{
		IMainboard mb = new Mainboard(firmLOC, ftdiLOC, name);
		addMainboard(mb);
	}
	
	public IMainboard findMainboardByName(String name)
	{
		for (IMainboard mb : mainboards)
		{
			if (mb.getName().equalsIgnoreCase(name))
			{
				return mb;
			}
		}
		return null;
	}
	
	public Iterator<IMainboard> getMainboards()
	{
		return new Iterator<IMainboard>(mainboards);
	}
	
	public void refresh()
	{
		detectMainboards(mainboards);
	}
	
	// ================================================================
	
	private void detectMainboards(List<IMainboard> mainboards)
	{
		mainboards.clear();
		
		// open D2XX and find all FTDI devices
		JD2XX jd = new JD2XX();
		Object[] locs = null;
		Object[] desc = null;
		try
		{
			locs = jd.listDevicesByLocation();
			desc = jd.listDevicesByDescription();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		if (locs==null) return;
		if (desc==null) return;
		
		// look for FTDI devices names as FIRMATA
		List<String> firmataList = new ArrayList<String>();
		List<Integer> firmataLocs = new ArrayList<Integer>();
		for (int i=0; i<desc.length; i++)
		{
			String dev = (String)desc[i];
			dev = dev.toUpperCase();
			if (dev.contains("FIRMATA"))
			{
				firmataList.add(dev);
				firmataLocs.add((Integer)locs[i]);
			}
		}
		if (firmataList.size() == 0) return;
		
		// for each FIRMATA device, look for a matching SERIAL device
		for (int j=0; j<firmataList.size(); j++)
		{
			String num = firmataList.get(j).replace("FIRMATA", "").trim();
			for (int i=0; i<desc.length; i++)
			{
				String dev = (String)desc[i];
				dev = dev.toUpperCase();
				if (dev.contains(num))
				if (dev.contains("SERIAL"))
				{
					Integer loc = (Integer)locs[i];
					addMainboard(firmataLocs.get(j),loc,num);
				}
			}
		}
	}
}
