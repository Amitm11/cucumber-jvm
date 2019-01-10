package step_definitions;

import static org.testng.AssertJUnit.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.DataHelper;
import modules.ApprovalWorkFlow;
import modules.CheckWorksheets;
import modules.HeaderLeftRight;
import modules.NavigationFolderPM;
import pageobjects.VendavoCommonPage;
import pageobjects.VendavoCreatePriceSettingPage;
import pageobjects.VendavoCreateWorkbookPage;
import pageobjects.VendavoHeaderPage;
import pageobjects.VendavoPriceManagerPage;
import pageobjects.VendavoStandardPage;

public class PageStepsDef
{
	public WebDriver driver;

	public PageStepsDef() 
	{
		driver = Hooks.driver;
		PageFactory.initElements(driver, VendavoCreatePriceSettingPage.class);
		PageFactory.initElements(driver, VendavoCreateWorkbookPage.class);
		PageFactory.initElements(driver, VendavoStandardPage.class);
		PageFactory.initElements(driver, VendavoCommonPage.class);
		PageFactory.initElements(driver, VendavoHeaderPage.class);
		PageFactory.initElements(driver, VendavoPriceManagerPage.class);
	}
	
	@Then("^user verifies page title$")
	public void user_verifies_page_title() throws Throwable 
	{
		Thread.sleep(5000);
		assertEquals("Powered by Vendavo", driver.getTitle());
		Reporter.log("Title of the page is ::: " + driver.getTitle());
	}
	
	@When("^user verifies (?:the|a)? (.*) header presence$")
	public void user_verifies_header(String side) throws Throwable 
	{
		Thread.sleep(2000);
		HeaderLeftRight.isHeaderPresent(driver, side);
	}

	@When("^user verifies (?:the|a)? (.*) header menu options$")
	public void user_verifies_the_header_options(String side) throws Throwable 
	{
		HeaderLeftRight.verifyMenuOptions(driver, side);
	}
	
	@And("^navigates to (.*) page$")
	public void navigates_to_module_page(String module) throws Throwable 
	{
//		PageHelper.waitForPageLoad(driver);
		if (module.equalsIgnoreCase("price manager"))
			NavigationFolderPM.open_price_manager_page(driver, module);
		else if (module.equalsIgnoreCase("deal manager"))
		{

		}
	}

	@Then("^user verifies the number of root children presence$")
	public void user_verifies_number_of_root_children_presence() throws Throwable 
	{
		NavigationFolderPM.count_root_children(driver, "root children");
	}

	@Then("^user verifies the number of sub children presence for each of the root childeren$")
	public void user_verifies_number_of_sub_children_for_each_of_the_root_children() throws Throwable 
	{
		NavigationFolderPM.count_sub_children(driver, "sub children");
	}

	@Then("^user opens an existing workbook$")
	public void user_opens_existing_workbook() throws Throwable
	{
		CheckWorksheets.open_existing_workbook(driver);
	}

	@And("^get the approval level$")
	public void get_approval_level() throws Throwable
	{
		ApprovalWorkFlow.getApprovalLevel(driver);
	}
	
	@Then("^user opens worksheets and expands all of them and wait for data to load$")
	public void user_opens_worksheets_and_perform_expand_all() throws Throwable 
	{
		CheckWorksheets.open_worksheets(driver);
	}

	@Then("^user verifies the presence of (.*) folder and expands it$")
	public void user_verifies_root_children_presence_expands_it(String flag) throws Throwable
	{
		String rootFolder = null;
		for (int i= 0; i <= DataHelper.mydata.size()-1; i++)
		{
			if (DataHelper.mydata.get(i).containsValue(flag.substring(5)))
			{
				rootFolder = DataHelper.mydata.get(i).get("Folder Name");
				System.out.println("Folder name is ::: "+rootFolder);
			}
		}
		
		By root_element_path = VendavoCreateWorkbookPage.M_root_expand(rootFolder);
//		NavigationFolderPM.count_root_children(driver, rootFolder);
		NavigationFolderPM.expand_root(driver, rootFolder, root_element_path);
	}

	@And("^user verifies the presence of (.*) workbook and clicks on it$")
	public void user_verifies_sub_children_presence_clicks_it(String flag) throws Throwable
	{
		String subChild = null;
		for (int i= 0; i <= DataHelper.mydata.size()-1; i++)
		{
			if (DataHelper.mydata.get(i).containsValue(flag.substring(5)))
			{
				subChild = DataHelper.mydata.get(i).get("Workbook Name");
				System.out.println("Workbook name is ::: "+subChild);
			}
		}
		By sub_child = VendavoCreateWorkbookPage.M_sub_child_element(subChild);
		NavigationFolderPM.select_sub_child(driver, sub_child);
	}
}
