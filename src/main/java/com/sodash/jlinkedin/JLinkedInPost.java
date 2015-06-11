package com.sodash.jlinkedin;

import java.util.List;
import java.util.Map;

import com.sodash.jlinkedin.fields.VisibilityType;
import com.sodash.jlinkedin.model.LIMessage;

import winterwell.json.JSONObject;
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
	
	public LIMessage postComment(String companyId, String updateId, String comment) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+updateId+"/update-comments-as-company/";
			
		Map postData = new ArrayMap(	
			"comment", comment
			);
		
		String json = post(jsonUrl, postData);
		

		JSONObject jobj = new JSONObject(json);
		// update key: has UPDATE-c{companyid}-{topicid}
		// or UPDATE-{personid}-{topicid}
		LIMessage m = new LIMessage(jobj);
		return m;
	}
	

	public void sendMessage(List<String> recepientIds, String title, String contents) {
		throw new TodoException();
	}
	



	public void addPostComment(String name, String postMe) {
		// TODO Auto-generated method stub
		
	}

	public void createPost(String dewart, String name, String contents) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Wrapper for {@link postShare(String, boolean, String, Map) postShare} with null companyId.
	 */
	public LIMessage postPersonShare(String comment, boolean privatePost) {
		return postShare(comment, privatePost, null, null);
	}
	
	/**
	 * Wrapper for {@link postShare(String, boolean, String, Map) postShare} for clarity purposes.
	 */	
	public LIMessage postCompanyShare(String comment, boolean privatePost, String companyId) {
		return postShare(comment, privatePost, companyId, null);		
	}

	/**
	 * Wrapper for {@link postShare(String, boolean, String, String, String, String, String) postShare} with null companyId.
	 */
	public LIMessage postPersonShare(String comment, boolean privatePost,
			String title, String description, String url, String imgUrl) {
		return postShare(comment, privatePost, null, title, description, url, imgUrl);
	}
	
	/**
	 * Wrapper for {@link postShare(String, boolean, String, String, String, String, String)  postShare} for clarity purposes.
	 */
	public LIMessage postCompanyShare(String comment, boolean privatePost, String companyId,
			String title, String description, String url, String imgUrl) {
		return postShare(comment, privatePost, companyId, title, description, url, imgUrl);
	}
	
	/**
	 * Bundle explicit content params into "content" map for master method.
	 * @param comment The comment text
	 * @param privatePost Privacy, true: Connections Only, false: Anyone
	 * @param companyId Can be null (will post as person)
	 * @param title Title of post, max 200 chars, can be null (will do simple-post)
	 * @param description Description of URL, max 256 chars, can be null (will do simple-post)
	 * @param url Can be null (will do simple-post)
	 * @param imgUrl Can be null (will do simple-post)
	 * @return
	 */
	private LIMessage postShare(String comment, boolean privatePost, String companyId, 
			String title, String description, String url, String imgUrl) {
		Map content = null;
		if(title != null && description != null && url != null && imgUrl != null) {
			content = new ArrayMap(
				"title", title,
				"description", description,
				"submitted-url", url,
				"submitted-image-url", imgUrl);	
		}

		return postShare(comment, privatePost, companyId, content);
	}

	/**
	 * Master postShare method
	 * @param comment The comment text
	 * @param privatePost Privacy, true: Connections Only, false: Anyone
	 * @param companyId Can be null (will post as person)
	 * @param content A Map containing title, description, submitted-url, submitted-image-url (can be null, will do simple post)
	 * @return
	 */
	private LIMessage postShare(String comment, boolean privatePost, String companyId, Map content) {
		String jsonUrl = companyId == null? 
			"https://api.linkedin.com/v1/people/~/shares" :
			"https://api.linkedin.com/v1/companies/"+companyId+"/shares";
		
		Map postData = new ArrayMap(	
			"comment", comment,
			"visibility", new ArrayMap("code", privatePost? VisibilityType.CONNECTIONS_ONLY.value() : VisibilityType.ANYONE.value())				
			);
		
		if(content != null) {
			postData.put("content", content);
		}
		
		String json = post(jsonUrl, postData);
		

		JSONObject jobj = new JSONObject(json);
		// update key: has UPDATE-c{companyid}-{topicid}
		// or UPDATE-{personid}-{topicid}
		LIMessage m = new LIMessage(jobj);
		return m;
	}
}
