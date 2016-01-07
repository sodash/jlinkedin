package com.sodash.jlinkedin.model;

import java.util.List;

import com.sodash.jlinkedin.fields.NetworkUpdateReturnType;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;
import winterwell.utils.reporting.Log;
import winterwell.utils.time.Time;

public abstract class LIPostBase extends LIModelBase {

	public LIPostBase(JSONObject base) {
		super(base);
	}

	/**
	 * Get the message's ID
	 * Comments have property "id"
	 * Updates (aka Shares) have property "updateKey"
	 */
	public abstract String getId();
	
	/**
	 * Update key is of the form UPDATE-c9750841-6014790980211867648
	 * or UPDATE-companyid-updateid
	 * @return
	 */
	public final String getUpdateKey() {
		return this.base.optString("updateKey");
	}
	
	public abstract String toString();
	
	String contents;
	

	JSONObject getStatus() {
		JSONObject uc = base.optJSONObject("updateContent");
		if (uc==null) {
			Log.d("linkedin", "getStatus no updateContent in "+base.toString());
			return null;
		}
		JSONObject js = uc.optJSONObject("companyStatusUpdate");
		if (js==null) {
			Log.d("linkedin", "getStatus no csu in " + base.toString());
//			js = uc.optJSONObject(""); // TODO
//			if (js==null) 
			return null;
		}
		JSONObject js2 = js.optJSONObject("share");
		if (js2!=null) return js2;
		return js;
	}
	
	JSONObject getJob() {
		JSONObject uc = base.optJSONObject("updateContent");
		if (uc==null) {
			Log.d("linkedin", "getStatus no updateContent in " + base.toString());
			return null;
		}
		JSONObject js = uc.optJSONObject("companyJobUpdate");
		if (js==null) {
			Log.d("linkedin", "getJob no companyJobUpdate in " + base.toString());
			return null;
		}
		return js.optJSONObject("job");
	}

	Object type;
	
	public final Object getType() {
		if(type == null) {
			try {
				type = NetworkUpdateReturnType.fromValue(base.getString("updateType"));	
			} catch (IllegalArgumentException e) {}
		}
		assert type != null : this;
		return type;
	}

	public String getPublicUrl() {
		return null;
	}
	
	public static String publicUrlForId(String id) {
		return "//www.linkedin.com/nhome/updates?topic=" + id;
	}

	/**
	 * Returning null is OK here - when posting we synthesise an LIPostBase or derived class instead of constructing from JSON.
	 * @return
	 */
	public String getContents() {
		if(contents != null) return contents;
		if(getStatus() != null) {
			String comment = getStatus().optString("comment");
			return comment;
		}
		return null;
	}

	public final void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * Can be null
	 * @return
	 */
	public final Time getCreatedTime() {		
		if (base.has("timestamp")) {
			return new Time(this.base.getLong("timestamp"));
		}
		JSONObject s = this.getStatus();
		if (s!=null && s.has("timestamp")) {
			return new Time(s.getLong("timestamp"));
		}
		Log.d("linkedin", "No timestamp for "+getClass()+" "+getId());
		return null;
	}
	
	LICompany company;
	LIProfile person;
	
	public final LIProfile getCreator() {
		if (person != null) return person;
		if( ! this.base.has("person")) return null;
		return new LIProfile(this.base.getJSONObject("person"));
	}	
	
	/**
	 * Non-partner API calls can only return status updates from companies, so
	 * this should always yield an LICompany when called on an Update.
	 * Comments can be made by Companies or People - if called on a Comment
	 * made by a Person, this will return null.
	 * @return
	 */
	public final LICompany getCompany() {
		if (company != null) return company;		
		if( ! this.base.has("company")) return null;
		return new LICompany(this.base.getJSONObject("company"));
	}
	
	/**
	 * Classes that can have explicit shared-content information should override this
	 * @return
	 */
	public JSONObject getAttachment() {
		return null;
	}



}
