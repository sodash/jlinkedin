package com.sodash.jlinkedin;

import static org.junit.Assert.*;

import org.junit.Test;

public class JLinkedInPostTest {

	@Test
	public void testPostComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendMessage() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostCompanyShareStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostCompanyShareStringStringStringObjectStringObject() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		String socialCutlery = "9750841";
		Object posted = jli.post().postCompanyShare(socialCutlery, 
					"Today is National Fork Day!", 
					"Fork day is an important celebration", 
					"National Fork Day",
					"http://sodash.com", 
					"http://www.roullierwhite.com/ekmps/shops/roullierwhite/images/maxwell-williams-oyster-fork-123-p.jpg", 
					false);
	}

	@Test
	public void testAddPostComment() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreatePost() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostShareString() {
		fail("Not yet implemented");
	}

	@Test
	public void testPostShareStringStringStringStringStringBoolean() {
		fail("Not yet implemented");
	}

}
