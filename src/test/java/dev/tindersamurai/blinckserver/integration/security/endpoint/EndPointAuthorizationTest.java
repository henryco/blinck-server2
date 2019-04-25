package dev.tindersamurai.blinckserver.integration.security.endpoint;

import dev.tindersamurai.blinckserver.integration.BlinckIntegrationAccessTest;
import org.junit.Test;

/**
 * @author Henry on 27/08/17.
 */
public class EndPointAuthorizationTest extends BlinckIntegrationAccessTest {


	@Test
	public void testPublicAdminAuthorization() throws Exception {
		assert getForAdminAuthToken() != null;
	}


	@Test
	public void testPublicUserAuthorization() throws Exception {
		assert getForUserAuthToken() != null;
	}

}