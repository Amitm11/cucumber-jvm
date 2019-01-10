package modules;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import helpers.DataHelper;
import helpers.PageHelper;
import pageobjects.VendavoLoginPage;

public class SignInAction
{
	public static void Quick_Login(WebDriver driver, String user) throws Throwable
	{
		PageHelper.waitForPageLoad(driver);
		if (VendavoLoginPage.quick_login.isDisplayed())
		{
			PageHelper.takeEvidence(driver, "Before_SignIn");
			int size = VendavoLoginPage.quick_user.size();
			for (int i = 0; i <= size-1; i++)
			{
				String u_name = VendavoLoginPage.quick_user.get(i).getText();
				if (u_name.equalsIgnoreCase(user))
				{
					Reporter.log("Username is ::: "+u_name);
					VendavoLoginPage.quick_user.get(i).click();
					Thread.sleep(2000);
//					PageHelper.waitForPageLoad(driver);
					errorCheck(driver, "quick login");
					break;
				}
				else
					errorCheck(driver, "quick login");
			}
		}
		else
		{
			Assert.assertTrue(false, "Vendavo quick login options did not matched with the given user");
			PageHelper.takeEvidence(driver, "Quick_Login_Error");
		}
	}

	public static void Execute(WebDriver driver, String user, String custName) throws Throwable
	{
		PageHelper.waitForPageLoad(driver);
		DataHelper.data("SignIn", custName+"_TestData");
		PageHelper.takeEvidence(driver, "Before_SignIn");
		
		if (VendavoLoginPage.username.isDisplayed())
		{
			for (int i = 0; i <= DataHelper.mydata.size()-1; i++)
			{
//				if (DataHelper.mydata.get(i).get("Username").equalsIgnoreCase(user))
//				{
					VendavoLoginPage.username.clear();
					VendavoLoginPage.username.sendKeys(DataHelper.mydata.get(i).get("Username"));
					Reporter.log("Username entered in the text box ::: "+DataHelper.mydata.get(i).get("Username"));
					
					VendavoLoginPage.password.clear();
					VendavoLoginPage.password.sendKeys(DataHelper.mydata.get(i).get("Password"));
//					Reporter.log("Password entered in the text box ::: "+DataHelper.mydata.get(i).get("Password"));
			
					VendavoLoginPage.signin_button.click();
					Reporter.log("Click action is performed on Submit button");
					
//					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//					Thread.sleep(2000);
					PageHelper.isPageLoaded(driver);
					errorCheck(driver, "Key in login");
//					PageHelper.isPageLoaded(driver);
//				}
//				else
//					Assert.assertTrue(false, "Data helper did not have corresponding data.");
			}
		}
		else
			Reporter.log("Username textbox is not displayed. OR page not loaded properly");
	}
	
	public static void errorCheck(WebDriver driver, String loginType) throws Throwable
	{
		if (loginType.equalsIgnoreCase("Key in login") && PageHelper.isElementPresent(driver, VendavoLoginPage.login_error_element))
		{
			String err_msg = VendavoLoginPage.login_error.getText();
			Reporter.log("Login action failed due to following error ::: "+err_msg);
			PageHelper.takeEvidence(driver, "Error_KeyIn_SignIn");
			Assert.assertTrue(false, "Login action failed");
		}
		else if (loginType.equalsIgnoreCase("quick login") && PageHelper.isElementPresent(driver, VendavoLoginPage.quick_login_element)) 
		{
			Reporter.log("Login action failed due to incorrect match of user name in quick login");
			PageHelper.takeEvidence(driver, "Error_Quick_SignIn");
			Assert.assertTrue(false, "Login action failed");
		}
		else
		{
			HeaderLeftRight.isHeaderPresent(driver, "right");
			Reporter.log("Login action performed successfully by "+loginType);
			PageHelper.takeEvidence(driver, "After_SignIn");
		}
	}
}
