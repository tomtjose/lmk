package com.ttj.lmk;

public class RegistrationDO {

	private String regurl;
	private String selection;
	private long interval;
	private RegType regtype;
	private String email;
	private String currentVal;
	private Conditions conditions;
	private long timestamp;
	private String name;
	
	public enum Conditions {
		DIFFERENT,
		MORE,
		LESS;
	}
	public enum RegType {
		NOTIFY_STOP,
		NOTIFY_CONTINUE,
		STORE,
		STOR_NOTIFY;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegurl() {
		return regurl;
	}

	public void setRegurl(String regurl) {
		this.regurl = regurl;
	}

	public String getSelection() {
		return selection;
	}

	public void setSelection(String selection) {
		this.selection = selection;
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public RegType getRegtype() {
		return regtype;
	}

	public void setRegtype(RegType regtype) {
		this.regtype = regtype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCurrentVal() {
		return currentVal;
	}

	public void setCurrentVal(String currentVal) {
		this.currentVal = currentVal;
	}

	public Conditions getConditions() {
		return conditions;
	}

	public void setConditions(Conditions conditions) {
		this.conditions = conditions;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
		
	
}
