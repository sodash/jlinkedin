package com.sodash.jlinkedin;

import java.util.Collection;
import java.util.Map;

import com.sodash.jlinkedin.fields.FieldEnum;
import com.sodash.jlinkedin.model.LIProfile;
import com.winterwell.json.JSONObject;
import com.winterwell.utils.StrUtils;
import com.winterwell.utils.Utils;
import com.winterwell.utils.containers.ArrayMap;
import com.winterwell.utils.containers.IOneShot;
import com.winterwell.utils.web.SimpleJson;
import com.winterwell.utils.web.WebUtils2;
import com.winterwell.web.FakeBrowser;

abstract class JLinkedInAPIFacet<SubType extends JLinkedInAPIFacet> implements IOneShot {
	
	protected Map addStdParams(Map vars) {
		if (vars==null) vars = new ArrayMap();
		vars.put("format", "json");
		// count and page: done in the get() override
		return vars;
	}

	FakeBrowser fb = new FakeBrowser();

	public LIProfile getMyProfile() {
		return getProfile(null);
	}

	/**
	 * 
	 * @param url
	 * @param vars Standard parameters will be added for you. Can be null.
	 * @return json
	 */
	String getPage(String url, Map vars) {
		assert jli.authToken != null;
		// add auth header
		fb.setRequestHeader("Authorization", "Bearer "+jli.authToken);
		vars = addStdParams(vars);
		String page = fb.getPage(url, vars);
		assert page != null : url+" "+vars;
		return page;
	}
	
	
	/**
	 * 
	 * @param url
	 * @param vars Standard parameters will be added for you. Can be null.
	 * @return json
	 */
	String post(String url, Map vars) {
		assert jli.authToken != null;
		// add auth header
		fb.setRequestHeader("Authorization", "Bearer "+jli.authToken);
		Map urlvars = addStdParams(null);
		url = WebUtils2.addQueryParameters(url, urlvars);
		Object c = vars.get("content");
		String encodedPostBody = new SimpleJson().toJson(vars);
		fb.setRequestHeader("x-li-format", "json");
		try {
			String page = fb.post(url, "application/json", encodedPostBody);
			JSONObject jobj = new JSONObject(page);		
			return page;
		} catch(Exception ex) {
			throw Utils.runtime(ex);
		}
	}



	public LIProfile getProfile(String id) {
		if (id==null) id = "~";
//		// TODO there's quite a few useful fields you can request! TODO a "get user" method, which tallies with what we
//		// can handle in storePerson2
//		Set<ProfileField> fields = new ArraySet(ProfileField.ID, 
//				ProfileField.FIRST_NAME, ProfileField.LAST_NAME, ProfileField.SUMMARY, ProfileField.PICTURE_URL,
//				ProfileField.DATE_OF_BIRTH);
		
		/*
		 * LI can be a little slow to return this profile, even if the login is accepted. Give it 500ms
		 * and try three times
		 */
		String fieldSel = "";
		if (fields!=null) {
			fieldSel = ":("+StrUtils.join(fields, ",")+")";
		}
		String json = getPage("https://api.linkedin.com/v1/people/"+id+fieldSel, null);		
		JSONObject jobj = new JSONObject(json);
		return new LIProfile(jobj);
	}
	
	JLinkedIn jli;

	FieldEnum[] fields;

	public SubType setFields(FieldEnum... fields) {
		this.fields = fields;		
		return (SubType) this;
	}
	public SubType setFields(Collection<? extends FieldEnum> fields) {
		this.fields = fields.toArray(new FieldEnum[0]);		
		return (SubType) this;
	}

}
