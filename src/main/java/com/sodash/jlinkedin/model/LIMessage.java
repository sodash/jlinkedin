/**
 * 
 */
package com.sodash.jlinkedin.model;

import java.util.List;

import winterwell.json.JSONObject;
import winterwell.utils.TodoException;
import winterwell.utils.time.Time;

import com.sodash.jlinkedin.fields.MessageType;


/**
 * Unify all the many types of message LI offers
 * @author daniel
 *
 */
public class LIMessage extends LIModelBase {

	private LIUpdate update;

	public LIMessage(JSONObject base) {
		super(base);		
	}
	
	public LIMessage(LIUpdate u) {
		super(u.base);
		this.update = u;
	}

	public String getId() {
		String _id = super.getId();
		if (_id==null) {
			// See https://developer.linkedin.com/documents/commenting-reading-comments-and-likes-network-updates
			// update key: has UPDATE-c{companyid}-{topicid}
			String key = (String) base.get("updateKey");
			// Do we want to use the update key, or pull out the topic-id??
			if (key!=null) return key;
//			id = u.getUpdateKey();
			assert false : this;
		}
		return _id;
	}
	
	MessageType type;
	private String contents;
	
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

	public String getTitle() {
		throw new TodoException(base);
	}
	
	public String getDescription() {
		throw new TodoException(base);
	}
	
	String sharedUrl;
	
	public String getSharedUrl() {
		if (true) throw new TodoException(base);
		return sharedUrl;
	}
	
	public MessageType getType() {
		assert type != null : this;
		return type;
	}

	public String getPublicUrl() {
		throw new TodoException(base);
	}

	public String getContents() {
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


//		public static String getActivityString(Update u) {
//			List<Activity> acts = u.getUpdateContent().getPerson()
//					.getPersonActivities().getActivityList();
//			Activity act = acts.get(0);
//			return act.getBody();
//		}
//
//		public String getCurrentStatusString(Update u) {
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

		
		throw new TodoException(getType()+" "+base);
	}
	
	public Object getAttachments() {
		throw new TodoException(getType()+" "+base);
	}

	public List<LIMessage> getComments() {
		throw new TodoException(getType()+" "+base);
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public LIProfile getCreator() {
		throw new TodoException(getType()+" "+base);
	}

	public Time getCreatedTime() {
		throw new TodoException(getType()+" "+base);
	}

	public com.sodash.jlinkedin.model.LICompany getCompany() {
		throw new TodoException(getType()+" "+base);
	}

}
