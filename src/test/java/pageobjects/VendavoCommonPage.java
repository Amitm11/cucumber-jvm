package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VendavoCommonPage extends BaseClass
{
	public VendavoCommonPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public static By loading = By.xpath("//div[@class='VxType_float']//span[contains(text(),'Loading...')]");
	
	public static By req_data = By.xpath("//td[contains(text(),'Requesting data')]");
	
	public static By init_table = By.xpath("//td[contains(text(),'Initializing table')]");
	
	public static By apply_filter = By.xpath("//td[contains(text(),'Applying filter')]");
	
	public static By init_grid = By.xpath("//td[contains(text(),'Initializing grid')]");
	
	public static By load_grid_data = By.xpath("td[contains(text(),'Loading grid cells')]");
	
	public static By page_load = By.xpath("//td[contains(@class,'VxGlobalNav-HeaderLeft')]");
	
	public static By msg_box = By.xpath("//table[@id='VMessageBox']");
	
	public static List<WebElement> msg_text_elements = driver.findElements(By.xpath("//table[@id='VMessageBox']//tr//b"));
	
	@FindBy(how=How.XPATH, using = "//li[@class='VPageErrorMessage']")
	public static WebElement msg_text;
	
	public static By existing_workbook_select(String wb_id) throws Throwable
	{
		By workbook_id = By.xpath("//td[@class='VXTableA_CELL']/span[text()='"+wb_id+"']");
		return workbook_id;
	}
//	@FindBy(how=How.XPATH, using = "//td[@class='VXTableA_CELL']/span")
//	public static WebElement workbook_id;
	
	@FindBy(how=How.XPATH, using = "//div[@class='VWorkbench_Header'][@minimize='true']//img[@title='Restore']")
	public static WebElement tools_restore;
	
	@FindBy(how=How.XPATH, using = "//td[text()='Approvals']")
	public static WebElement approval_tab;
	
	@FindBy(how=How.XPATH, using = "//table[@class='VApprovalMap_thread']//td[@class='VApprovalMap_approval']")
	public static List<WebElement> approval_list;
	
	@FindBy(how=How.XPATH, using = "//table[@class='VApprovalMap_thread']//td[@class='VApprovalMap_approval']//div[@class='VApprovalMap_user']")
	public static List<WebElement> approval_user;
}
