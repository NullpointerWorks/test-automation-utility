package com.axtron.tau.model;

import java.util.ArrayList;
import java.util.List;

import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.model.plugin.TestPlugin;

public class TestManager 
{
	private List<TestSetup> setups;
	private IMainboard selectedMB;
	private TestPlugin selectedPL;
	
	public TestManager()
	{
		setups = new ArrayList<TestSetup>();
		selectedMB = null;
		selectedPL = null;
	}
	
	public void selectMainboard(IMainboard mainboard) {selectedMB = mainboard;}
	public void selectPlugin(TestPlugin plugin) {selectedPL = plugin;}
	
	public IMainboard getSelectedMainboard() {return selectedMB;}
	public TestPlugin getSelectedTestPlugin() {return selectedPL;}
	
	/**
	 * @return a reference to the test setup
	 */
	public TestSetup confirmSetup()
	{
		var ts = new TestSetup(selectedMB, selectedPL);
		setups.add( ts );
		selectedMB = null;
		selectedPL = null;
		return ts;
	}
	
	public boolean removeSetup(TestSetup ref)
	{
		int l = setups.size() - 1;
		while (l>=0)
		{
			TestSetup ts = setups.get(l);
			if (ts.hashCode() == ref.hashCode())
			{
				setups.remove(l);
				return true;
			}
			l--;
		}
		return false;
	}
}
