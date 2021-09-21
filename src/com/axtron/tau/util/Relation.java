package com.axtron.tau.util;

public enum Relation 
{
	UNKNOWN("Unknown");
	// exisitng relations removed due to NDA
	
	private String name = "";
	private Relation(String n) {name = n;}
	public String getName() {return name;}
}
