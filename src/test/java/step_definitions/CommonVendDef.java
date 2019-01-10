package step_definitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import cucumber.api.java.en.Then;
import helpers.CustomXWPFDocument;
import helpers.EmailHelper;
import helpers.EvidenceDocumentHelper;
import pageobjects.VendavoCommonPage;

public class CommonVendDef 
{
	public WebDriver driver;

	public CommonVendDef()
	{
		driver = Hooks.driver;
		PageFactory.initElements(driver, VendavoCommonPage.class);
	}
	
	@Then("^user creates evidence file$")
	public void create_evidence_file() throws Throwable
	{
//		PageHelper.createEvidenceFile();
		EvidenceDocumentHelper.createEvidenceFile(driver);
	}
	
	@Then("^convert word file to pdf file$")
	public void word_to_pdf() throws Throwable
	{
		CustomXWPFDocument.convertDocToPdf();
	}
	
	@Then("^user sends email to stakeholders with attachment$")
	public void send_email_with_attachment() throws Throwable
	{
		EmailHelper.sendEmail("D:\\SeleniumInstallables\\SmokeTestEvidence.pdf");
//		if(ButtonClickAction.err_count > 0)
//			Assert.assertTrue(false);
	}
}
