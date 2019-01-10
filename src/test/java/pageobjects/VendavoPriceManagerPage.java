package pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VendavoPriceManagerPage extends BaseClass
{
	public VendavoPriceManagerPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(how=How.XPATH, using="//div[@id='VxFolderNav_managePrices']//div[contains(@id, 'root.children')]//div//span[@class='VTree_label']")
	public static List<WebElement> folderNav_root_children_PM;
	
	@FindBy(how=How.XPATH, using="//div[@id='VxFolderNav_managePrices']//div[@id='VTree_VxFolderNav_managePrices.1.children']/div//span[@class='VTree_label']")
	public static List<WebElement> folderNav_children1_PM;
	
	@FindBy(how=How.XPATH, using="//div[@id='VxFolderNav_managePrices']//div[@id='VTree_VxFolderNav_managePrices.2.children']/div/span")
	public static List<WebElement> folderNav_children2_PM;
	
	@FindBy(how=How.XPATH, using="//div[@id='VxFolderNav_managePrices']//div[contains(@id, 'root.children')]/div/img[@class='VTree_toggle']")
	public static List<WebElement> tree_toggle;
}
