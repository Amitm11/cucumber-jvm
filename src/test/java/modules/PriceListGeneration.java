package modules;

import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import helpers.PageHelper;
import pageobjects.VendavoCreatePriceSettingPage;

public class PriceListGeneration 
{
	public static void open_price_list_view_page(WebDriver driver) throws Throwable
	{
		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.selector_section))
			VendavoCreatePriceSettingPage.selector_minimize.click();
		
		int size = VendavoCreatePriceSettingPage.worksheet_list_data.size();
		for (int i = 1; i < size; i++)
		{
			String ws_text = VendavoCreatePriceSettingPage.worksheet_list_data.get(i).getText();
			if (ws_text.contains("Price List"))
			{
				System.out.println(ws_text);
				VendavoCreatePriceSettingPage.worksheet_list_data.get(i).click();
				Reporter.log("Clicked on " + ws_text);
				Thread.sleep(5000);
				PageHelper.takeEvidence(driver, ws_text);
				generate_pricelist(driver);
			}
		}
	}
	
	public static void add_product(WebDriver driver) throws Throwable
	{
		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.product_filter))
		{
			VendavoCreatePriceSettingPage.add_product_button.click();
			Thread.sleep(4000);

			if (VendavoCreatePriceSettingPage.product_search_box.isDisplayed())
			{
				Reporter.log("Product Selector dialog box is opened");
				CreateWorkbookPMSyngenta.get_excel_data(driver);
				
				VendavoCreatePriceSettingPage.product_search_box.sendKeys(CreateWorkbookPMSyngenta.filter_product_hierarchy_data);
				Thread.sleep(3000);
				VendavoCreatePriceSettingPage.select_product_from_list.click();
				VendavoCreatePriceSettingPage.add_selected_product.click();
				Thread.sleep(1000);
				if (VendavoCreatePriceSettingPage.selected_product_result_list.isDisplayed())
				{
					Reporter.log("Product is added in the Selected Items list");
					VendavoCreatePriceSettingPage.OK_button.click();
					Thread.sleep(7000);
					
					int size = VendavoCreatePriceSettingPage.product_list.size();
//					System.out.println("Size of list ::: " + size);
					for(int i = 0; i < size-1; i++)
					{
						String product_name = VendavoCreatePriceSettingPage.product_list.get(i).getText();
//						System.out.println("Added product in the filter is ::: " + product_name);
						if (product_name.equals(CreateWorkbookPMSyngenta.filter_product_hierarchy_data))
							Reporter.log("Product is added to the Product Hierarchy filter");
					}
				}
				else
					Assert.assertTrue("There is some problem with product selection", false);
			}
		}
	}
	
	public static void delete_default_product(WebDriver driver) throws Throwable
	{
		Actions action = new Actions(driver);
		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.default_product))
		{
			VendavoCreatePriceSettingPage.select_default_product.click();
			action.contextClick(VendavoCreatePriceSettingPage.select_default_product).build().perform();
			Thread.sleep(1000);
			VendavoCreatePriceSettingPage.delete_selected_product.click();
			Thread.sleep(2000);
			Runtime.getRuntime().exec("D:\\Cucumber-jvm-project\\cucumber-jvm-master\\src\\test\\java\\helpers\\Delete_OK.exe");
			Thread.sleep(3000);
			if (!PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.default_product))
				Reporter.log("Default product is deleted from the list");
			else
				Assert.assertTrue("There is some problem in product deletion", false);
		}
		else
			Reporter.log("There is no default product to delete");
	}
	
	public static void generate_pricelist(WebDriver driver) throws Throwable
	{
		String parentHandle = driver.getWindowHandle();
		System.out.println(parentHandle);
		VendavoCreatePriceSettingPage.generate_schedule_button.click();
		Thread.sleep(4000);
		Set<String> allHandles = driver.getWindowHandles();
		System.out.println(allHandles);
//		PageHelper.takeEvidence(driver, "Generate_PL_Popup");
		for (String currentHandle : allHandles)
		{
			if (!currentHandle.equals(parentHandle))
			{
				driver.switchTo().window(currentHandle);
//				PageHelper.takeEvidence(driver, "Generate_PL_Popup");
				driver.switchTo().frame(VendavoCreatePriceSettingPage.frame_1);
				System.out.println("Driver switched to new window");
				Thread.sleep(500);
				VendavoCreatePriceSettingPage.apply_button.click();
				Thread.sleep(10000);
				driver.switchTo().window(parentHandle);
			}
		}

		if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.pl_locked_generation))
		{
			VendavoCreatePriceSettingPage.refresh_button.click();
			Thread.sleep(10000);
			if (PageHelper.isElementPresent(driver, VendavoCreatePriceSettingPage.table_header))
			{
				Reporter.log("Price list is generated");
				PageHelper.takeEvidence(driver, "PriceList_Generated");
			}
			else
			{
				Reporter.log("price list is still generating");
				PageHelper.takeEvidence(driver, "PriceList_Still_Generating");
			}
		}
		else
		{
			PageHelper.takeEvidence(driver, "Error_Clicking Refresh_Button");
			Assert.assertTrue("There is problem generating price list", false);
		}
		verify_price_list(driver);
	}
	
	public static void verify_price_list(WebDriver driver) throws Throwable
	{
		String rowCount = VendavoCreatePriceSettingPage.pl_row_count.getText();
		Reporter.log("Row count in generated price list ::: "+rowCount);
	}
	
	public static void generate_price_list(WebDriver driver) throws Throwable
	{
		open_price_list_view_page(driver);
//		add_product(driver);
//		delete_default_product(driver);
//		generate_pricelist(driver);
//		verify_price_list(driver);
	}
}
