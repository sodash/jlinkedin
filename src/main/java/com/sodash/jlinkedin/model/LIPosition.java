package com.sodash.jlinkedin.model;

import winterwell.json.JSONObject;

/**
 * A job
 * @author daniel
 *
 */
public class LIPosition {

	private JSONObject base;

	public LIPosition(JSONObject posn) {
		this.base = posn;
	}

	@Override
	public String toString() {
		return base.toString();
	}
	
	public Object getCompany() {
		return ""; // TODO
	}
	
	public Boolean isCurrent() {
		return base.optBoolean("isCurrent");
	}
	
	public String getTitle() {
		return base.getString("title");
	}
}
