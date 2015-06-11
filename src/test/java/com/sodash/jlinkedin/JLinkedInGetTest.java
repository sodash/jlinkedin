package com.sodash.jlinkedin;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.sodash.jlinkedin.model.LICompany;

public class JLinkedInGetTest {

	@Test
	public void testGetUserCompanies() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		List<LICompany> coms = jli.get().getUserCompanies();
		System.out.println(coms);
	}

}
