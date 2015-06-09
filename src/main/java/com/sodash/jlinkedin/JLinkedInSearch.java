package com.sodash.jlinkedin;

import java.util.Map;
import java.util.Set;

import winterwell.utils.TodoException;

import com.sodash.jlinkedin.fields.CompanyField;
import com.sodash.jlinkedin.fields.FacetField;
import com.sodash.jlinkedin.fields.ProfileField;
import com.sodash.jlinkedin.fields.SearchParameter;
import com.sodash.jlinkedin.model.LICompany;
import com.sodash.jlinkedin.model.LIProfile;
import com.sodash.jlinkedin.model.ListResults;

public class JLinkedInSearch extends JLinkedInAPIFacet<JLinkedInSearch> {

	public JLinkedInSearch(JLinkedIn jLinkedIn) {
		this.jli = jLinkedIn;
	}

	public ListResults<LICompany> searchCompanies(
			Map<SearchParameter, String> searchParameters) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

	public ListResults<LIProfile> searchPeople(
			Map<SearchParameter, String> searchParameters,
			Set<ProfileField> pfs, Set<FacetField> ffs) {
		// TODO Auto-generated method stub
		throw new TodoException();
	}

}
