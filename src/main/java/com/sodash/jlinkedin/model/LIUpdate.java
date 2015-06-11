package com.sodash.jlinkedin.model;

import java.util.List;

import com.sodash.jlinkedin.fields.NetworkUpdateReturnType;

import winterwell.json.JSONObject;
import winterwell.utils.TodoException;

/**
 * An event (something like making a connection, which can't really be described as a message).
 * @author daniel
 *
 */
public class LIUpdate extends LIModelBase {

	public LIUpdate(JSONObject base) {
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

	public List<LIMessage> getComments() {
		throw new TodoException();
	}

}