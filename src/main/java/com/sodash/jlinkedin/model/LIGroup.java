package com.sodash.jlinkedin.model;

import winterwell.json.JSONObject;

import com.sodash.jlinkedin.fields.GroupField;
import com.sodash.jlinkedin.fields.ProfileField;

public class LIGroup extends LIModelBase {

	public LIGroup(String html) {
		super(new JSONObject());		
	}

	public LIGroup(JSONObject base) {
		super(base);
	}

	public String getDescription() {
		return base.optString("description");
	}
	
	/**
	 * Equivalent to {@link #getSmallLogoUrl()}
	 * @return
	 */
	public String getImageUrl() {
		return getSmallLogoUrl();
	}
	
	public String getSmallLogoUrl() {
		return base.optString(GroupField.SMALL_LOGO_URL.fieldName());
	}
	public String getLargeLogoUrl() {
		return base.optString(GroupField.LARGE_LOGO_URL.fieldName());
	}


	@Override
	public String getPublicUrl() {
		return base.optString(GroupField.SITE_GROUP_URL.fieldName());
	}

	public String getName() {
		return base.optString(GroupField.NAME.fieldName());
	}
}
