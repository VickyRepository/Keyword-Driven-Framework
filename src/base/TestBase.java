package base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

import utils.Xls_Reader;

public class TestBase {

	/**
	 * All the initialization activity will be carried out in this class
	 *
	 */

	public static WebDriver webDriver;
	public static Properties config;
	public static Properties objectRepo;
	public static boolean isLoggedIn;
	public static Xls_Reader dataTable;

	public void doInitialization() throws Exception {

		if (webDriver == null) {
			config = new Properties();
			FileInputStream configFile;
			configFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\config\\Configuration.properties");
			config.load(configFile);

			objectRepo = new Properties();
			FileInputStream objectRepoFile;
			objectRepoFile = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\config\\ObjectRepositry.properties");
			objectRepo.load(objectRepoFile);

			dataTable = new Xls_Reader(System.getProperty("user.dir") + "\\src\\input\\TestSheet.xlsx");
		}

	}

	public WebDriver intializeBrowser() throws Exception {

		String browserType = config.getProperty("browser");

		switch (browserType.toLowerCase()) {

		case "chrome":
			System.setProperty("webdriver.chrome.driver", config.getProperty("chromeDriverPath"));
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--Start-maximized");
			return webDriver = new ChromeDriver(options);

		case "firefox":
			System.setProperty("webdriver.gecko.driver", config.getProperty("geckoDriverPath"));
			FirefoxOptions capabilities = new FirefoxOptions();
			capabilities.setCapability("marionette", true);
			return webDriver = new FirefoxDriver(capabilities);

		case "iexplorer":
			System.setProperty("webdriver.ie.driver", config.getProperty("ieDriverPath"));
			return new InternetExplorerDriver();

		default:
			throw new Exception("Driver not Found: " + browserType);
		}
	}

	public static WebElement getObject(String propertyValue) throws Exception {

		try {
			String value = objectRepo.getProperty(propertyValue);
			String[] split = value.split("~");
			String locaterType = split[0];
			String locaterValue = split[1];

			switch (locaterType.toLowerCase()) {

			case "css":
				return webDriver.findElement(By.cssSelector(locaterValue));

			case "xpath":
				return webDriver.findElement(By.xpath(locaterValue));

			default:
				throw new Exception("locater Type not Found: " + locaterType);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

}