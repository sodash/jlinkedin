package com.sodash.jlinkedin.model;

import java.util.List;

import com.winterwell.json.JSONObject;
import com.winterwell.utils.TodoException;

import com.sodash.jlinkedin.fields.NetworkUpdateReturnType;

/**
 * An event (something like making a connection, which can't really be described as a message).
 * @author daniel
 *
 */
public class LIEvent extends LIModelBase {

	public LIEvent(JSONObject base) {
		super(base);
	}

	@Override
	public String getPublicUrl() {
		return null;
	}

	public NetworkUpdateReturnType getType() {
		throw new TodoException();
	}

	public String getUpdateKey() {
		throw new TodoException();
	}

	public Object getContents() {
		throw new TodoException();
	}

	public List<LIComment> getComments() {
		throw new TodoException();
	}
	
	public LICompany getCompany() {
		if(!this.base.has("company")) return null;
		return new LICompany(this.base.getJSONObject("company"));
	}
}
