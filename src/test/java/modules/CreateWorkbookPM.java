package modules;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import helpers.DataHelper;
import helpers.PageHelper;
import pageobjects.VendavoCreatePriceSettingPage;
import pageobjects.VendavoCreateWorkbookPage;
import pageobjects.VendavoStandardPage;

public class CreateWorkbookPM 
{
	public static String filter_plg_data;
	public static String filter_header;
	public static String vid;
	
	public static void create_new_wb(WebDriver driver, String wb_type, String temp_name) throws Throwable
	{
		Thread.sleep(3000);
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		click_new_template_button(driver);
		select_template(driver, temp_name);
		
//			else
//			{
//				PageHelper.takeEvidence(driver, "Error_Creating_New_Template");
//				Assert.assertTrue(false, "New Workbook From Template option is not present");
//			}
//		}
//		else
//		{
//			PageHelper.takeEvidence(driver, "Error_Creating_New_Workbook");
//			Assert.assertTrue(false, "There is some issue in creating new workbook");
//		}
	}

	public static void click_new_template_button(WebDriver driver) throws Throwable
	{
		String admin_tab = HeaderLeftRight.verifyLeftMenuOptions(driver);
		System.out.println(admin_tab);
		if (admin_tab.equalsIgnoreCase("admin"))
			create_template_admin(driver);
		else if(admin_tab.equalsIgnoreCase("non-admin"))
		{
			System.out.println("Clicking the button");
			VendavoCreatePriceSettingPage.new_template.click();
		}
	}
	
	public static void create_template_admin(WebDriver driver) throws Throwable
	{
		if(VendavoCreatePriceSettingPage.new_menu.isDisplayed())
		{
			VendavoCreatePriceSettingPage.new_menu.click();
			if (VendavoCreatePriceSettingPage.new_wb_from_template.isDisplayed())
			{
				VendavoCreatePriceSettingPage.new_wb_from_template.click();
				Reporter.log("Clicked on New Workbook From Template option");
				Thread.sleep(5000);
			}
		}
	}
			
	public static void select_template(WebDriver driver, String temp_name) throws Throwable
	{
		Thread.sleep(3000);
		String parentHandle = driver.getWindowHandle();
		WebDriver popup = null;
		Set<String> allHandles = driver.getWindowHandles();
		System.out.println(allHandles);
		
		for (String currentHandle : allHandles)
		{
			if(!currentHandle.equals(parentHandle))
			{
				popup = driver.switchTo().window(currentHandle);
				popup.switchTo().frame(VendavoCreatePriceSettingPage.frame_1);
				System.out.println("Switched to new popup window");
				if (popup.getTitle().equalsIgnoreCase("Powered By Vendavo"))
				{
					Reporter.log("Selecting a template");
					VendavoCreateWorkbookPage.M_template_selection(temp_name).findElement(driver).click();
//					Thread.sleep(500);
//					PageHelper.takeEvidence(driver, "New_Template_Selected");
					VendavoCreatePriceSettingPage.OK_button.click();
					Thread.sleep(4000);
//					PageHelper.isPageLoaded(driver);
					driver.switchTo().window(parentHandle);
					
					if(PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.v_id))
					{
						vid = VendavoCreatePriceSettingPage.wb_id.getAttribute("value");
						Reporter.log("Newly created workbook ID is ::: "+ vid);
						Reporter.log("Workbook created successfully");
						PageHelper.takeEvidence(driver, vid);
					}
					else
					{
						PageHelper.takeEvidence(driver, "Error_Creating_WB");
						Assert.assertTrue(false, "Workbook is not created");
					}
				}
				break;
			}
		}
	}
	
	public static void get_excel_data(WebDriver driver, int k) throws Throwable
	{
		int j = 0;
//		Thread.sleep(500);
		System.out.println("MyData Size ::: "+DataHelper.mydata.size());
		
		if (VendavoStandardPage.filter_label.size() > 6)
			j = 2;
		else if(VendavoStandardPage.filter_label.size() > 6 && VendavoStandardPage.column_header.size() > 1)
			j = 4;
		else
			j = 1;
		
		for (int i = j; i <= DataHelper.mydata.size()-1; i++)
		{
//			Reporter.log("Field Label ::: "+VendavoStandardPage.filter_label.get(k).getText());
//			Reporter.log("My Data ::: "+DataHelper.mydata.get(i));
			VendavoStandardPage.filter_label.get(k).click();
//			Thread.sleep(500);
			if (DataHelper.mydata.get(i).containsKey(VendavoStandardPage.filter_label.get(k).getText().replace("*", "")))
			{
				filter_plg_data = DataHelper.mydata.get(i).get(VendavoStandardPage.filter_label.get(k).getText().replace("*", ""));
				System.out.println("Filter data "+k+" ::: "+filter_plg_data);
				break;
			} 
//			else
//				Assert.assertTrue(false, "Hash map key, value pair did not match");
		}
	}
	
	public static void select_plg_value(WebDriver driver) throws Throwable
	{
		int size = driver.findElements(VendavoStandardPage.select_filter_value(filter_header)).size();
		System.out.println("Size of options ::: "+size);
		
		for (int j = 0; j <= size-1; j++)
		{
			String option_value = VendavoStandardPage.select_filter_value(filter_header).findElements(driver).get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_plg_data))
			{
				VendavoStandardPage.select_filter_value(filter_header).findElements(driver).get(j).click();
//				Thread.sleep(1000);
				Reporter.log(filter_header + " value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with the "+filter_header+" value selection");
		}
	}
	
	public static void select_filters(WebDriver driver, String custName) throws Throwable
	{	
		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.header_label))
		{
//			Thread.sleep(500);
			
			//-------------- Start for 3M ---------------
			for (int i = 0; i < VendavoStandardPage.filter_label.size(); i++)
			{
				get_excel_data(driver, i);
				filter_header = VendavoStandardPage.filter_label.get(i).getText();
				filter_header = filter_header.replace(" ", "").replace("*", "");
				System.out.println("Filter label ::: "+filter_header);
				if (filter_header.equalsIgnoreCase("SalesOrganization"))
					filter_header = "VSalesOrg";
				
				VendavoStandardPage.select_filter_control(filter_header).findElement(driver).click();
//				Thread.sleep(500);
				PageHelper.isRequestingDataPresent(driver);
				PageHelper.isLoadingPresent(driver);
				select_plg_value(driver);
			}
//			PageHelper.takeEvidence(driver, "WB_Filter");
			//-------------- End for 3M ---------------
		}
		else
		{
			PageHelper.takeEvidence(driver, "Error_Filter_Not_Present");
			Assert.assertTrue(false, "Filters are not present");
		}
	}
	
	public static void select_ws_filters(WebDriver driver, String fltrName) throws Throwable
	{
		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.header_label))
		{
//			Thread.sleep(500);
			System.out.println("Label Size ::: "+VendavoStandardPage.filter_label.size());
			//-------------- Start for 3M ---------------
			for (int i = 0; i < VendavoStandardPage.filter_label.size(); i++)
			{
				get_excel_data(driver, i);
				filter_header = VendavoStandardPage.filter_label.get(i).getText();
				filter_header = filter_header.replace(" ", "").replace("*", "");
				System.out.println("Filter label ::: "+filter_header);
				if (filter_header.contains(fltrName))
				{
					VendavoStandardPage.select_filter_control(filter_header).findElement(driver).click();
//					Thread.sleep(500);
					PageHelper.isRequestingDataPresent(driver);
					PageHelper.isLoadingPresent(driver);
					select_plg_value(driver);
				}
			}
			PageHelper.takeEvidence(driver, "WS_Filter");
			PageHelper.waitForGridDataLoad(driver);
			//-------------- End for 3M ---------------
		}
		else
		{
			PageHelper.takeEvidence(driver, "Error_Filter_Not_Present");
			Assert.assertTrue(false, "Filters are not present");
		}
	}
	
	public static void fill_wb_name(WebDriver driver) throws Throwable
	{
		Date date = new Date();
		String timestamp = new SimpleDateFormat("dd/MM").format(date);
		String v_name = "Auto SM - " + timestamp;
		VendavoCreatePriceSettingPage.wb_name.clear();
		VendavoCreatePriceSettingPage.wb_name.sendKeys(v_name);
		Reporter.log("Workbook name entered is :::" + v_name);
		VendavoCreatePriceSettingPage.wb_id.click();
		Thread.sleep(1000);
		PageHelper.takeEvidence(driver, "Entered_WB_Name");
	}
	
	public static void select_date(WebDriver driver) throws Throwable
	{
		Thread.sleep(1000);
		VendavoStandardPage.date_picker.click();
		Thread.sleep(500);
		PageHelper.isRequestingDataPresent(driver);
		Reporter.log("Clicked on Validity field");
		
		SimpleDateFormat fromDate = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 15);
		String toDate = fromDate.format(c.getTime());
		
		Date date = new Date();
		String fmDate = fromDate.format(date);
				
		if (PageHelper.isElementPresent(driver, VendavoStandardPage.date_picker_popup))
		{
			VendavoStandardPage.from_date.clear();
			VendavoStandardPage.from_date.sendKeys(fmDate);
			VendavoStandardPage.to_date.clear();
			VendavoStandardPage.to_date.sendKeys(toDate);
			Reporter.log("From and To date entered as ::: "+fmDate+ " AND "+toDate);
			PageHelper.takeEvidence(driver, "Selected_Date");
			VendavoCreateWorkbookPage.Date_Picker_OK_button.click();
			Thread.sleep(1000);
		}
		else
		{
			PageHelper.takeEvidence(driver, "Error_Date_Picker_Not_Displayed");
			Assert.assertTrue(false, "Date Picker pop up did not displayed");
		}
	}
}
