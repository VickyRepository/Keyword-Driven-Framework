package suites;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import base.TestBase;
import junit.framework.Assert;
import utils.Utilities;

@RunWith(Parameterized.class)
public class LoginTest extends TestBase {

	String userName;
	String password;

	@Before
	public void doInit() throws Exception {
		doInitialization();
		boolean executionMode = Utilities.isSkip("LoginTest");
		if (executionMode == false) {
			Assert.assertTrue(false);
		}
		intializeBrowser();
	}

	public LoginTest(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	@Test
	public void loginTestWithInVaildCredential() throws Exception {

		webDriver.get(config.getProperty("applicationURL"));
		Utilities.doLogin(userName, password);
		if (isLoggedIn == false) {
			System.out.println("Invalid crendtial login test : Pass");
			Assert.assertTrue(false);
		} else if (isLoggedIn == false) {
			System.out.println("Invalid crendtial login test : Fail");
			Assert.assertTrue(false);
		}
	}

	@Parameters
	public static Collection<String[]> getData() {

		return Utilities.getData("LoginData");

	}

	@After
	public void tearDown() {
		if (webDriver != null)
			webDriver.quit();
	}

}
