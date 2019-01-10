package modules;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.PageHelper;
import pageobjects.VendavoCommonPage;
import pageobjects.VendavoLoginPage;

public class ApprovalWorkFlow 
{
	public static String[] users;

	public static void getApprovalLevel(WebDriver driver) throws Throwable
	{
		users = null;
		VendavoCommonPage.tools_restore.click();
		Thread.sleep(1000);
		if (VendavoCommonPage.approval_tab.isDisplayed())
		{
			VendavoCommonPage.approval_tab.click();
			int size = VendavoCommonPage.approval_list.size();
			System.out.println("Approval Levels ::: "+ size);
			for(int i = 0; i <= size-1; i++)
			{
				users[i] = VendavoCommonPage.approval_user.get(i).getText();
				System.out.println("Approval user "+i+" ::: "+users[i]);
			}
		}
		PageHelper.takeEvidence(driver, "Approval_Level");
	}
	
	public static void approver_sign_in(WebDriver driver) throws Throwable
	{
		if (VendavoLoginPage.username.isDisplayed())
		{
			for(int i = 0; i <= users.length; i++)
			{
				VendavoLoginPage.username.clear();
				VendavoLoginPage.username.sendKeys(ApprovalWorkFlow.users[i]);
				Reporter.log("Username entered in the text box ::: " + users[i]);
					
				VendavoLoginPage.password.clear();
				VendavoLoginPage.password.sendKeys("ASDF");
			
				VendavoLoginPage.signin_button.click();
				Reporter.log("Click action is performed on Submit button");
				
				PageHelper.isPageLoaded(driver);
				SignInAction.errorCheck(driver, "Key in login");
				PageHelper.takeEvidence(driver, "Approver"+(i+1)+"_Sign_In");
				
				NavigationFolderPM.open_price_manager_page(driver, "Price Manager");
				CheckWorksheets.open_existing_workbook(driver);
				ButtonClickAction.approve_workbook(driver);
				SignoutAction.Execute(driver);
			}
		}
	}
	
	
}
