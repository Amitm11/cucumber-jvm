package step_definitions;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.DataHelper;
import helpers.PageHelper;
import modules.ApprovalWorkFlow;
import modules.ButtonClickAction;
import modules.CheckWorksheets;
import modules.CreateWorkbookPM;
import modules.CreateWorkbookPMSyngenta;
import modules.PriceListGeneration;
import modules.SignInAction;
import modules.SignoutAction;
import pageobjects.VendavoHeaderPage;
import pageobjects.VendavoLoginPage;

public class FormStepsDef
{
	public static List<HashMap<String, String>> datamap = null;
	public static HashMap<String, String> sampleData = new HashMap<String, String>();
	public WebDriver driver;

	public FormStepsDef() 
	{
		driver = Hooks.driver;
		PageFactory.initElements(driver, VendavoLoginPage.class);
		PageFactory.initElements(driver, VendavoHeaderPage.class);
	}

	@Given("^user is logged in to vendavo website as (.*) for (.*)$")
	public void user_is_logged_in_to_vendavo_website(String username, String custName) throws Throwable 
	{
		user_opens_website();

		if (PageHelper.isElementPresent(driver, By.className("quick-login")))
			SignInAction.Quick_Login(driver, username);
		else
			SignInAction.Execute(driver, username, custName);
	}

	@When("^user opens vendavo website$")
	public void user_opens_website() throws Throwable 
	{
		// driver.get("http://gsdsynsap.vmcz.vendavo.com:50000/vendavo/");
		driver.get("http://localhost:8100/vendavo/");
		Thread.sleep(2000);
	}

	@Then("^user signs in as (.*) for (.*)$")
	public void user_signs_in(String user, String custName) throws Throwable 
	{
		PageHelper.Deletefiles();
		SignInAction.Execute(driver, user, custName);
	}

	@Then("^user signs in to approve the workbook$")
	public void sign_in_approve_workbook() throws Throwable
	{
		ApprovalWorkFlow.approver_sign_in(driver);
	}
	
	@Then("^user signs out$")
	public void user_signs_out() throws Throwable 
	{
		SignoutAction.Execute(driver);
	}
	
	@Then("^user creates a (.*) for (.*) workbook$")
	public void user_creates_new_workbook_from_template(String wb_type, String flag) throws Throwable
	{
		String temp_name = null;
		for (int i= 0; i <= DataHelper.mydata.size()-1; i++)
		{
			if (DataHelper.mydata.get(i).containsValue(flag.substring(5)))
			{
				temp_name = DataHelper.mydata.get(i).get("Workbook Name");
				System.out.println("Template name is ::: "+temp_name + " Workbook");
			}
		}
		CreateWorkbookPM.create_new_wb(driver, wb_type, temp_name + " Workbook");
	}
	
	@Then("^user reads (.*) sheet from (.*) excel file for (.*) workbook$")
	public void user_reads_the_excel_file(String sheetName, String fileName, String flag) throws Throwable 
	{
		String wbName = null;
		for (int i= 0; i <= DataHelper.mydata.size()-1; i++)
		{
			if (DataHelper.mydata.get(i).containsValue(flag.substring(5)))
			{
				wbName = DataHelper.mydata.get(i).get("Workbook Name");
				System.out.println("Template name is ::: "+wbName);
			}
		}
		if (sheetName.equalsIgnoreCase("WBFilterValues") || sheetName.equalsIgnoreCase("GridData"))
			DataHelper.wbData(sheetName, fileName, wbName);
		else if(sheetName.equalsIgnoreCase("WSFilterValues"))
			DataHelper.wsData(sheetName, fileName, wbName);
		else
			DataHelper.data(sheetName, fileName);
	}
	
	@And("^selects the filter values for (.*)$")
	public void selects_filter_values(String custName) throws Throwable 
	{
		CreateWorkbookPM.select_filters(driver, custName);
	}
	
	@Then("^user selects validity date values$")
	public void select_validity_date_values() throws Throwable 
	{
		CreateWorkbookPM.select_date(driver);
	}

	@Then("^user selects workspace values$")
	public void selects_workspace_values() throws Throwable 
	{
		DataHelper.data("Vendavo_TestData.xlsx", "WorkspaceValues");
		CreateWorkbookPMSyngenta.select_workspace_data(driver);
	}

	@Then("^user fills the workbook name$")
	public void user_fills_wb_name() throws Throwable
	{
		CreateWorkbookPM.fill_wb_name(driver);
	}
	
	@Then("^user selects (.*) filter value$")
	public void select_currency_filter(String filterName) throws Throwable
	{
//		DataHelper.data("WSFilterValues", "3M_TestData");
		CreateWorkbookPM.select_ws_filters(driver, filterName);
	}
	
	@And("^fills up the first row data$")
	public void fill_up_ws_data() throws Throwable
	{
		CheckWorksheets.fill_grid_data(driver);
	}
	
//	@And("^fills up the first row data for (.*)$")
//	public void price_corridor_threshold_data(String columns) throws Throwable
//	{
//		CheckWorksheets.fill_grid_data(driver, columns);
//	}
	
	@Then("^user generates the price list$")
	public void user_generates_price_list() throws Throwable
	{
		DataHelper.data("PriceListFilters", "Vendavo_TestData");
		PriceListGeneration.generate_price_list(driver);
	}
	
	@And("^user clicks on (.*) button$")
	public void button_click(String button_label) throws Throwable 
	{
		if (button_label.equalsIgnoreCase("save"))
			ButtonClickAction.save_workbook(driver);
		else if (button_label.equalsIgnoreCase("save and close"))
			ButtonClickAction.save_close_workbook(driver);
		else if (button_label.equalsIgnoreCase("submit"))
			ButtonClickAction.submit_workbook(driver);
		else if (button_label.equalsIgnoreCase("approve"))
			ButtonClickAction.approve_workbook(driver);
	}
}