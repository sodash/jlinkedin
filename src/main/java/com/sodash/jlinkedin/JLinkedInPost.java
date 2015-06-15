package com.sodash.jlinkedin;

import java.util.List;
import java.util.Map;

import com.sodash.jlinkedin.fields.VisibilityType;
import com.sodash.jlinkedin.model.LIComment;
import com.sodash.jlinkedin.model.LIPostBase;
import com.sodash.jlinkedin.model.LIPostRequest;
import com.sodash.jlinkedin.model.LIUpdate;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;
import winterwell.utils.containers.ArrayMap;
import winterwell.utils.containers.IOneShot;

/**
 * See https://developer.linkedin.com/docs/share-on-linkedin
 * A single-use object.
 * @author daniel
 *
 */
public class JLinkedInPost extends JLinkedInAPIFacet {

	public JLinkedInPost(JLinkedIn jLinkedIn) {
		this.jli = jLinkedIn;
	}
	

	VisibilityType vis = VisibilityType.ANYONE;
	
	public JLinkedInPost setVisibility(VisibilityType vis) {
		this.vis = vis;
		return this;
	}	
	
	
	/**
	 * Master postShare method
	 * @return
	 */
	LIPostBase post(final LIPostRequest post) {		
		if (post.companyId!=null) {
			if ( ! StrUtils.isInteger(post.companyId)) throw new IllegalArgumentException(post.companyId+" in "+post);
		}
				
		// Which endpoint?
		String jsonUrl;
		if (post.targetId!=null) {
			if (post.companyId ==null) throw new TodoException(post);
			jsonUrl = "https://api.linkedin.com/v1/companies/"+post.companyId+"/updates/key="+post.targetId+"/update-comments-as-company/";
		} else if (post.companyId == null) {
			jsonUrl = "https://api.linkedin.com/v1/people/~/shares";	
		} else {
			jsonUrl = "https://api.linkedin.com/v1/companies/"+post.companyId+"/shares";			
		}						
		
		Map postData = new ArrayMap(	
			"comment", post.contents,
			"visibility", new ArrayMap("code", post.privatePost? VisibilityType.CONNECTIONS_ONLY.value() : VisibilityType.ANYONE.value())				
			);
		
		// Full or Simple (no url) share?
		if (post.url!=null) {
			Map content = new ArrayMap(
					"title", post.title,
					"description", post.description,
					"submitted-url", post.url,
					"submitted-image-url", post.imgUrl);
			postData.put("content", content);
		}
		
		String json = post(jsonUrl, postData);		

		JSONObject jobj = new JSONObject(json);
		// update key: has UPDATE-c{companyid}-{topicid}
		// or UPDATE-{personid}-{topicid}
		LIUpdate m = new LIUpdate(jobj,post);
		return m;
	}


	public LIUpdate postCompanyUpdate(String companyId, String text) {
		LIPostRequest post = new LIPostRequest();
		post.companyId = companyId;
		post.contents = text;
		return (LIUpdate) post(post);
	}


	public LIUpdate postUpdate(LIPostRequest post) {
		return (LIUpdate) post(post);
	}


	public LIUpdate postPersonUpdate(String text) {
		LIPostRequest post = new LIPostRequest();
		post.contents = text;
		return (LIUpdate) post(post);
	}
}
