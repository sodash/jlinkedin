package com.sodash.jlinkedin.model;

import com.sodash.jlinkedin.fields.GroupField;

public class LIGroup extends LIModelBase {


	public String getDescription() {
		return base.optString("description");
	}

	public String getUrl() {
		return base.optString(GroupField.SITE_GROUP_URL.fieldName());
	}
}
