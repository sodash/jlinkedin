package com.sodash.jlinkedin;

import java.util.List;

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
	
	public void postComment(String updateId, String contents) {
		throw new TodoException();
	}
	

	public void sendMessage(List<String> recepientIds, String title, String contents) {
		throw new TodoException();
	}
	

	public void postCompanyShare(String companyId, String comment) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

	public void addPostComment(String name, String postMe) {
		// TODO Auto-generated method stub
		
	}

	public void createPost(String dewart, String name, String contents) {
		// TODO Auto-generated method stub
		
	}

	public void postShare(String contents) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param contents
	 * @param title 200 chars
	 * @param description 256 chars
	 * @param url
	 * @param imgUrl
	 * @param privatePost
	 */
	public LIMessage postShare(String contents, String title, String description,
			String url, String imgUrl, boolean privatePost) {
		String json = getPage("https://api.linkedin.com/v1/people/~/shares", new ArrayMap(
				"title", title,
				"description", description,
				"submitted-url", url,
				"submitted-image-url", imgUrl,
				"comment", contents,
				"visibility", privatePost? VisibilityType.CONNECTIONS_ONLY : VisibilityType.ANYONE				
				));
		JSONObject jobj = new JSONObject(json);
		throw new TodoException(jobj);
	}
	

	public Object postCompanyShare(String companyId, String title,
			String description, String contents, String url, String imgUrl, boolean privatePost) 
	{
		String json = post("https://api.linkedin.com/v1/companies/"+companyId+"/shares", new ArrayMap(
				"content", new ArrayMap(
						"title", title,
						"description", description,
						"submitted-url", url,
						"submitted-image-url", imgUrl),						
				"comment", contents,
				"visibility", new ArrayMap("code", privatePost? VisibilityType.CONNECTIONS_ONLY.value() : VisibilityType.ANYONE.value())				
				));
		JSONObject jobj = new JSONObject(json);
		// update key: has UPDATE-c{companyid}-{topicid}
		LIMessage m = new LIMessage(jobj);
		return m;
	}

}
