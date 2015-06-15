package com.sodash.jlinkedin;

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

	public JLinkedInPost postAsPerson() {
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

	public JLinkedInPost postAsCompany(String companyId) {
		return new JLinkedInPost(this, companyId);
	}


}
