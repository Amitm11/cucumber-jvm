package modules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import helpers.PageHelper;
import pageobjects.VendavoCreateWorkbookPage;
import pageobjects.VendavoHeaderPage;
import pageobjects.VendavoPriceManagerPage;

public class NavigationFolderPM 
{
	public static void open_price_manager_page(WebDriver driver, String module) throws Throwable
	{
		int size = VendavoHeaderPage.header_left_menu.size();
//		System.out.println("There are "+ size + " menu options");
		for(int i = 0; i < size; i++)
		{
			String menuTxt = VendavoHeaderPage.header_left_menu.get(i).getText();
			if (menuTxt.equalsIgnoreCase(module))
			{
				VendavoHeaderPage.header_left_menu.get(i).click();
				Reporter.log("User clicked on "+ menuTxt +" module");
				Thread.sleep(1000);
				PageHelper.takeEvidence(driver, "PM_Module");
				break;
			}
//			else
//				System.out.println(menuTxt+" module is not present");
		}
	}
	public static void count_root_children(WebDriver driver, String root) throws Throwable
	{
		Thread.sleep(5000);
		if (!VendavoPriceManagerPage.folderNav_root_children_PM.isEmpty())
		{
			int count = VendavoPriceManagerPage.folderNav_root_children_PM.size();
			Reporter.log("There are "+ count + " root children");
			for (int i = 0; i < count; i++)
			{
				String rootTxt = VendavoPriceManagerPage.folderNav_root_children_PM.get(i).getText();
				Reporter.log("Root child is ::: " + rootTxt);
			}
		}
		else
			Reporter.log("There is no root child present");
	}
	
	public static void count_sub_children(WebDriver driver, String sub_children) throws Throwable
	{
		Thread.sleep(3000);
		if (!VendavoPriceManagerPage.folderNav_root_children_PM.isEmpty())
		{
			int count = VendavoPriceManagerPage. folderNav_children1_PM.size();
			Reporter.log("There are "+ count + " sub children");
			for (int i = 0; i < count; i++)
			{
				String rootTxt = VendavoPriceManagerPage. folderNav_children1_PM.get(i).getText();
				Reporter.log("Sub child is ::: " + rootTxt);
			}
		}
		else
			Reporter.log("There is no sub child present");
	}
	
	public static void expand_root(WebDriver driver, String rootFolder, By root_element_toggle) throws Throwable
	{
		if (!VendavoPriceManagerPage.folderNav_root_children_PM.isEmpty())
		{
			driver.findElement(root_element_toggle).click();
//			VendavoCreate6_3PriceSettingPage.root_toggle.click();
			Thread.sleep(1000);
			if (VendavoPriceManagerPage.folderNav_children1_PM.size() > 0)
//			if (VendavoCreate6_3PriceSettingPage.child_title.isDisplayed())
			{
				Reporter.log(rootFolder + " is extended");
//				PageHelper.takeEvidence(driver, rootFolder);
			}
			else
			{
				Reporter.log(rootFolder + " is not extended");
				PageHelper.takeEvidence(driver, rootFolder);
			}
		}
	}
	
	public static void select_sub_child(WebDriver driver, By sub_child) throws Throwable
	{
//		VendavoCreate6_3PriceSettingPage.child_title.click();
		String subChild = driver.findElement(sub_child).getAttribute("title");
		driver.findElement(sub_child).click();
		Thread.sleep(4000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (VendavoCreateWorkbookPage.table_title.isDisplayed())
		{
			Reporter.log("Left side pane is opened");
			System.out.println("Clicked on sub child ::: "+subChild);
			PageHelper.takeEvidence(driver, subChild);
		}
		else
		{
			Reporter.log("Left side pane is not opened");
		}
	}
}
