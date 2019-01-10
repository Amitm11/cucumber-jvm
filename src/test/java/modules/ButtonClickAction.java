package modules;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.PageHelper;
import pageobjects.VendavoCommonPage;
import pageobjects.VendavoCreatePriceSettingPage;
import pageobjects.VendavoCreateWorkbookPage;

public class ButtonClickAction 
{
	public static int err_count = 0;
	
	public static void save_workbook(WebDriver driver) throws Throwable
	{
		VendavoCreateWorkbookPage.save_button.click();
		PageHelper.takeEvidence(driver, "Saved_WB");
		Thread.sleep(2000);
		check_wb_save_error(driver, "saved");
	}
	
	public static void save_close_workbook(WebDriver driver) throws Throwable
	{
		VendavoCreateWorkbookPage.save_close_button.click();
		PageHelper.takeEvidence(driver, "Save_Close_WB");
		Thread.sleep(2000);
		check_wb_save_error(driver, "saved and closed");
	}
	
	public static void submit_workbook(WebDriver driver) throws Throwable
	{
		String parentHandle = driver.getWindowHandle();
		VendavoCreateWorkbookPage.submit_button.click();
		Thread.sleep(1000);
		
		Set<String> allHandles = driver.getWindowHandles();
		for (String currentHandle : allHandles)
		{
			if (!currentHandle.equals(parentHandle))
			{
				driver.switchTo().window(currentHandle);
//				PageHelper.takeEvidence(driver, "Submit_WB");
				driver.switchTo().frame(VendavoCreatePriceSettingPage.frame_1);
				System.out.println("Driver switched to new window");
				
				VendavoCreatePriceSettingPage.OK_button.click();
				Thread.sleep(7000);

				Set<String> allHandlesCheck = driver.getWindowHandles();
				System.out.println(allHandlesCheck);
				if (allHandlesCheck.size() > 1)
				{
					System.out.println("Still on the submit window.");
					String msg_text = VendavoCommonPage.msg_text.getText();
					Reporter.log("Workbook submission failed due to following error: <br>" + msg_text);
					PageHelper.takeEvidence(driver, "Submit_PopUp_Error");
//					Assert.assertTrue(false, "Workbook submission failed due to following error - " + msg_text);
					err_count = 1;
				}
				else
				{	
					driver.switchTo().window(parentHandle);
					if (PageHelper.isElementPresent(driver, VendavoCreateWorkbookPage.table_title_present))
					{
//						if (CreateWorkbookPM.vid.equalsIgnoreCase(anotherString))
						Reporter.log("Workbook submitted successfully.");
						PageHelper.takeEvidence(driver, "Workbook_Submitted");
					}
				}
				
//				if (PageHelper.isElementPresent(driver, VendavoCreateWorkbookPage.table_title_present))
//				{
////					if (CreateWorkbookPM.vid.equalsIgnoreCase(anotherString))
//					Reporter.log("Workbook submitted successfully.");
//					PageHelper.takeEvidence(driver, "Workbook_Approved");
//				}
//				else
//				{
//					driver.switchTo().window(currentHandle);
//					if (PageHelper.isElementPresent(driver, VendavoCommonPage.msg_box))
//					{
//						String msg_text = VendavoCommonPage.msg_text.getText();
//						Reporter.log(msg_text);
//						Assert.assertTrue(false, "Workbook submission failed due to above error");
//					}
//				}
//				else
//				{
//					
//				}
			}
//			else
//				Assert.assertTrue(false, "Submit dialog did not pop up");
		}
//		check_wb_save_error(driver, "submitted");
	}
	
	public static void check_wb_save_error(WebDriver driver, String action) throws Throwable
	{
		if (!PageHelper.isElementPresent(driver, VendavoCommonPage.msg_box))
			Reporter.log("Workbook "+action+" successfully");
		else
		{
			PageHelper.takeEvidence(driver, "Error_"+action+"_WB");
			Reporter.log("Workbook didn't "+action+" due to following error(s)");
			int msg_count = VendavoCommonPage.msg_text_elements.size();
			for (int i = 0; i > msg_count; i++)
			{
				String msg_text = VendavoCommonPage.msg_text_elements.get(i).getText();
				Reporter.log(msg_text);
			}
		}
	}
	
	public static void approve_workbook(WebDriver driver) throws Throwable
	{
		String parentHandle = driver.getWindowHandle();
		VendavoCreateWorkbookPage.approve_button.click();
		Thread.sleep(2000);
		
		Set<String> allHandles = driver.getWindowHandles();
		for (String currentHandle : allHandles)
		{
			if (!currentHandle.equals(parentHandle))
			{
				driver.switchTo().window(currentHandle);
				driver.switchTo().frame(VendavoCreatePriceSettingPage.frame_1);
				System.out.println("Driver switched to new window");
				
				VendavoCreatePriceSettingPage.OK_button.click();
				Thread.sleep(7000);

				Set<String> allHandlesCheck = driver.getWindowHandles();
				System.out.println(allHandlesCheck);
				if (allHandlesCheck.size() > 1)
				{
					System.out.println("Still on approve window.");
					String msg_text = VendavoCommonPage.msg_text.getText();
					Reporter.log("Workbook approval failed due to following error: <br>" + msg_text);
					PageHelper.takeEvidence(driver, "Approve_PopUp_Error");
					err_count = 1;
				}
				else
				{	
					driver.switchTo().window(parentHandle);
					if (PageHelper.isElementPresent(driver, VendavoCreateWorkbookPage.table_title_present))
					{
//						if (CreateWorkbookPM.vid.equalsIgnoreCase(anotherString))
						Reporter.log("Workbook approved successfully.");
						PageHelper.takeEvidence(driver, "Workbook_Approved");
					}
				}
			}
		}
	}
}
