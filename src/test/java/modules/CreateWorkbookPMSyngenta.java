package modules;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import helpers.DataHelper;
import helpers.PageHelper;
import pageobjects.VendavoCreatePriceSettingPage;
import pageobjects.VendavoStandardPage;

public class CreateWorkbookPMSyngenta 
{
	static String filter_plg_data;
	static String filter_country_data;
	static String filter_SAP_sys_data;
	static String filter_price_setting_currency_data;
	static String filter_price_exchange_rate_data;
	static String filter_SAP_currency_data;
	static String filter_SAP_exchange_rate_data; 
	static String filter_product_hierarchy_data;
	static String filter_header;
	
	public static void create_new_wb(WebDriver driver, String wb_type, String temp_name) throws Throwable
	{
		String parentHandle = driver.getWindowHandle();
		WebDriver popup = null;
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		if(VendavoCreatePriceSettingPage.new_menu.isDisplayed())
		{
			VendavoCreatePriceSettingPage.new_menu.click();
			if (VendavoCreatePriceSettingPage.new_wb_from_template.isDisplayed())
			{
				VendavoCreatePriceSettingPage.new_wb_from_template.click();
				System.out.println("Clicked on New Workbook From Template option");
				Thread.sleep(7000);
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
							System.out.println("Selecting a template");
							VendavoCreatePriceSettingPage.template_for_selection.click();
							Thread.sleep(1000);
							VendavoCreatePriceSettingPage.OK_button.click();
							Thread.sleep(10000);
							driver.switchTo().window(parentHandle);
							
							if(PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.v_id))
							{
								String vid = VendavoCreatePriceSettingPage.wb_id.getAttribute("value");
								System.out.println("Newly created workbook ID is ::: "+ vid);
								System.out.println("Workbook created successfully");
							}
							else
								Assert.assertTrue(false, "Workbook is not created");
						}
						break;
					}
				}
			}
			else
				Assert.assertTrue(false, "New Workbook From Template option is not present");
		}
		else
			Assert.assertTrue(false, "There is some issue in creating new workbook");
	}
	
	public static void get_excel_data(WebDriver driver) throws Throwable
	{
		Thread.sleep(1000);
		
		for (int i = 1; i <= DataHelper.mydata.size()-1; i++)
		{
			System.out.println("My Data ::: "+DataHelper.mydata.get(i));
			if (DataHelper.mydata.get(i).containsKey("Product Line Group"))
			{
				filter_plg_data = DataHelper.mydata.get(i).get("Product Line Group");
				filter_country_data = DataHelper.mydata.get(i).get("Country");
				filter_SAP_sys_data = DataHelper.mydata.get(i).get("SAP System");
				System.out.println("Filter data "+i+" ::: "+filter_plg_data);
			} 
						
			if (DataHelper.mydata.get(i).containsKey("Price Setting Currency"))
			{
				filter_price_setting_currency_data = DataHelper.mydata.get(i).get("Price Setting Currency");
				filter_price_exchange_rate_data = DataHelper.mydata.get(i).get("Price Setting Exchange Rate");
				filter_SAP_currency_data = DataHelper.mydata.get(i).get("Publish to SAP Currency");
				filter_SAP_exchange_rate_data = DataHelper.mydata.get(i).get("Publish to SAP Exchange Rate");
				System.out.println(filter_price_setting_currency_data);
			}
			
			if (DataHelper.mydata.get(i).containsKey("Product Hierarchy"))
			{
				filter_product_hierarchy_data = DataHelper.mydata.get(i).get("Product Hierarchy");
				System.out.println("Product to be searched ::: " + filter_product_hierarchy_data);
			}
		}
	}
	
	public static void select_plg_value(WebDriver driver) throws Throwable
	{
		System.out.println("plg option list count ::: " + VendavoStandardPage.plg_option_list.size());
		for (int j = 0; j <= VendavoStandardPage.plg_option_list.size()-1; j++)
		{
			String option_value = VendavoStandardPage.plg_option_list.get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_plg_data))
			{
				VendavoStandardPage.plg_option_list.get(j).click();
				Thread.sleep(1000);
				System.out.println("Product Line Group value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with Product Line Group value selection");
		}
	}
	
	public static void select_country_value(WebDriver driver) throws Throwable
	{
		System.out.println("Country option list count ::: " + VendavoStandardPage.country_option_list.size());
		for (int j = 0; j <= VendavoStandardPage.country_option_list.size()-1; j++)
		{
			String option_value = VendavoStandardPage.country_option_list.get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_country_data))
			{
				VendavoStandardPage.country_option_list.get(j).click();
				Thread.sleep(1000);
				System.out.println("Country value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with Destination Country value selection");
		}
	}
	
	public static void select_SAP_sys_value(WebDriver driver) throws Throwable
	{
		System.out.println("SAP System option list count ::: " + VendavoStandardPage.SAP_sys_option_list.size());
		for (int j = 0; j <= VendavoStandardPage.SAP_sys_option_list.size()-1; j++)
		{
			String option_value = VendavoStandardPage.SAP_sys_option_list.get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_SAP_sys_data))
			{
				VendavoStandardPage.SAP_sys_option_list.get(j).click();
				Thread.sleep(30000);
//				PageHelper.isApplyingFilterPresent(driver);
				System.out.println("SAP System value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with SAP System value selection");
		}
	}
	
	public static void select_price_setting_currency_value(WebDriver driver) throws Throwable
	{
		System.out.println("Price Setting Currency option list count ::: " + VendavoStandardPage.price_setting_currency_list.size());
		for (int j = 0; j <= VendavoStandardPage.price_setting_currency_list.size()-1; j++)
		{
			String option_value = VendavoStandardPage.price_setting_currency_list.get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_price_setting_currency_data))
			{
				VendavoStandardPage.price_setting_currency_list.get(j).click();
				Thread.sleep(1000);
				System.out.println("Price Setting Currency value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with Price Setting Currency value selection");
		}
	}
	
	public static void select_price_setting_exchange_rate_value(WebDriver driver) throws Throwable
	{
		System.out.println("Price Setting Exchange Rate option list count ::: " + VendavoStandardPage.price_setting_exchange_rate_list.size());
		for (int j = 0; j <= VendavoStandardPage.price_setting_exchange_rate_list.size()-1; j++)
		{
			String option_value = VendavoStandardPage.price_setting_exchange_rate_list.get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_price_exchange_rate_data))
			{
				VendavoStandardPage.price_setting_exchange_rate_list.get(j).click();
				Thread.sleep(1000);
				System.out.println("Price Setting Exchange Rate value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with Price Setting Exchange Rate value selection");
		}
	}
	
	public static void select_publish_SAP_currency_value(WebDriver driver) throws Throwable
	{
		System.out.println("Publish SAP Currency option list count ::: " + VendavoStandardPage.publish_SAP_currency_list.size());
		for (int j = 0; j <= VendavoStandardPage.publish_SAP_currency_list.size()-1; j++)
		{
			String option_value = VendavoStandardPage.publish_SAP_currency_list.get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_SAP_currency_data))
			{
				VendavoStandardPage.publish_SAP_currency_list.get(j).click();
				Thread.sleep(1000);
				System.out.println("Publish SAP Currency value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with Publish SAP Currency value selection");
		}
	}
	
	public static void select_publish_SAP_exchage_rate_value(WebDriver driver) throws Throwable
	{
		System.out.println("Publish SAP Exchange Rate option list count ::: " + VendavoStandardPage.publish_SAP_exchage_rate_list.size());
		for (int j = 0; j <= VendavoStandardPage.publish_SAP_exchage_rate_list.size()-1; j++)
		{
			String option_value = VendavoStandardPage.publish_SAP_exchage_rate_list.get(j).getText();
			System.out.println("option value ::: " + option_value);
			if (option_value.equalsIgnoreCase(filter_SAP_exchange_rate_data))
			{
				VendavoStandardPage.publish_SAP_exchage_rate_list.get(j).click();
				Thread.sleep(1000);
				System.out.println("Publish SAP Exchange Rate value selected ::: "+option_value);
				break;
			}
			else
				Assert.assertTrue(true, "There is some problem with Publish SAP Exchange Rate value selection");
		}
	}
	
	public static void select_filters(WebDriver driver, String custName) throws Throwable
	{	
		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.header_label))
		{
			get_excel_data(driver);
			Thread.sleep(500);
			
			VendavoStandardPage.product_line_group_input.click();
			Thread.sleep(500);
			PageHelper.isRequestingDataPresent(driver);
			PageHelper.isLoadingPresent(driver);
			select_plg_value(driver);							
			Thread.sleep(1500);
			
			VendavoStandardPage.country_input.click();
			Thread.sleep(500);
			PageHelper.isRequestingDataPresent(driver);
			PageHelper.isLoadingPresent(driver);
			select_country_value(driver);
			Thread.sleep(1500);		
			
			VendavoStandardPage.SAP_system_input.click();
			Thread.sleep(500);
			PageHelper.isRequestingDataPresent(driver);
			PageHelper.isLoadingPresent(driver);
			select_SAP_sys_value(driver);
			Thread.sleep(3000);
			System.out.println("Selected the required filters");
					
			String globalValue = VendavoStandardPage.global_value.getText();
			System.out.println("Global filter value is ::: "+globalValue);
			
			String regionValue = VendavoStandardPage.region_value.getText();
			System.out.println("Region filter value is ::: "+regionValue);
				
			String territoryValue = VendavoStandardPage.territory_value.getText();
			System.out.println("Territory filter value is ::: "+territoryValue);
		}
		else
			Assert.assertTrue(false, "Filters are not present");
	}
	
	public static void fill_wb_name(WebDriver driver) throws Throwable
	{
		Date date = new Date();
		String timestamp = new SimpleDateFormat("dd/MM").format(date);
		String v_name = "Auto SM - " + timestamp;
		VendavoCreatePriceSettingPage.wb_name.clear();
		VendavoCreatePriceSettingPage.wb_name.sendKeys(v_name);
		System.out.println("Workbook name entered is :::" + v_name);
	}
	
	public static void select_workspace_data(WebDriver driver) throws Throwable
	{
//		get_excel_data(driver);
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 20); 
		wait.until(ExpectedConditions.elementToBeClickable(VendavoStandardPage.price_setting_currency_input));
		VendavoStandardPage.price_setting_currency_input.click();
		Thread.sleep(1000);
		PageHelper.isRequestingDataPresent(driver);
		select_price_setting_currency_value(driver);							
		Thread.sleep(1500);
		
//		Thread.sleep(1000);
		VendavoStandardPage.price_setting_exchange_rate_input.click();
		Thread.sleep(1000);
		PageHelper.isRequestingDataPresent(driver);
		select_price_setting_exchange_rate_value(driver);							
		Thread.sleep(1500);
		
//		Thread.sleep(1000);
		VendavoStandardPage.publish_SAP_currency_input.click();
		Thread.sleep(1000);
		PageHelper.isRequestingDataPresent(driver);
		select_publish_SAP_currency_value(driver);							
		Thread.sleep(1500);
		
//		Thread.sleep(1000);
		VendavoStandardPage.publish_SAP_exchage_rate_input.click();
		Thread.sleep(1000);
		PageHelper.isRequestingDataPresent(driver);
		select_publish_SAP_exchage_rate_value(driver);							
		Thread.sleep(1500);
	}
}
