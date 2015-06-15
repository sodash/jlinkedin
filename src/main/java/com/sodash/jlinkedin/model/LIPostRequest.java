package com.sodash.jlinkedin.model;

/**
 * A comment / share / post you wish to make.
 * @author daniel
 *
 */
public class LIPostRequest {

	/**
	 * Title of post, max 200 chars, can be null (will do simple-post)
	 */
	public String title;
	/**
	 * Description of URL, max 256 chars, can be null (will do simple-post)
	 */
	public String description;
	/**
	 * The url to share
	 */
	public String url;
	public String imgUrl;
	
	 /**
	  * Privacy, true: Connections Only, false: Anyone
	  */
	public boolean privatePost;
	
	/**
	 * NB: This is called "comment" in the API docs
	 */
	public String contents;
	
	/**
	 * For comments: From the update you are commenting on.
	 */
	public String targetId;
	

}
