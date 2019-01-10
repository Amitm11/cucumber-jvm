package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VendavoLoginPage extends BaseClass
{
	public VendavoLoginPage(WebDriver driver)
	{
		super(driver);
	}    

	public static By quick_login_element = By.xpath("//div[@class='quick-login']");
	
	@FindBy(how=How.CLASS_NAME, using="quick-login")
	public static WebElement quick_login;
	
	@FindBy(how=How.XPATH, using="//div[@class='quick-login']//li/a")
	public static List<WebElement> quick_user;
	
	@FindBy(how=How.ID, using="username")
	public static WebElement username;
	
	@FindBy(how=How.ID, using="password")
	public static WebElement password;
	
	@FindBy(how=How.NAME, using="Logon")
	public static WebElement signin_button;
	
	@FindBy(how=How.XPATH, using="//form[@name='logon']/div[contains(@class,'error invalid-account')]")
	public static WebElement login_error; 
	
	public static By login_error_element = By.xpath("//form[@name='logon']/div[contains(@class,'error invalid-account')]");
}
