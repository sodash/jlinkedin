package com.sodash.jlinkedin;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sodash.jlinkedin.fields.FieldEnum;
import com.sodash.jlinkedin.fields.ProfileField;
import com.sodash.jlinkedin.model.LIGroup;
import com.sodash.jlinkedin.model.LIUpdate;
import com.sodash.jlinkedin.model.LIProfile;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;
import winterwell.utils.Utils;
import winterwell.utils.containers.ArrayMap;
import winterwell.utils.containers.ArraySet;
import winterwell.web.FakeBrowser;

/**
 * 
 * See
 * https://apigee.com/console/linkedin
 * 
 * @author daniel
 *
 */
public class JLinkedIn {	
	
	String authToken;

	public JLinkedInPost post() {
		return new JLinkedInPost(this);
	}
	
	public JLinkedIn setAuthToken(String token) {
		this.authToken = token;
		return this;
	}


	public void deletePost(String name) {
		// TODO Auto-generated method stub
		
	}

	public void deletePostComment(String name) {
		// TODO Auto-generated method stub
		
	}

	public JLinkedInGet get() {
		return new JLinkedInGet(this);
	}

	public JLinkedInSearch search() {
		return new JLinkedInSearch(this);
	}


}
