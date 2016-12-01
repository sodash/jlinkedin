package com.sodash.jlinkedin.model;

import com.winterwell.json.JSONObject;

public abstract class LIModelBase {

	@Override
	public String toString() {
		return base.toString();
	}

	final JSONObject base;
	
	public LIModelBase(JSONObject base) {
		this.base = base;
	}

	public String getId() {		
		return base.getString("id");
	}

	abstract public String getPublicUrl();
	
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
