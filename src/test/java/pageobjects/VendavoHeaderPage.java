package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VendavoHeaderPage extends BaseClass
{
	public VendavoHeaderPage(WebDriver driver)
	{
		super(driver);
	}    
	
//	------------------ Right Header -------------------------------------------------------------
	
	@FindBy(how=How.XPATH, using="//td[contains(@class,'VxGlobalNav-HeaderRight')]")
	public static WebElement header_right;
	
	@FindBy(how=How.XPATH, using="//td[contains(@class,'VxGlobalNav-HeaderRight')]/span")
	public static List<WebElement> header_right_menu;
	
	@FindBy(how=How.XPATH, using="//td[contains(@class,'VxGlobalNav-HeaderRight')]//span[contains(text(),' VMP')]")
	public static WebElement hearder_right_vmp;
	
	@FindBy(how=How.XPATH, using="//td[contains(@class,'VxGlobalNav-HeaderRight')]//span[contains(text(),'OneVendavo')]")
	public static WebElement header_right_one_vendavo;
	
	@FindBy(how=How.XPATH, using="//td[contains(@class,'VxGlobalNav-HeaderRight')]//span[contains(text(),'Account Settings')]")
	public static WebElement header_right_account_settings;
	
	@FindBy(how=How.XPATH, using="//td[contains(@class,'VxGlobalNav-HeaderRight')]//span[contains(text(),'Sign Out')]")
	public static WebElement header_right_sign_out;
	
//	------------------ Left Header ---------------------------------------------------------------
	
	@FindBy(how=How.XPATH, using="//td[contains(@class,'VxGlobalNav-HeaderLeft')]")
	public static WebElement header_left;
	
	@FindBy(how=How.XPATH, using="//span[contains(@class,'VxGlobalNav-HeaderMenu ')]")
	public static List<WebElement> header_left_menu;
}
		

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	