package com.sodash.jlinkedin;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import winterwell.json.JSONObject;
import winterwell.utils.containers.ArrayMap;

import com.sodash.jlinkedin.model.LICompany;
import com.sodash.jlinkedin.model.LIMessage;
import com.sodash.jlinkedin.model.LIUpdate;
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
		List<LIMessage> updates = jli.get().getCompanyUpdates(socialCutleryId, null, null, null);
		boolean gotUpdates = updates != null && socialCutleryId.equals(updates.get(0).getCompany().getId());
		assert(gotUpdates): "Didn't get Social Cutlery updates from a request for Social Cutlery updates...";
	}
	
	@Test
	public void testGetCompanyUpdate() {

		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		LIMessage update = jli.get().getCompanyUpdate(socialCutleryId, forkDayUpdateKey);
		boolean correctUpdate = update!= null && forkDayUpdateKey.equals(update.getId());
		assert(correctUpdate): "Didn't get the Fork Day update from a request for the Fork Day update...";
	}
	
	@Test
	public void  testGetCompanyUpdateComments() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		List<LIMessage> comments = jli.get().getCompanyUpdateComments(socialCutleryId, forkDayUpdateKey);
		boolean gotComments = false;
		if(comments != null) {
			for(LIMessage comment: comments) {
				if(socialCutleryId.equals(comment.getCompany().getId())) gotComments = true;
			}
		}
		assert(gotComments): "Didn't get a comment by Social Cutlery that should have existed...";
	}
	
	@Test
	public void testGetCompanyUpdateLikes() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		List<LIUpdate> likes = jli.get().getCompanyUpdateLikes(socialCutleryId, forkDayUpdateKey);
		boolean gotLikes = false;
		if(likes != null) {
			for(LIUpdate like: likes) {
				if(socialCutleryId.equals(like.getCompany().getId())) gotLikes = true;
			}
		}
		assert(gotLikes): "Didn't get a like by Social Cutlery that should have existed...";
	}
}
