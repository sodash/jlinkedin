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
		throw new TodoException(base);
	}
	
	public final String getDescription() {
		throw new TodoException(base);
	}
	
	String sharedUrl;
	
	public final String getSharedUrl() {
		if (true) throw new TodoException(base);
		return sharedUrl;
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
	public final LICompany getCompany() {
		if (company!=null) return company;
		if( ! this.base.has("company")) return null;
		return new LICompany(this.base.getJSONObject("company"));
	}


}
