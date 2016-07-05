package com.sodash.jlinkedin.model;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;

/**
 * A post / status-update / share. "Update" and "Share" are the terms LinkedIn API docs use. 
 * @author daniel
 *
 */
public class LIJobPosting extends LIPostBase {
	
	String sharedUrl;

	public LIJobPosting(JSONObject u) {
		super(u);
	}
	
	public LIJobPosting(LIModelBase u) {
		super(u.base);
	}
	
	public String toString() {
		return getClass().getSimpleName()+"["+StrUtils.joinWithSkip(" ", getTitle(), getContents())+"]";
	}
	
	public LIJobPosting(LIEvent u) {
		super(new JSONObject());
		throw new TodoException(u);
	}

	@Override
	public String getPublicUrl() {
		JSONObject request = getJobField("siteJobRequest");
		if (request == null) return null;
		return request.optString("url");
	}
	
	/**
	 * Returns updateContent.companyJobUpdate.job[field]
	 * @param field
	 * @return
	 */
	JSONObject getJobField(String field) {
		JSONObject job = getJob();
		if (job == null) return null;
		JSONObject v = job.optJSONObject(field);
		return v;
	}
	
	public final String getTitle() {
		JSONObject position = getJobField("position");
		if (position == null) return null;
		return position.optString("title");
	}

	@Override
	public final String getContents() {
		JSONObject job = getJob();
		if (job == null) return null;
		return job.optString("description");
	}
	

	@Override
	public String getId() {
		return extractIdFromUpdateKey(this.getUpdateKey());
	}
	
	/**
	 * ID not explicitly given as part of an Update - have to extract it from the update key,
	 * which looks like "UPDATE-companyid-updateid"
	 */
	public static String extractIdFromUpdateKey(String key) {
		if(key == null) return null;
		String[] keyBits = key.split("-");
		if (keyBits.length > 2) return keyBits[2];
		else return null;
	}
}
