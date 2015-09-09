package com.sodash.jlinkedin.model;

import java.util.List;

import com.sodash.jlinkedin.JLinkedInGet;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;
import winterwell.utils.reporting.Log;
import winterwell.utils.web.SimpleJson;

/**
 * A post / status-update / share. "Update" and "Share" are the terms LinkedIn API docs use. 
 * @author daniel
 *
 */
public class LIUpdate extends LIPostBase {
	
	String sharedUrl;

	public LIUpdate(JSONObject u) {
		super(u);
	}
	
	public LIUpdate(LIModelBase u) {
		super(u.base);
	}
	
	public String toString() {
		return getClass().getSimpleName()+"["+StrUtils.joinWithSkip(" ", getTitle(), getDescription(), getContents(), getSharedUrl())+"]";
	}
	
	public LIUpdate(JSONObject jobj, LIPostRequest post, String companyId) {
		this(jobj);
		// fill in data
		if (companyId != null && getCompany() == null) {
			company = new LICompany(companyId);
		}
		if (post.contents != null && getContents() == null) {
			setContents(post.contents);
		}
	}
	
	public LIUpdate(LIEvent u) {
		super(new JSONObject());
		throw new TodoException(u);
	}


	
	
	@Override
	public String getPublicUrl() {
		return LIPostBase.publicUrlForId(getId());
	}

	public ListResults<LIComment> getComments() {
		// cf https://developer.linkedin.com/docs/company-pages#company_updates
		JSONObject commentList = base.optJSONObject("updateComments");
		if (commentList==null) return new ListResults<LIComment>();
		return JLinkedInGet.toResults(commentList, LIComment.class);
	}
	
	/**
	 * @return a JSON blob with the shared URL, image, title, description
	 */
	public JSONObject getAttachment() {
		JSONObject js = getStatus();
		if (js==null) return null;
		return js.optJSONObject("content");
	}
	
	/**
	 * Returns updateContent.companyStatusUpdate.share.content[field]
	 * @param field
	 * @return
	 */
	String getStatusContentField(String field) {
		JSONObject js = getStatus();
		if (js==null) return null;
		JSONObject jc = js.optJSONObject("content");
		if (jc==null) return null;
		String v = jc.optString(field);
		return v;
	}
	public final String getTitle() {
		return getStatusContentField("title");
	}

	public final String getDescription() {
		JSONObject js = getStatus();
		if (js==null) return null;
		JSONObject jc = js.optJSONObject("content");
		if (jc != null) {
			String d = jc.optString("description");
			return d;
		}
		return null;
	}
	
	public final String getSharedUrl() {
		if (sharedUrl!=null) return sharedUrl;
		sharedUrl = getStatusContentField("submittedUrl");
		return sharedUrl;
	}
	
	public final String getImageUrl() {
		String imgUrl = getStatusContentField("submittedImageUrl");
		if(imgUrl == null) imgUrl = getStatusContentField("thumbnailUrl"); // fallback to LI-hosted thumbnail
		return imgUrl;
	}
	

	@Override
	public String getId() {
		return extractIdFromUpdateKey(this.getUpdateKey());
	}
	
	/**
	 * ID not explicitly given as part of an Update - have to extract it from the update key,
	 * which looks like "UPDATE-companyid-updateid"
	 */
	public static String extractIdFromUpdateKey(String key) {
		if(key == null) return null;
		String[] keyBits = key.split("-");
		if (keyBits.length > 2) return keyBits[2];
		else return null;
	}
}
