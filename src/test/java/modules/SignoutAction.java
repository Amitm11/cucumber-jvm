package modules;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import helpers.PageHelper;
import pageobjects.VendavoHeaderPage;

public class SignoutAction 
{
	public static void Execute(WebDriver driver) throws Exception
	{
		Thread.sleep(3000);
		if (VendavoHeaderPage.header_right_sign_out.isDisplayed())
		{
			VendavoHeaderPage.header_right_sign_out.click();
			PageHelper.waitForPageLoad(driver);
			Thread.sleep(2000);
			Reporter.log("Sign out successful");
			System.out.println("Signed out");
			PageHelper.takeEvidence(driver, "Sign_Out");
		}
		else
			Assert.assertTrue(false, "Sign Out link is not present");
	}
}
