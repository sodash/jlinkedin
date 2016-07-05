package com.sodash.jlinkedin.model;

import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import winterwell.json.JSONObject;
import com.winterwell.utils.containers.ArrayMap;
import winterwell.utils.web.WebUtils;

import com.sodash.jlinkedin.fields.GroupField;
import com.sodash.jlinkedin.fields.ProfileField;
import com.winterwell.utils.web.WebUtils2;

public class LIGroup extends LIModelBase {

	public LIGroup(String gid, String html) {
		super(new JSONObject(new ArrayMap("id", gid)));		
		List<String> h1s = WebUtils2.extractXmlTags("h1", html, false);
//		Document doc = WebUtils2.parseXml(html);
//		List<Node> nodes = WebUtils2.xpathQuery("//h1[.entity-title]", doc);
//		Node h1 = nodes.get(0);
//		String name = h1.getTextContent();
		if ( ! h1s.isEmpty()) {
			base.put(GroupField.NAME.fieldName(), WebUtils.stripTags(h1s.get(0)));
		}
	}
	

	public LIGroup(JSONObject base) {
		super(base);
	}

	public String getDescription() {
		return base.optString("description");
	}
	
	/**
	 * Equivalent to {@link #getSmallLogoUrl()}
	 * @return
	 */
	public String getImageUrl() {
		return getSmallLogoUrl();
	}
	
	public String getSmallLogoUrl() {
		return base.optString(GroupField.SMALL_LOGO_URL.fieldName());
	}
	public String getLargeLogoUrl() {
		return base.optString(GroupField.LARGE_LOGO_URL.fieldName());
	}


	@Override
	public String getPublicUrl() {
		String url = base.optString(GroupField.SITE_GROUP_URL.fieldName());
		if (url!=null) return url;
		return "https://www.linkedin.com/grp/home?gid="+getId();
	}

	public String getName() {
		return base.optString(GroupField.NAME.fieldName());
	}
}
