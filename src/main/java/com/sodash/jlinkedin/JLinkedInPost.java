package com.sodash.jlinkedin;

import java.util.List;
import java.util.Map;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;

import com.sodash.jlinkedin.fields.VisibilityType;
import com.sodash.jlinkedin.model.LIComment;
import com.sodash.jlinkedin.model.LIPostBase;
import com.sodash.jlinkedin.model.LIPostRequest;
import com.sodash.jlinkedin.model.LIUpdate;
import com.winterwell.utils.containers.ArrayMap;

/**
 * See https://developer.linkedin.com/docs/share-on-linkedin
 * A single-use object.
 * @author daniel
 *
 */
public class JLinkedInPost extends JLinkedInAPIFacet {


	/**
	 * Can be null (will post as person)
	 */
	private String companyId;

	public JLinkedInPost(JLinkedIn jLinkedIn) {
		this.jli = jLinkedIn;
	}
	

	public JLinkedInPost(JLinkedIn jLinkedIn, String companyId) {
		this.jli = jLinkedIn;
		this.companyId = companyId;
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
		if (companyId!=null) {
			if ( ! StrUtils.isInteger(companyId)) throw new IllegalArgumentException(companyId+" in "+post);
		}
				
		// Which endpoint?
		String jsonUrl;
		if (post.targetId!=null) {
			if (companyId ==null) throw new TodoException(post);
			jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+post.targetId+"/update-comments-as-company/";
		} else if (companyId == null) {
			jsonUrl = "https://api.linkedin.com/v1/people/~/shares";	
		} else {
			jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/shares";			
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
		LIUpdate m = new LIUpdate(jobj,post,companyId);
		return m;
	}



	public LIUpdate postUpdate(LIPostRequest post) {
		return (LIUpdate) post(post);
	}


	public LIUpdate postUpdate(String text) {
		LIPostRequest post = new LIPostRequest();
		post.contents = text;
		return (LIUpdate) post(post);
	}


	public LIComment postComment(String updateId, String contents) {
		LIPostRequest post = new LIPostRequest();
		post.contents = contents;
		post.targetId = updateId;
		return (LIComment) post(post);
	}


	public void postToGroup(String dewart, LIPostRequest post) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}


	public void sendMessage(List<String> recepientIds, String name,
			String contents) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}
}
