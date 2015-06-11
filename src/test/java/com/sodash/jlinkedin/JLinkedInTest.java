package com.sodash.jlinkedin;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sodash.jlinkedin.fields.ProfileField;
import com.sodash.jlinkedin.model.LIProfile;

public class JLinkedInTest {

	public static String TEST_OAUTH_TOKEN = "AQWCe32z5e_ruARMdUdFZIRYvzz43kD5931hu7SD0vmfjK2gFutWH1AauKuAAw4cJWDzb6OLToREMHXuq6KZEnGloOePTq_TGi2cGIxpK6N_wqG4uy6jkL31eT2w8magBodXK6B_EXjn5wMtocAxNvuUgbEmb7OWBjuPCNwmg3Lv6LcZh7M";
	public static String TEST_OAUTH_TOKEN_SPOON = //"AQW9iL6H9sYCRxDguHWefTrEUG_DyjzADOOyzs5CPpUjCYs84V5WhDCUOYbppwCwczU-VvvYsAiGoZ2Ub_8H7B6F_yt0JwxHJcfwmOqtCMcYm9X93oNYlHRuq9udNPUVYgUNLefBnWG7eZi6Y0Hx27iIXCGF9QS5_IPh-OoQbbG_aNdcCGo";
												  "AQV81ws3zblNd_rhj0wncnmMhFGyh_VzVtN_C3SsEhNjdwC0TAsz-I3Mh9rU4EA9e91Q_gxvRzPtw3W9cgc-Hz5AXVeqMOBzVpGY9b7b-5H-MIYjJPURnpKFRLaxw5hVUPg1Pt1ZJAO8xl9zIENi7vAtZd_pwrEqEuNQMldZNyLpRz3N-Gk";
	
	@Test
	public void testGetProfile() {
		JLinkedIn jli = new JLinkedIn().setAuthToken(TEST_OAUTH_TOKEN);		
		
		LIProfile peep = jli.get()
				.setFields(ProfileField.ID,ProfileField.FIRST_NAME,ProfileField.LAST_NAME, ProfileField.SUMMARY, ProfileField.PICTURE_URL,
						ProfileField.INDUSTRY,
						ProfileField.DATE_OF_BIRTH, // this gets quietly ignored?
						ProfileField.PUBLIC_PROFILE_URL, ProfileField.LOCATION, ProfileField.POSITIONS)
				.getMyProfile();
		assert peep != null;
		System.out.println(peep);
		System.out.println(peep.getName()+" from "+peep.getLocation()+" who does "+peep.getPositions());
		
	}

}
