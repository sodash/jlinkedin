package com.sodash.jlinkedin;

import static org.junit.Assert.*;

import org.junit.Test;

import winterwell.utils.Utils;

import com.sodash.jlinkedin.model.LIPostRequest;
import com.sodash.jlinkedin.model.LIUpdate;

public class JLinkedInPostTest {

	String socialCutlery = "9750841";
	
	@Test
	public void testPostCompanySimpleShare() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		
		LIUpdate posted = jli.post().postCompanyUpdate(
			socialCutlery,
			"Today is National Fork Day! Find out about National Fork Day at http://sodash.com!"		
			);
		
		assert posted != null;
		assert posted.getId() != null;
		assert posted.getContents().startsWith("Today is National Fork Day");
		assert posted.getCompany() != null;
		assert posted.getCompany().getId().equals(socialCutlery);
	}

	@Test
	public void testPostCompanyShare() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		LIPostRequest post = new LIPostRequest();
		String salt = Utils.getRandomString(4);
		post.title = "National Fork Day "+salt;
		post.contents ="Today is National Fork Day! "+salt;
		post.companyId = socialCutlery;		
		post.description = "Fork day is an important celebration "+salt; 
		post.url = "http://sodash.com"; 
		post.imgUrl = "http://www.roullierwhite.com/ekmps/shops/roullierwhite/images/maxwell-williams-oyster-fork-123-p.jpg";

		Object posted = jli.post().postUpdate(post);
		assert posted != null;
	}
	
	@Test
	public void testPostPersonShareSimple() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		LIUpdate posted = jli.post().postPersonUpdate("I'm Spoon McGuffin, and I love it when people visit http://sodash.com!");
		assert posted != null;
	}

	@Test
	public void testPostPersonShare() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(JLinkedInTest.TEST_OAUTH_TOKEN_SPOON);
		LIPostRequest post = new LIPostRequest();
		String salt = Utils.getRandomString(4);
		post.contents = "Look at me, I'm Spoon McGuffin! "+salt;
		post.title = "Spoon's Homepage "+salt; 
		post.description = "Don't worry if you don't see Spoon there. "+salt; 
		post.url = "http://sodash.com"; 
		post.imgUrl = "http://images.fineartamerica.com/images-medium-large-5/spoon-pencil-drawing-andrea-pontillo.jpg";
		LIUpdate posted = jli.post().postUpdate(post);				
		assert posted != null;
	}


}
