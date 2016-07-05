/**
 * 
 */
package com.sodash.jlinkedin.model;

import winterwell.json.JSONObject;


/**
 * Just for comments, which can only have a creator and some text (URLs aren't extracted).
 * @author daniel
 *
 */
public class LIComment extends LIPostBase {

	public LIComment(JSONObject base) {
		super(base);		
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName()+"["+getContents()+"]";
	}
	
	// This stuff isn't buried in the Comment structure like it is in Updates
	JSONObject getStatus() {
		return base;
	}
	
	@Override
	public String getContents() {
		return base.optString("comment");
	}

	@Override
	public String getId() {
		return base.optString("id");
	}
}
