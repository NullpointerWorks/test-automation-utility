package com.axtron.tau.model.config.xml;

import exp.nullpointerworks.xml.Element;

public interface ConfigItem 
{
	String name();
	Element make();
	void verify(Element root);
}
