package modules;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import helpers.DataHelper;
import helpers.PageHelper;
import pageobjects.VendavoCommonPage;
import pageobjects.VendavoCreatePriceSettingPage;
import pageobjects.VendavoStandardPage;

public class CheckWorksheets 
{
	public static String grid_data;
	
	public static void open_existing_workbook(WebDriver driver) throws Throwable
	{
		VendavoCommonPage.existing_workbook_select(CreateWorkbookPM.vid).findElement(driver).click();
		Actions action = new Actions(driver);
		action.doubleClick(VendavoCommonPage.existing_workbook_select(CreateWorkbookPM.vid).findElement(driver)).build().perform();
		Thread.sleep(7000);
		if(PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.v_id))
		{
			Reporter.log("Recently created workbook is opened.");
			PageHelper.takeEvidence(driver, "Workbook_Opened");
		}
		else
		{
			Reporter.log("Workbook didnot open, please check the error message");
			PageHelper.takeEvidence(driver, "Error_Workbook_Open");
			ButtonClickAction.err_count = 1;
		}
//		System.out.println("Number of Workbooks ::: " + VendavoCreate6_3PriceSettingPage.workbook_list.size());
//		for (int i = 0; i <= VendavoCreate6_3PriceSettingPage.workbook_list.size()-1; i++)
//		{
//			if (VendavoCreate6_3PriceSettingPage.workbook_list.get(i).getText().equalsIgnoreCase("zon-adjustment"))
//			{
//				Actions action = new Actions(driver);
//				action.doubleClick(VendavoCreate6_3PriceSettingPage.workbook_list.get(i)).build().perform();
//				Thread.sleep(7000);
//				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//				System.out.println("Existing workbook is opened");
//			}
//		}
	}
	
	public static void open_worksheets(WebDriver driver) throws Throwable
	{
		if(PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.selector_section))
			VendavoCreatePriceSettingPage.selector_minimize.click();
		
		int size = VendavoCreatePriceSettingPage.worksheet_list_data.size();
		Reporter.log("Number of worksheets ::: " + (size-1));
		for (int i = 1; i <= (size-1); i++)
		{
			Thread.sleep(2000);
			String ws_text = VendavoCreatePriceSettingPage.worksheet_list_data.get(i).getText();
			if (ws_text.contains("Price List"))
				break;
			
			VendavoCreatePriceSettingPage.worksheet_list_data.get(i).click();
			System.out.println("Clicked on " + ws_text);
			Thread.sleep(3000);
			PageHelper.waitForGridDataLoad(driver);
//			PageHelper.takeEvidence(driver, ws_text);
			if (VendavoCreatePriceSettingPage.row_header.isDisplayed())
			{
				Reporter.log("Opened worksheet name is ::: " + ws_text);
				expand_all(driver);
			}
			else
			{
				PageHelper.takeEvidence(driver, "Error_Loading_"+ws_text);
				Assert.assertTrue("There is some problem in loading worksheet ", false);
			}
		}
	}
	
	public static void expand_all(WebDriver driver) throws Throwable
	{
		Actions action = new Actions(driver);

		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.node_collapse_img))
		{
			action.contextClick(VendavoCreatePriceSettingPage.row_header).build().perform();
			Thread.sleep(500);
			Reporter.log("Right click performed");

			if (VendavoCreatePriceSettingPage.expand_all.isDisplayed())
			{
				VendavoCreatePriceSettingPage.expand_all.click();
				PageHelper.waitForGridDataLoad(driver);
			}
			else
			{
				PageHelper.takeEvidence(driver, "Error_Expand_All");
				Assert.assertTrue("There is some problem with expand all feature", false);
			}
			
			if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.node_expand_img))
			{
				Reporter.log("Grid data expand loaded successfully");
				PageHelper.takeEvidence(driver, "Expand_All");
			}
			else
			{
				PageHelper.takeEvidence(driver, "Error_Grid_Data_Load");
				Assert.assertTrue("There is some problem with grid data load", false);
			}
		}
		else
		{
			Reporter.log("Worksheet page loaded successfully");
			PageHelper.takeEvidence(driver, "Worksheet_Page_Opened");
		}
	}
	
//	public static void fill_grid_data(WebDriver driver) throws Throwable
//	{
////		PageHelper.isLoadingGridCellsPresent(driver);
//		Thread.sleep(2000);
//		if (PageHelper.isElementPresent(driver, VendavoStandardPage.country_list_price_element))
//		{
//			VendavoStandardPage.country_list_price.sendKeys("120");
//			VendavoStandardPage.max_discount.click();
//			Thread.sleep(1000);
//			VendavoStandardPage.max_discount.sendKeys("3");
//			VendavoStandardPage.country_list_price.click();
//			PageHelper.takeEvidence(driver, "Grid_Data_Entered");
//		}
//	}
	
//	public static void fill_grid_data(WebDriver driver, String columns) throws Throwable
//	{
//		VendavoStandardPage.upper_price_threshold.clear();
//		Thread.sleep(500);
//		VendavoStandardPage.upper_price_threshold.sendKeys("100");
//		Thread.sleep(500);
//		VendavoStandardPage.lower_price_threshold.click();
//		Thread.sleep(1000);
//		VendavoStandardPage.lower_price_threshold.clear();
//		Thread.sleep(500);
//		VendavoStandardPage.lower_price_threshold.sendKeys("70");
//		Thread.sleep(500);
//		VendavoStandardPage.upper_price_threshold.click();
//		Reporter.log("Entered Upper Price Corridor Threshold and Lower Price Corridor threshold data");
//		if (columns.contains("Lower Price"))
//			PageHelper.takeEvidence(driver, "Grid_Data_Entered");
//	}
	
	public static void fill_grid_data(WebDriver driver) throws Throwable
	{
		int col_count = VendavoStandardPage.column_header.size();
		System.out.println("No of Columns ::: "+col_count);

		int input_text_count = VendavoStandardPage.input_columns.size();
		System.out.println("No of input fields ::: " +input_text_count);

		for (int i = 0; i < input_text_count; i++)
		{
			get_grid_data(driver, i);
			Reporter.log((i+1) +". Input Value ::: " + grid_data);
//			VendavoStandardPage.input_columns.get(i).clear();
			VendavoStandardPage.input_columns.get(i).sendKeys(grid_data);
		}
		Reporter.log("First row grid data entered");
		PageHelper.takeEvidence(driver, "Grid_Data_Entered");
	}
	
	public static void get_grid_data(WebDriver driver, int k) throws Throwable
	{
		int j = 0;
		String key_text;
		Thread.sleep(500);
		
		System.out.println("MyData Size ::: "+DataHelper.mydata.size());
		
		String column_name = VendavoStandardPage.input_columns.get(k).getAttribute("name");
		System.out.println(column_name);
		String name = column_name.replace(".", ":");
		String[] name_colon = name.split(":");
		if (name_colon.length < 4)
			key_text = name_colon[1];
		else
			key_text = name_colon[1]+"."+name_colon[2];
		System.out.println("Key text ::: " + key_text);
		
		if(VendavoStandardPage.column_header.size() > 1)
			j = 4;
		
		for (int i = j; i <= DataHelper.mydata.size()-1; i++)
		{
//			Reporter.log("My Data ::: "+DataHelper.mydata.get(i));
			Thread.sleep(500);
			
			if (DataHelper.mydata.get(i).containsKey(key_text))
			{
				grid_data = DataHelper.mydata.get(i).get(key_text);
				System.out.println("Grid data "+ (k+1) +" ::: "+grid_data);
				break;
			} 
//			else
//				Assert.assertTrue(false, "Hash map key, value pair did not match");
		}
	}
}
