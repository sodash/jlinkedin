package com.sodash.jlinkedin.model;

import java.util.List;

import winterwell.json.JSONObject;
import winterwell.utils.StrUtils;
import winterwell.utils.TodoException;
import winterwell.utils.time.Time;

public abstract class LIPostBase extends LIModelBase {

	public LIPostBase(JSONObject base) {
		super(base);
	}

	/**
	 * Get the message's ID or Update Key
	 * Comments have property "id"
	 * Updates (aka Shares) have property "updateKey"
	 */
	public final String getId() {
		if(this.base.has("id")) {			
			return super.getId();
		} else if(this.base.has("updateKey")) {
			assert ! (this instanceof LIComment);
			return this.base.getString("updateKey");
		} else return null;
	}
	
	public String toString() {
		return getClass().getSimpleName()+"["+StrUtils.joinWithSkip(" ", getTitle(), getDescription(), getContents(), getSharedUrl())+"]";
	}
	
	String contents;
	
//	/**
//	 * Set contents & stuff from a Content object
//	 * @param con
//	 */
//	private void setFromContent(Content con) {
//		String id2 = con.getId();
//		url = con.getResolvedUrl();
//		if (url==null) url = con.getSubmittedUrl();
//		imgUrl = con.getSubmittedImageUrl();
//		title = con.getTitle();
//		desc = con.getDescription();
//	//	assert Utils.isBlank(contents) : contents+" in "+this;
//		contents = StrUtils.join(Arrays.asList(url, imgUrl, desc), "\n");
//		if ( ! Utils.equals(id2, id)) {
//			System.out.println("ID CLASH:\t"+id+"\tvs\t"+id2);
//		}
//	}

	public final String getTitle() {
		JSONObject jc = getStatus();
		if (jc!=null) {
			return jc.optString("title");
		}
		throw new TodoException(base);
	}
	
	JSONObject getStatus() {
		JSONObject uc = base.optJSONObject("updateContent");
		if (uc==null) return null;
		JSONObject js = uc.optJSONObject("companyStatusUpdate");
		if (js==null) {
			js = uc.optJSONObject(""); // TODO
			if (js==null) return null;
		}
		JSONObject js2 = js.optJSONObject("share");
		if (js2!=null) return js2;
		return js;
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
	
	String sharedUrl;
	
	public final String getSharedUrl() {
		if (sharedUrl!=null) return sharedUrl;
		sharedUrl = getStatusContentField("submittedImageUrl");
		return sharedUrl;
	}
	
	String getStatusContentField(String field) {
		JSONObject js = getStatus();
		if (js==null) return null;
		JSONObject jc = js.optJSONObject("content");
		if (jc==null) return null;
		String v = jc.optString(field);
		return v;
	}

	Object type;
	
	public final Object getType() {
		assert type != null : this;
		return type;
	}

	public final String getPublicUrl() {
		throw new TodoException(base);
	}

	public final String getContents() {
//		CompanyJobUpdate cju = uc.getCompanyJobUpdate();
//		CompanyPersonUpdate cpu = uc.getCompanyPersonUpdate();		
//		CompanyStatusUpdate csu = uc.getCompanyStatusUpdate();
//		UpdateAction action = uc.getUpdateAction();
//		if (csu!=null && csu.getShare()!=null) {
//			Share s = csu.getShare();
//			contents = s.getComment();
//			// TODO are we going to need these sub-ids?
//			// this.id = s.getId();
//			Content con = s.getContent();
//			if (con != null){
//			setFromContent(con);
//			}
//		}
//		CompanyProfileUpdate cpu2 = uc.getCompanyProfileUpdate();				


//		public final static String getActivityString(Update u) {
//			List<Activity> acts = u.getUpdateContent().getPerson()
//					.getPersonActivities().getActivityList();
//			Activity act = acts.get(0);
//			return act.getBody();
//		}
//
//		public final String getCurrentStatusString(Update u) {
//			String status = u.getUpdateContent().getPerson().getCurrentStatus();
//			return status;
//		}
//		if (type != null && type.equals(NetworkUpdateReturnType.APPLICATION_CONNECTION_UPDATE)){
//			contents = LIMailClient.getActivityString(u);
//			return;
//		}
//		contents = StrUtils.join(Arrays.asList(title, url, desc), " ");
//		
//		if (Utils.isBlank(contents)) {
//			
//			
//			if (type == NetworkUpdateReturnType.STATUS_UPDATED) {
//				contents = person1.getCurrentStatus();
//			} else if (type == NetworkUpdateReturnType.SHARED_ITEM) {
//				CurrentShare cs = person1.getCurrentShare();
//				Content s = cs.getContent();
//				if (s != null)
//					setFromContent(s);
//				else {
//					contents = cs.getComment();
//				}
//			} else if (person1 != null
//					&& person1.getRecommendationsGiven() != null
//					&& person1.getRecommendationsGiven().getTotal() != 0) {
//				// Recommendees?
//				RecommendationsGiven recommendations = u.getUpdateContent()
//						.getPerson().getRecommendationsGiven();
//				// FIXME!!!
//				contents = XStreamUtils.serialiseToXml(recommendations);
//			} else {
//				// fail
//			}
//		}

		
		return contents;
	}
	
	public final Object getAttachments() {
		throw new TodoException(getType()+" "+base);
	}

	public final void setContents(String contents) {
		this.contents = contents;
	}

	/**
	 * Non-partner API calls can only return status updates from companies, so
	 * this should always yield an LICompany when called on an Update.
	 * Comments can be made by Companies or People - if called on a Comment
	 * made by a Person, this will return null.
	 * @return
	 */
	public final LIProfile getCreator() {
		if( ! this.base.has("person")) return null;
		return new LIProfile(this.base.getJSONObject("person"));
	}	

	public final Time getCreatedTime() {
		if(!this.base.has("timestamp")) return null;
		return new Time(this.base.getLong("timestamp"));
	}
	
	LICompany company;
	
	/**
	 * Non-partner API calls can only return status updates from companies, so
	 * this should always yield an LICompany when called on an Update.
	 * Comments can be made by Companies or People - if called on a Comment
	 * made by a Person, this will return null.
	 * @return
	 */
	public LICompany getCompany() {
		if (company!=null) return company;		
		if( ! this.base.has("company")) return null;
		return new LICompany(this.base.getJSONObject("company"));
	}


}
