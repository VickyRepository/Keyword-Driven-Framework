package suites;

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
public class LoginTestCase extends TestBase {

	String userName;
	String password;
	String dataType;

	public LoginTestCase(String userName, String password, String dataType) {

		this.userName = userName;
		this.password = password;
		this.dataType = dataType;

	}

	@Before
	public void doInit() throws Exception {
		doInitialization();
		boolean executionMode = Utilities.isSkip("LoginTestCase");
		if (executionMode == false) {
			Assert.assertTrue(false);
		}
		intializeBrowser();
	}

	@Test
	public void loginTest() throws Exception {

		webDriver.get(config.getProperty("applicationURL"));
		Utilities.doLogin(userName, password);
		if (!isLoggedIn && dataType.equals("Y")) {
			System.err.println("Not Landed succesffuly on the Home page with valid credential");
			Utilities.getScreenshot("LoginFailureOne");
			Assert.assertTrue(false);
		} else if (isLoggedIn && dataType.equals("N")) {
			System.err.println("Landed on the Home page with invalid credential");
			Utilities.getScreenshot("LoginFailureTwo");
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
