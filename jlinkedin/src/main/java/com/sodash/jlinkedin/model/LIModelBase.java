package com.sodash.jlinkedin.model;

import winterwell.json.JSONObject;

public abstract class LIModelBase {

	@Override
	public String toString() {
		return base.toString();
	}

	JSONObject base;

	public String getId() {		
		return base.getString("id");
	}

	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj==null || obj.getClass() != getClass()) return false;
		return getId().equals(((LIProfile) obj).getId());
	}
}
