package com.sodash.jlinkedin.model;

import java.util.ArrayList;
import java.util.List;

import com.sodash.jlinkedin.fields.ProfileField;
import com.winterwell.jgeoplanet.IPlace;
import com.winterwell.jgeoplanet.SimplePlace;

import creole.data.XId;
import winterwell.json.JSONArray;
import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.web.SimpleJson;

public class LIProfile extends LIModelBase {
	

	public LIProfile(JSONObject jobj) {
		this.base = jobj;
	}	
	

	public String getHeadline() {
		return base.optString("headline");
	}
	public String getName() {
		return StrUtils.joinWithSkip(" ", getFirstName(), getLastName());
	}

	public String getLastName() {
		return base.optString("lastName");
	}

	public String getFirstName() {
		return base.optString("firstName");
	}

	public IPlace getLocation() {
		Object locn = base.getMap().get("location");
		if (locn==null) return null;
		if (locn instanceof String) {
			return new SimplePlace((String)locn);
		}
		JSONObject lobj = (JSONObject) locn; 
		JSONObject countryObj = lobj.optJSONObject("country");
		String country = countryObj==null? null : countryObj.optString("code");
		return new SimplePlace(lobj.optString("name"), null, country);
	}

	public List<LIPosition> getPositions() {
		JSONObject posns = base.optJSONObject(ProfileField.POSITIONS.fieldName());
		if (posns==null) return null;
		JSONArray values = posns.optJSONArray("values");
		if (values==null) return null;
		List<LIPosition> list = new ArrayList();
		for(Object _posn : values.getList()) {
			JSONObject posn = (JSONObject) _posn;
			list.add(new LIPosition(posn));
		}
		return list;
	}	
	
	public String getPublicUrl() {
		return base.optString(ProfileField.PUBLIC_PROFILE_URL.fieldName());
	}


	public String getImageUrl() {
		return base.optString(ProfileField.PICTURE_URL.fieldName());
	}


	public String getSummary() {
		return base.optString(ProfileField.SUMMARY.fieldName());
	}

}
