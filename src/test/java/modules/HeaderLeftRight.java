package modules;

import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import pageobjects.VendavoHeaderPage;

public class HeaderLeftRight 
{
	public static void isHeaderPresent(WebDriver driver, String side) throws Exception
	{
		if (side.equalsIgnoreCase("right"))
			headerRightPresence(driver);
		else
			headerLeftPresence(driver);
	}
	
	public static void verifyMenuOptions(WebDriver driver, String side) throws Throwable
	{
		if (side.equalsIgnoreCase("right"))
			verifyRightMenuOptions(driver);
		else
			verifyLeftMenuOptions(driver);
	}
	
	public static void verifyRightMenuOptions(WebDriver driver) throws Throwable
	{
		int size = VendavoHeaderPage.header_right_menu.size();
		Reporter.log("There are "+ size + " menu options");
		for(int i = 0; i < size; i++)
		{
			String menuTxt = VendavoHeaderPage.header_right_menu.get(i).getText();
			Reporter.log("Right Menu options "+ i +"  :::  " + menuTxt);
		}
	}
	
	public static String verifyLeftMenuOptions(WebDriver driver) throws Throwable
	{
		String adminTab = "non-admin";
		int size = VendavoHeaderPage.header_left_menu.size();
		Reporter.log("There are "+ size + " menu options");
		for(int i = 0; i < size; i++)
		{
			String menuTxt = VendavoHeaderPage.header_left_menu.get(i).getText();
			System.out.println("Left Menu options "+ i +"  :::  " + menuTxt);
			if (menuTxt.equalsIgnoreCase("Administration"))
				adminTab = "admin";
		}
		return adminTab;
	}
	public static void headerRightPresence(WebDriver driver) throws Exception
	{
		if (VendavoHeaderPage.header_right.isDisplayed())
		{
			Reporter.log("Right header is present on the Vendavo website");
		}
		else
		{
			Reporter.log("Right header is not present on the Vendavo website");
		}
	}
	
	public static void headerLeftPresence(WebDriver driver) throws Exception
	{
		if (VendavoHeaderPage.header_left.isDisplayed())
		{
			Reporter.log("Left header is present on the Vendavo website");
		}
		else
		{
			Reporter.log("Left header is not present on the Vendavo website");
		}
	}
}
