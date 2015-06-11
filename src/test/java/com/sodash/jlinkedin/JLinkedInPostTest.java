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
	public void testPostCompanyShareStringBooleanString() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		String socialCutlery = "9750841";
		Object posted = jli.post().postCompanyShare(
			"Today is National Fork Day! Find out about National Fork Day at http://sodash.com!",
			false,
			socialCutlery
			);
		assert posted != null;
	}

	@Test
	public void testPostCompanyShareStringBooleanStringStringStringStringString() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		String socialCutlery = "9750841";
		Object posted = jli.post().postCompanyShare(
			"Today is National Fork Day!",
			false,
			socialCutlery,
			
			"National Fork Day", 
			"Fork day is an important celebration", 
			"http://sodash.com", 
			"http://www.roullierwhite.com/ekmps/shops/roullierwhite/images/maxwell-williams-oyster-fork-123-p.jpg"
			);
		assert posted != null;
	}
	
	@Test
	public void testPostPersonShareStringBoolean() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		Object posted = jli.post().postPersonShare(
			"I'm Spoon McGuffin, and I love it when people visit http://sodash.com!",
			false
			);
		assert posted != null;
	}

	@Test
	public void testPostPersonShareStringBooleanStringStringStringStringString() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		Object posted = jli.post().postCompanyShare(
			"Look at me, I'm Spoon McGuffin!",
			false,
			null,
			
			"Spoon's Homepage", 
			"Don't worry if you don't see Spoon there.", 
			"http://sodash.com", 
			"http://images.fineartamerica.com/images-medium-large-5/spoon-pencil-drawing-andrea-pontillo.jpg"
			);
		assert posted != null;
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
