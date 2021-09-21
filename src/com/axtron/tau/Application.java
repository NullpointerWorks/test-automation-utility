package com.axtron.tau;

import com.axtron.tau.control.*;
import com.axtron.tau.control.customdialog.CustomConnectActionCommand;
import com.axtron.tau.control.interfaces.ActionCommand;
import com.axtron.tau.control.interfaces.Command;
import com.axtron.tau.model.*;
import com.axtron.tau.model.classloader.SuperClassLoader;
import com.axtron.tau.model.classloader.UnmanagedClassLoader;
import com.axtron.tau.model.config.Configuration;
import com.axtron.tau.model.config.xml.XMLConfiguration;
import com.axtron.tau.model.log.*;
import com.axtron.tau.model.plugin.LibraryLoader;
import com.axtron.tau.model.plugin.PluginLoader;
import com.axtron.tau.view.*;
import com.axtron.tau.view.swing.UILookAndFeel;
import com.axtron.tau.util.PathBuilder;
import com.axtron.tau.util.Version;

import com.nullpointerworks.util.FileUtil;

public class Application 
{
	public static Version getVersion()
	{
		return new Version(1,0,1,"");
	}
	
	public static void main(String[] args) 
	{
		new Application();
	}
	
	public Application()
	{
		// java global look-and-feel
		UILookAndFeel.setLookAndFeel(UILookAndFeel.WINDOWS);
		
		// models
		PathBuilder mPath 					= new PathBuilder(FileUtil.getSourceCodePath(Application.class));
		Configuration mConfiguration 		= new XMLConfiguration(mPath);
		MainboardManager mMainboardManager 	= new MainboardManager();
		TestManager mTestManager 			= new TestManager();
		SuperClassLoader mSuperClassLoader 	= new UnmanagedClassLoader();
		LibraryLoader mLibraryLoader		= new LibraryLoader(mSuperClassLoader);
		PluginLoader mPluginLoader			= new PluginLoader(mSuperClassLoader);
		ILoggerFactory mLoggerFactory 		= new LoggerFactory();
		ILogger mLogger 					= mLoggerFactory.getLogger(LoggerType.CHRONO);
		
		// views
		StartInterface vStart 	= new StartInterface();
		AppWindow vWindow 		= new AppWindow("Test Automation Utility", 900, 600);
		
		// commands
		Command cLoadPlugs 		= new LoadPluginsCommand(mConfiguration, mPluginLoader, mLibraryLoader, mTestManager, vStart);
		Command cStartTest 		= new StartTestProcedureCommand(vWindow, mTestManager, mLogger);
		ActionCommand cRefesh 	= new RefreshMBActionCommand(vStart, mMainboardManager);
		ActionCommand cSelect 	= new MBSelectActionCommand(vStart, mMainboardManager, mTestManager);
		ActionCommand cCustom 	= new CustomConnectActionCommand(vStart, mMainboardManager);
		ActionCommand cConnect 	= new MainboardConnectActionCommand(vStart, mTestManager, cStartTest);
		
		// tie it all together
		vStart.setRefreshCommand(cRefesh);
		vStart.setSelectCommand(cSelect);
		vStart.setCustomCommand(cCustom);
		vStart.setConnectCommand(cConnect);
		vWindow.addPermanentTab("Start", vStart);
		
		// show/start application
		cLoadPlugs.onExecute();
		vWindow.setVisible(true);
		cRefesh.onExecute();
	}
}
