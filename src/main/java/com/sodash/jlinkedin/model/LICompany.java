package com.sodash.jlinkedin.model;

import winterwell.json.JSONObject;
import winterwell.utils.Utils;

import com.sodash.jlinkedin.fields.CompanyField;
import com.winterwell.utils.containers.ArrayMap;

/**
 * {@link CompanyField}
 * @author daniel
 *
 */
public class LICompany extends LIEntity {

	
	public LICompany(JSONObject base) {
		super(base);
	}

	public LICompany(String companyId) {
		super(new JSONObject(new ArrayMap("id", companyId)));
	}

	public String getName() {
		return base.optString("name");
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
		return Utils.or(base.optString("squareLogoUrl"), base.optString("logoUrl"));
	}

	public String getPublicUrl() {
		if (getId()==null) return null;
		return "http://www.linkedin.com/company/"+getId();
	}
}
