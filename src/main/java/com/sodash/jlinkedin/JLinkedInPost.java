package com.sodash.jlinkedin;

import java.util.List;

import com.sodash.jlinkedin.fields.VisibilityType;

import winterwell.utils.TodoException;
import winterwell.utils.containers.IOneShot;

/**
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

	public void postCompanyShare(String companyId, String comment,
			String title, Object object, String url, Object object2) {
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

	public void postShare(String contents, String title, String description,
			String url, String imgUrl, boolean b) {
		// TODO Auto-generated method stub
		
	}
}
