package com.sodash.jlinkedin;

import java.lang.reflect.Constructor;
import java.util.Date;
import java.util.List;
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
import com.sodash.jlinkedin.model.LICompany;
import com.sodash.jlinkedin.model.LIGroup;
import com.sodash.jlinkedin.model.LIGroupMembership;
import com.sodash.jlinkedin.model.LIMessage;
import com.sodash.jlinkedin.model.LIModelBase;
import com.sodash.jlinkedin.model.LIUpdate;
import com.sodash.jlinkedin.model.ListResults;


public class JLinkedInGet extends JLinkedInAPIFacet<JLinkedInGet> {

	private Time since;
	private Time until;
	private int start;
	private int count;

	public JLinkedInGet(JLinkedIn jLinkedIn) {
		this.jli = jLinkedIn;
	}

	public LIGroup getGroupById(String gid) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

	public List<LIMessage> getCompanyUpdates(String dewart) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

	public ListResults<LIMessage> getCompanyUpdateComments(String dewart, String id) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

	public ListResults<LIMessage> getNetworkUpdateComments(String networkUpdateKey) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

	public List<LICompany> getUserCompanies() {
		String json = getPage("https://api.linkedin.com/v1/companies", new ArrayMap("is-company-admin", true));
		ListResults<LICompany> list = toResults(json, LICompany.class);
		return list;
		
	}

	private <LI extends LIModelBase> ListResults<LI> toResults(String json, Class<LI> class1) {
		try {
			JSONObject jobj = new JSONObject(json);
			int total = jobj.optInt("_total");
			JSONArray vs = jobj.getJSONArray("values");
			
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

	public List<LIMessage> getPostComments(String id) {
		throw new TodoException();
	}

	public List<LIMessage>  getPostsByGroup(String name, Set<PostField> postFields,
			int i, int j, PostSortOrder recency, String string, Date date) {
		throw new TodoException();
	}

	public List<LIGroupMembership> getGroupMemberships(String dewart) 
	{
		throw new TodoException();
	}

	public List<LIUpdate> getUserUpdates(Set<NetworkUpdateType> ut) {
		throw new TodoException();
	}

	public List<LIUpdate> getUserUpdates(String id, Set<NetworkUpdateType> ut) {
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

	public com.sodash.jlinkedin.model.LICompany getCompanyById(String string) {
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
