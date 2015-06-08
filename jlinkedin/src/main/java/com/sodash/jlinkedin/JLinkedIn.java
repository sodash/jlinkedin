package com.sodash.jlinkedin;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sodash.jlinkedin.fields.FieldEnum;
import com.sodash.jlinkedin.fields.ProfileField;
import com.sodash.jlinkedin.model.LIGroup;
import com.sodash.jlinkedin.model.LIMessage;
import com.sodash.jlinkedin.model.LIProfile;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;
import winterwell.utils.Utils;
import winterwell.utils.containers.ArrayMap;
import winterwell.utils.containers.ArraySet;
import winterwell.web.FakeBrowser;

public class JLinkedIn {	
	
	public JLinkedInPost post() {
		return new JLinkedInPost(this);
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
	
	FakeBrowser fb = new FakeBrowser();
	private FieldEnum[] fields;

	public JLinkedIn setAuthToken(String token) {
		// add auth header
		fb.setRequestHeader("Authorization", "Bearer "+token);
		return this;
	}
	
	private String getPage(String url, Map vars) {
		vars = addStdParams(vars);
		String page = fb.getPage(url, vars);
		return page;
	}

	private Map addStdParams(Map vars) {
		if (vars==null) vars = new ArrayMap();
		vars.put("format", "json");
		return vars;
	}

	public LIProfile getMyProfile() {
		return getProfile(null);
	}

	public void setFields(FieldEnum... fields) {
		this.fields = fields;		
	}
	public void setFields(Collection<? extends FieldEnum> fields) {
		this.fields = fields.toArray(new FieldEnum[0]);		
	}


	public LIGroup getGroupById(String gid) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

	public List<LIMessage> getCompanyUpdates(String dewart, int i, int j) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}


	public void deletePost(String name) {
		// TODO Auto-generated method stub
		
	}

	public void deletePostComment(String name) {
		// TODO Auto-generated method stub
		
	}

}
