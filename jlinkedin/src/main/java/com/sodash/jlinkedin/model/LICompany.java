package com.sodash.jlinkedin.model;

import com.sodash.jlinkedin.fields.CompanyField;

import winterwell.utils.Utils;

/**
 * {@link CompanyField}
 * @author daniel
 *
 */
public class LICompany extends LIModelBase {

	
	public String getName() {
		return base.getString("name");
	}
	
	public String getType() {
		return base.optString("type");
	}

	public String getIndustry() {
		return base.optString("industry");
	}
	
	public String getDescription() {
		return base.optString("description");
	}

	public String getImageUrl() {
		return Utils.or(base.optString("square-logo-url"), base.optString("logo-url"));
	}

	public String getPublicUrl() {
		if (getId()==null) return null;
		return "http://www.linkedin.com/company/"+getId();
	}
}
