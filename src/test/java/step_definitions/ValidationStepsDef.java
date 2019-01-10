package step_definitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.And;
import pageobjects.VendavoCommonPage;
import pageobjects.VendavoCreatePriceSettingPage;
import pageobjects.VendavoCreateWorkbookPage;
import pageobjects.VendavoHeaderPage;
import pageobjects.VendavoPriceManagerPage;
import pageobjects.VendavoStandardPage;

public class ValidationStepsDef
{
	public WebDriver driver;

	public ValidationStepsDef() 
	{
		driver = Hooks.driver;
		PageFactory.initElements(driver, VendavoCreatePriceSettingPage.class);
		PageFactory.initElements(driver, VendavoCreateWorkbookPage.class);
		PageFactory.initElements(driver, VendavoStandardPage.class);
		PageFactory.initElements(driver, VendavoCommonPage.class);
		PageFactory.initElements(driver, VendavoHeaderPage.class);
		PageFactory.initElements(driver, VendavoPriceManagerPage.class);
	}
	
	@And("^captures the error message and report it$")
	public void capture_error_message_submit() throws Throwable
	{
		
	}
}
