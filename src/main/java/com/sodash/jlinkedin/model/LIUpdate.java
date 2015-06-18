package com.sodash.jlinkedin.model;

import java.util.List;

import com.sodash.jlinkedin.JLinkedInGet;

import winterwell.json.JSONObject;
import winterwell.utils.TodoException;
import winterwell.utils.reporting.Log;
import winterwell.utils.web.SimpleJson;

/**
 * A post / status-update / share. "Update" and "Share" are the terms LinkedIn API docs use. 
 * @author daniel
 *
 */
public class LIUpdate extends LIPostBase {

	public LIUpdate(JSONObject u) {
		super(u);
	}
		

	public final String getImageUrl() {
		String imgUrl = getStatusContentField("submittedImageUrl");
		return imgUrl;
	}

	public LIUpdate(JSONObject jobj, LIPostRequest post, String companyId) {
		this(jobj);
		// fill in data
		if (companyId!=null && getCompany()==null) {
			company = new LICompany(companyId);
		}
		if (post.contents!=null && getContents()==null) {
			setContents(post.contents);
		}
	}
	
	public LIUpdate(LIEvent u) {
		super(new JSONObject());
		throw new TodoException(u);
	}


	@Override
	public LICompany getCompany() {
		LICompany co = super.getCompany();
		if (co!=null) return co;
		JSONObject uc = base.optJSONObject("updateContent");
		if (uc != null) {
			JSONObject jco = uc.optJSONObject("company");
			if (jco!=null) {
				company = new LICompany(jco);
			}
		}
		return company;
	}


	public ListResults<LIComment> getComments() {
		// cf https://developer.linkedin.com/docs/company-pages#company_updates
		JSONObject commentList = base.optJSONObject("updateComments");
		if (commentList==null) return new ListResults<LIComment>();
		return JLinkedInGet.toResults(commentList, LIComment.class);
	}


}
