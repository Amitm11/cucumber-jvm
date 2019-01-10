package step_definitions;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import helpers.DataHelper;
import modules.ButtonClickAction;

public class Hooks 
{
	public static WebDriver driver;

	@After
	/**
	 * Embed a screenshot in test report if test is marked as failed
	 */
	public void embedScreenshot(Scenario scenario) throws Throwable 
	{
		DataHelper.mydata.clear();
		Reporter.log("---------- Scenario Finished ------------");
		if (scenario.isFailed()) 
		{
			try 
			{
				scenario.write("Current Page URL is " + driver.getCurrentUrl());
				// byte[] screenshot = getScreenshotAs(OutputType.BYTES);
				byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) 
			{
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
//		EmailHelper.sendEmail("./target/surefire-reports/emailable-report.html");
		driver.quit();
		if(ButtonClickAction.err_count > 0)
			Assert.assertTrue(false);
	}

	@Before
	/**
	 * Delete all cookies at the start of each scenario to avoid shared state
	 * between tests
	 */
	public void openBrowser() throws MalformedURLException 
	{
		System.out.println("Called openBrowser method");

		System.setProperty("webdriver.ie.driver", "D:\\Cucumber-jvm-project\\IEDriverServer_x64_3.1.0\\IEDriverServer.exe");
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		ieCapabilities.setCapability("requireWindowFocus", true);
		driver = new InternetExplorerDriver(ieCapabilities);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
	}
}