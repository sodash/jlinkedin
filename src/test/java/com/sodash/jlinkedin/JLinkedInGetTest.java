package com.sodash.jlinkedin;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import winterwell.json.JSONObject;
import com.winterwell.utils.containers.ArrayMap;

import com.sodash.jlinkedin.model.LIComment;
import com.sodash.jlinkedin.model.LICompany;
import com.sodash.jlinkedin.model.LIGroup;
import com.sodash.jlinkedin.model.LIPostBase;
import com.sodash.jlinkedin.model.LIUpdate;
import com.sodash.jlinkedin.model.LIEvent;
import com.sodash.jlinkedin.model.ListResults;

public class JLinkedInGetTest {
	
	String socialCutleryId = "9750841";
	String winterwellId = "326124";
	String forkDayUpdateId = "6014703401818947585";
	String forkDayUpdateKey = "UPDATE-c9750841-6014703401818947585";
	
	@Test
	public void testGetUserCompanies() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		List<LICompany> coms = jli.get().getUserCompanies();
		System.out.println(coms);
		boolean spoonHasSocialCutlery = false;
		for(LICompany lic : coms) {
			if(socialCutleryId.equals(lic.getId())) spoonHasSocialCutlery = true;
		}
		assert(spoonHasSocialCutlery): "Social Cutlery should be one of Spoon's companies!";
	}

	@Test
	public void testGetSharingEnabled() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		boolean socialCutlerySharingEnabled = jli.get().getSharingEnabled(socialCutleryId);
		assert(socialCutlerySharingEnabled): "Social Cutlery should have sharing enabled";
	}
	
	@Test
	public void testGetCanAdminCompany() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		boolean canAdminSocialCutlery = jli.get().getCanAdminCompany(socialCutleryId);
		assert(canAdminSocialCutlery): "Spoon should have admin rights to Social Cutlery";
		// This 403s! Try making Spoon part of a company but not giving it admin rights? 
		// boolean canAdminWinterwell = jli.get().getCanAdminCompany(winterwellId);
		// assert(!canAdminWinterwell): "Spoon should *not* have admin rights to Winterwell";
	}
	
	@Test
	public void testGetCompanyById() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		LICompany lic = jli.get().getCompanyById(socialCutleryId);
		boolean socialCutleryReturned = socialCutleryId.equals(lic.getId());
		assert(socialCutleryReturned): "Request for company with ID " + socialCutleryId + " should have returned company with ID " + socialCutleryId;
	}
	
	@Test
	public void testGetCompanyUpdates() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		List<LIPostBase> updates = jli.get().getCompanyUpdates(socialCutleryId, null);
		assert updates != null;
		LIPostBase u0 = updates.get(0);
		LICompany co0 = u0.getCompany();
		boolean gotUpdates = socialCutleryId.equals(co0.getId());
		assert gotUpdates : updates;
	}
	
	@Test
	public void testGetSelfridgesBug() {
		JLinkedIn jli;
		String token = "AQVs4x771QJkCqHBwVjr6PO30mFVRoSpaFrqBiYssdyQ6jPRFKronz5Duc20ASn7efSIofKk6R28BhlhsQwNBuVFsbk50M8TBRPpYwr9uiUKWUHjRoUU5zvUj2E2AOauiVZQfJi1pPrKDsPXb_Q3cAuLEy_J3-cQjI0UgoO7KFKMmXZs_fs"; 				
		String uk = "UPDATE-c17250-6052839838036561920";
		jli = new JLinkedIn().setAuthToken(token);
		String companyId = "17250";
		ListResults<LIComment> got = jli.get().getCompanyUpdateComments(companyId, uk);
		System.out.println(got);
	}
	
	@Test
	public void testGetCompanyUpdate() {

		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		LIUpdate update = jli.get().getCompanyUpdate(socialCutleryId, forkDayUpdateKey);
		boolean correctUpdate = update != null && forkDayUpdateKey.equals(update.getId());
		assert(correctUpdate): "Didn't get the Fork Day update from a request for the Fork Day update...";
	}
	

	@Test
	public void testGetGroup() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN);
		String gid = "784657";
		LIGroup update = jli.get().getGroupById(gid);
		assert update != null;
		assert update.getId().equals(gid);
	}
	
	@Test
	public void  testGetCompanyUpdateComments() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		List<LIComment> comments = jli.get().getCompanyUpdateComments(socialCutleryId, forkDayUpdateKey);
		boolean gotComments = false;
		if(comments != null) {
			for(LIComment comment: comments) {
				if(comment.getCompany() != null && socialCutleryId.equals(comment.getCompany().getId())) {
					gotComments = true;
				}
			}
		}
		assert(gotComments): "Didn't get a comment by Social Cutlery that should have existed...";
	}
	
	@Test
	public void testGetCompanyUpdateLikes() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		List<LIEvent> likes = jli.get().getCompanyUpdateLikes(socialCutleryId, forkDayUpdateKey);
		boolean gotLikes = false;
		if(likes != null) {
			for(LIEvent like: likes) {
				if(like.getCompany() != null && socialCutleryId.equals(like.getCompany().getId())) gotLikes = true;
			}
		}
		assert(gotLikes): "Didn't get a like by Social Cutlery that should have existed...";
	}
}
