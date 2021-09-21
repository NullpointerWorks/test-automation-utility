package com.axtron.tau.model.log;

public enum LogResult
{
	LOG		("[ Log    ]"),
	STEP	("[ Step   ]"),
	PASS	("[ Pass   ]"),
	FAIL	("[ Fail   ]"),
	ERROR	("[ Error  ]"),
	DEBUG	("[ Debug  ]"),
	RESULT	("[ Result ]"),
	EMPTY	("[        ]"),
	NULL	("");
	
	@Override
	public String toString() {return s;}
	private final String s;
	private LogResult(String s) {this.s=s;}
}
