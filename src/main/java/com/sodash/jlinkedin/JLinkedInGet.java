package com.sodash.jlinkedin;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import winterwell.json.JSONArray;
import winterwell.json.JSONObject;
import winterwell.utils.TodoException;
import winterwell.utils.Utils;
import winterwell.utils.containers.ArrayMap;
import winterwell.utils.time.Time;

import com.sodash.jlinkedin.fields.CompanyField;
import com.sodash.jlinkedin.fields.GroupMembershipField;
import com.sodash.jlinkedin.fields.NetworkUpdateType;
import com.sodash.jlinkedin.fields.PostField;
import com.sodash.jlinkedin.fields.PostSortOrder;
import com.sodash.jlinkedin.fields.UpdateType;
import com.sodash.jlinkedin.model.LIComment;
import com.sodash.jlinkedin.model.LICompany;
import com.sodash.jlinkedin.model.LIGroup;
import com.sodash.jlinkedin.model.LIGroupMembership;
import com.sodash.jlinkedin.model.LIUpdate;
import com.sodash.jlinkedin.model.LIModelBase;
import com.sodash.jlinkedin.model.LIEvent;
import com.sodash.jlinkedin.model.ListResults;


public class JLinkedInGet extends JLinkedInAPIFacet<JLinkedInGet> {

	private Time since;
	private Time until;
	private int start;
	private int count;

	public JLinkedInGet(JLinkedIn jLinkedIn) {
		this.jli = jLinkedIn;
	}
	
	public boolean getSharingEnabled(String companyId) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/is-company-share-enabled";
		String json = getPage(jsonUrl, new ArrayMap());
		return "true".equalsIgnoreCase(json);
	}
	
	public boolean getCanAdminCompany(String companyId) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/relation-to-viewer/is-company-share-enabled";
		String json = getPage(jsonUrl, new ArrayMap());
		return "true".equalsIgnoreCase(json);
	}
	
	public List<LICompany> getUserCompanies() {
		String json = getPage("https://api.linkedin.com/v1/companies", new ArrayMap("is-company-admin", true));
		ListResults<LICompany> list = toResults(json, LICompany.class);
		return list;	
	}
	
	public LICompany getCompanyById(String companyId) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId;
		String json = getPage(jsonUrl, new ArrayMap());
		return new LICompany(new JSONObject(json));
	}
	
	public ListResults<LIUpdate> getCompanyUpdates(String companyId, UpdateType eventType, Integer startPage, Integer count) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates";
		Map params = new ArrayMap();
		if(eventType != null) {
			params.put("event-type", eventType);
		}
		
		if(startPage != null) {
			params.put("start", startPage);
		}
		
		if(count != null) {
			params.put("count", count);
		}
		String json = getPage(jsonUrl, params);
		return toResults(json, LIUpdate.class);
	}
	
	public LIUpdate getCompanyUpdate(String companyId, String updateKey) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+updateKey;
		String json = getPage(jsonUrl, new ArrayMap());
		return new LIUpdate(new JSONObject(json));
	}
	
	public ListResults<LIComment> getCompanyUpdateComments(String companyId, String updateKey) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+updateKey+"/update-comments";
		String json = getPage(jsonUrl, new ArrayMap());
		return toResults(json, LIComment.class);
	}
	
	public ListResults<LIEvent> getCompanyUpdateLikes(String companyId, String updateKey) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+updateKey+"/likes";
		String json = getPage(jsonUrl, new ArrayMap());
		return toResults(json, LIEvent.class);
	}


	public LIGroup getGroupById(String gid) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}
	
	public ListResults<LIUpdate> getNetworkUpdateComments(String networkUpdateKey) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}



	private <LI extends LIModelBase> ListResults<LI> toResults(String json, Class<LI> class1) {
		try {
			JSONObject jobj = new JSONObject(json);
			int total = jobj.optInt("_total");
			JSONArray vs = jobj.optJSONArray("values");
			if (vs==null) {
				assert total==0;
				return new ListResults();
			}
			
			ListResults list = new ListResults();
			list.setTotal(total);
			Constructor<LI> cons = class1.getConstructor(JSONObject.class);
			for(JSONObject v : vs) {
				LI obj = cons.newInstance(v);
				list.add(obj);
			}
			return list;
		} catch(Exception ex) {
			throw Utils.runtime(ex);
		}
	}

	public List<LIUpdate> getPostComments(String id) {
		throw new TodoException();
	}

	public List<LIUpdate>  getPostsByGroup(String name, Set<PostField> postFields,
			int i, int j, PostSortOrder recency, String string, Date date) {
		throw new TodoException();
	}

	public List<LIGroupMembership> getGroupMemberships(String dewart) 
	{
		throw new TodoException();
	}

	public List<LIEvent> getUserUpdates(Set<NetworkUpdateType> ut) {
		throw new TodoException();
	}

	public List<LIEvent> getUserUpdates(String id, Set<NetworkUpdateType> ut) {
		throw new TodoException();
	}

	public JLinkedInGet setSince(Time since) {
		this.since = since;
		return this;
	}
	public JLinkedInGet setUntil(Time since) {
		this.until = since;
		return this;
	}

	public LICompany getCompanyByUniversalName(String co) {
		throw new TodoException();
	}


	/**
	 * 
	 * @param start Usually 0 (unless you want the 2nd page of results)
	 * @return
	 */
	public JLinkedInGet setResultRange(int start, int count) {
		this.start = start;
		this.count = count;
		return this;
	}

}
