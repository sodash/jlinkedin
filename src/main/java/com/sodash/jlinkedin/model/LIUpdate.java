package com.sodash.jlinkedin.model;

import java.util.List;

import com.sodash.jlinkedin.JLinkedInGet;

import winterwell.json.JSONObject;
import winterwell.utils.TodoException;

/**
 * A post / status-update / share. "Update" and "Share" are the terms LinkedIn API docs use. 
 * @author daniel
 *
 */
public class LIUpdate extends LIPostBase {

	public LIUpdate(JSONObject u) {
		super(u);
	}
		

	public LIUpdate(JSONObject jobj, LIPostRequest post) {
		this(jobj);
		// fill in data
		if (post.companyId!=null && getCompany()==null) {
			company = new LICompany(post.companyId);
		}
		if (post.contents!=null && getContents()==null) {
			setContents(post.contents);
		}
	}


	public ListResults<LIComment> getComments() {
		// cf https://developer.linkedin.com/docs/company-pages#company_updates
		JSONObject commentList = base.optJSONObject("updateComments");
		if (commentList==null) return new ListResults<LIComment>();
		return JLinkedInGet.toResults(commentList, LIComment.class);
	}


}
