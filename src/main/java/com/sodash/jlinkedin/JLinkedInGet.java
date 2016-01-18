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
import com.sodash.jlinkedin.model.LIEvent;
import com.sodash.jlinkedin.model.LIGroup;
import com.sodash.jlinkedin.model.LIGroupMembership;
import com.sodash.jlinkedin.model.LIJobPosting;
import com.sodash.jlinkedin.model.LIModelBase;
import com.sodash.jlinkedin.model.LIPostBase;
import com.sodash.jlinkedin.model.LIUpdate;
import com.sodash.jlinkedin.model.ListResults;


public class JLinkedInGet extends JLinkedInAPIFacet<JLinkedInGet> {

//	private Time since;
//	private Time until;
	private int start;
	private int count;

	@Override
	protected Map addStdParams(Map vars) {
		Map map = super.addStdParams(vars);
//		if (since!=null) vars.put();
//		if (until!=null) vars.put();
		if (start!=0) vars.put("start", start);
		if (count!=0) vars.put("count", count);
		return map;
	}
	
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
		String jsonUrl = "https://api.linkedin.com/v1/companies/" + companyId + ":(id,name,logo-url,square-logo-url,locations,num-followers)";
		String json = getPage(jsonUrl, new ArrayMap());
		return new LICompany(new JSONObject(json));
	}
	
	/**
	 * 
	 * @param companyId
	 * @param eventType Can be null
	 * @return
	 */
	public ListResults<LIUpdate> getCompanyUpdates(String companyId, UpdateType eventType) {
		assert companyId!=null;
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates";
		Map params = new ArrayMap();
		if(eventType != null) {
			params.put("event-type", eventType);
		}		
		String json = getPage(jsonUrl, params);
		
		return toResultsAuto(json);

	}
	
	public LIUpdate getCompanyUpdate(String companyId, String updateKey) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+updateKey;
		String json = getPage(jsonUrl, new ArrayMap());
		return new LIUpdate(new JSONObject(json));
	}
	
	public ListResults<LIComment> getCompanyUpdateComments(String companyId, String updateKey) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+updateKey+"/update-comments";
		String json = getPage(jsonUrl, new ArrayMap());
		assert json != null : jsonUrl;
		return toResults(json, LIComment.class);
	}
	
	public ListResults<LIEvent> getCompanyUpdateLikes(String companyId, String updateKey) {
		String jsonUrl = "https://api.linkedin.com/v1/companies/"+companyId+"/updates/key="+updateKey+"/likes";
		String json = getPage(jsonUrl, new ArrayMap());
		return toResults(json, LIEvent.class);
	}


	public LIGroup getGroupById(String gid) {
		String html = getPage("https://www.linkedin.com/grp/home", new ArrayMap("gid", gid));
		return new LIGroup(gid, html);
	}
	
	public static <LI extends LIModelBase> ListResults<LI> toResults(String json, Class<LI> class1) {
		assert json != null;
		if (json==null || "null".equals(json)) {
			return new ListResults();
		}
		return toResults(new JSONObject(json), class1);		
	}
	
	public static <LI extends LIModelBase> ListResults<LI> toResults(JSONObject jobj, Class<LI> class1) {
		try {			
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
	
	/**
	 * Turns a JSON list, known to be a mix of status-updates and job-postings, into a list of LIUpdates and LIJobPostings.
	 * @param json
	 * @return
	 */
	public static ListResults<LIModelBase> toResults(String json) {
		assert json != null;
		if (json==null || "null".equals(json)) {
			return new ListResults();
		}
		return toResultsAuto(new JSONObject(json));		
	}
	
	public static ListResults<LIModelBase> toResultsAuto(JSONObject jobj) {
		try {			
			int total = jobj.optInt("_total");
			JSONArray vs = jobj.optJSONArray("values");
			if (vs == null) {
				assert total == 0;
				return new ListResults<LIModelBase>();
			}
			
			ListResults<LIModelBase> list = new ListResults<LIModelBase>();
			list.setTotal(total);
			Constructor<LIUpdate> updateCons = LIUpdate.class.getConstructor(JSONObject.class);
			Constructor<LIJobPosting> jobCons = LIJobPosting.class.getConstructor(JSONObject.class);
			
			for(JSONObject v : vs) {
				JSONObject content = v.optJSONObject("updateContent");
				if (content == null) continue;
				if (content.has("companyStatusUpdate")) list.add(updateCons.newInstance(v));
				else if (content.has("companyJobUpdate")) list.add(jobCons.newInstance(v));
			}
			return list;
		} catch(Exception ex) {
			throw Utils.runtime(ex);
		}
	}

	public ListResults<LIComment> getPostComments(String id) {
		throw new TodoException();
	}

	public List<LIPostBase>  getPostsByGroup(String groupId, PostSortOrder recency, String type) {
		throw new TodoException();
	}

	public List<LIGroupMembership> getGroupMemberships(String dewart) 
	{
		throw new TodoException();
	}

	public List getUserUpdates(Set<NetworkUpdateType> ut) {
		throw new TodoException();
	}

	public List getUserUpdates(String id, Set<NetworkUpdateType> ut) {
		throw new TodoException();
	}

	/**
	 * @deprecated I don't think LinkedIn support this anymore :(
	 * @param since
	 * @return
	 */
	public JLinkedInGet setSince(Time since) {
//		this.since = since;
		return this;
	}
	
	/**
	 * @deprecated I don't think LinkedIn support this anymore :(
	 * @param until
	 * @return
	 */
	public JLinkedInGet setUntil(Time until) {
//		this.until = until;
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

	public ListResults<LIComment> getNetworkUpdateComments(
			String networkUpdateKey) {
		throw new TodoException(networkUpdateKey);
	}

}
