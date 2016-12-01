package com.sodash.jlinkedin.model;

import java.util.ArrayList;
import java.util.List;

import com.winterwell.json.JSONArray;
import com.winterwell.json.JSONObject;
import com.winterwell.utils.StrUtils;

import com.sodash.jlinkedin.fields.ProfileField;
import com.winterwell.jgeoplanet.IPlace;
import com.winterwell.jgeoplanet.SimplePlace;

public class LIProfile extends LIEntity {
	

	public LIProfile(JSONObject jobj) {
		super(jobj);
		// 		// We'll store private (almost) "as normal" and deal with the weirdness
//		super(new XId(
//				LinkedInPlugin.PRIVATE.equals(person.getId())? LinkedInPlugin.PRIVATE : XId.WART_P+person.getId(), 
//				LinkedInPlugin.dflt));
//		this.base = person;
//		if (LinkedInPlugin.PRIVATE.equals(person.getId())){
//			name = LinkedInPlugin.PRIVATE;
//		} else if (Utils.isBlank(base.getFirstName())) {
//			name = base.getLastName();
//		} else {
//			name = base.getFirstName()+" "+base.getLastName();
//		}
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
		if (posns==null) {
			posns = base.optJSONObject(ProfileField.THREE_CURRENT_POSITIONS.fieldName());
			if (posns==null) {
				return null;
			}
		}
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


	public Object getAssociations() {
		Object edu = base.opt(ProfileField.ASSOCIATIONS.fieldName());
		if (edu != null) {
			System.out.println(edu);
		}
		return edu;
	}


	public String getIndustry() {
		return base.optString(ProfileField.INDUSTRY.fieldName());
	}


	public Object getEducations() {
		Object edu = base.opt(ProfileField.EDUCATIONS.fieldName());
		if (edu != null) {
			System.out.println(edu);
		}
		return edu;
	}

}
