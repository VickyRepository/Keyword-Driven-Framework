package suites;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import base.TestBase;
import junit.framework.Assert;
import utils.Utilities;

public class RegistrationTestCase extends TestBase {

	@Before
	public void doInit() throws Exception {
		doInitialization();
		boolean executionMode = Utilities.isSkip("RegistrationTestCase");
		if(executionMode==false) {
			Assert.assertTrue(false);
		}
		intializeBrowser();
	}

	@Test
	public void loginTestWithVaildCredential() throws Exception {

		webDriver.get(config.getProperty("applicationURL"));
		Utilities.doLogin("Tester", "test");
		if (isLoggedIn == true)
			System.out.println("Landed succesffuly on the Home page");
		else
			System.out.println("Not landed on the Home page");
	}

	@After
	public void tearDown() {
		if (webDriver != null)
			webDriver.quit();
	}

}
