package com.axtron.tau.control;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.axtron.tau.control.interfaces.Command;
import com.axtron.tau.model.TestManager;
import com.axtron.tau.model.TestSetup;
import com.axtron.tau.model.log.ILogger;
import com.axtron.tau.model.mainboard.IMainboard;
import com.axtron.tau.model.plugin.TestPlugin;
import com.axtron.tau.model.plugin.TestPluginWrapper;
import com.axtron.tau.util.Relation;
import com.axtron.tau.view.AppWindow;
import com.axtron.tau.view.swing.ClosableTabHeader;
import com.axtron.tau.view.swing.EditorListener;

public class StartTestProcedureCommand implements Command
{
	private final AppWindow vWindow;
	private final TestManager mTestmanager;
	private final ILogger logger;
	
	public StartTestProcedureCommand(AppWindow window, TestManager testmanager, ILogger log)
	{
		mTestmanager = testmanager;
		vWindow = window;
		logger = log;
	}
	
	@Override
	public void onExecute() 
	{
		TestSetup ref = mTestmanager.confirmSetup();
		// could validate the test setup, but before it gets here it already goes through lots of error checks.
		
		TestPlugin pl = ref.getTestPlugin();
		IMainboard mb = ref.getMainboard();
		
		TestPluginWrapper wrapper = new TestPluginWrapper(pl);
		wrapper.setFirmata( mb.getFirmata() );
		wrapper.setD2XX( mb.getFTDI() );
		wrapper.setLogger( logger );
		wrapper.setMainboardName( mb.getName() );
		
		String tabTitle = pl.getName();
		if (pl.getRelation() != Relation.UNKNOWN)
			tabTitle = pl.getRelation().getName() + " - " + tabTitle;
		
		ClosableTabHeader header = vWindow.addClosableTab( tabTitle , pl.getTestInterface() );
		if (header == null)
		{
			int dialogResult = JOptionPane.showConfirmDialog(null, 
					"Failed to open a tab for this test. Would you like to open a new window instead?", 
					"Tab Creation Failure", JOptionPane.YES_NO_OPTION);
			if (dialogResult == JOptionPane.YES_OPTION)
			{
				JDialog testdialog = new JDialog();
				testdialog.setModalityType( ModalityType.APPLICATION_MODAL );
				testdialog.setTitle(pl.getRelation().getName() + " - " + pl.getName());
				testdialog.setResizable(true);
				testdialog.add(pl.getTestInterface());
				testdialog.pack();
				testdialog.setLocationRelativeTo(null);
				testdialog.setVisible(true);
				return;
			}
		}
		
		EditorListener cPlugClose = new ClosePluginTabListener(mTestmanager, ref);
		header.addEditorListener(cPlugClose);
	}
}
