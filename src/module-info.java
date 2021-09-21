module axtron.tau 
{
	requires transitive libnpw.xml; 
	requires libnpw.util;

	requires transitive java.desktop;
	requires transitive firmata4j;
	requires transitive jd2xx;
	requires jssc; // java 8
	
	exports com.axtron.tau.control.interfaces;
	exports com.axtron.tau.model.classloader;
	exports com.axtron.tau.model.log;
	exports com.axtron.tau.model.mainboard.firmata;
	exports com.axtron.tau.model.plugin;
	exports com.axtron.tau.view.awt;
	exports com.axtron.tau.view.swing;
	exports com.axtron.tau.util;
	exports com.axtron.tau.util.pinout;
}
