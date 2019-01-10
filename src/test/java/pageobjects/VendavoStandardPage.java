package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VendavoStandardPage extends BaseClass
{
	public VendavoStandardPage(WebDriver driver) 
	{
		super(driver);
	}
//	-------------------------- Filter ----------------	
	@FindBy(how=How.XPATH, using="//td[@id='filter_VWorkbench_HeaderLabel']")
	public static WebElement filter_header;
	
	@FindBy(how=How.XPATH, using="//td[@class='VWorkbookFilter_Label']")
	public static List<WebElement> filter_label;
	
	@FindBy(how=How.XPATH, using="//td[@id='VBusinessSlice.BusinessUnit_control']")
	public static WebElement product_line_group_input;
	
	@FindBy(how=How.XPATH, using="//div[@id='VBusinessSlice.BusinessUnit_options']//tr/td")
	public static List<WebElement> plg_option_list;
	
	@FindBy(how=How.XPATH, using="//td[@id='VBusinessSlice.Country_control']")
	public static WebElement country_input;
	
	@FindBy(how=How.XPATH, using="//div[@id='VBusinessSlice.Country_options']//tr/td")
	public static List<WebElement> country_option_list;
	
	@FindBy(how=How.XPATH, using="//td[@id='VBusinessSlice.SourceSystem_control']")
	public static WebElement SAP_system_input;
	
	@FindBy(how=How.XPATH, using="//div[@id='VBusinessSlice.SourceSystem_options']//tr/td")
	public static List<WebElement> SAP_sys_option_list;
	
	public static By select_filter_control(String controlName)
	{
		By ctrl_element;
		if (controlName.contains("SoldTo"))
			ctrl_element = By.xpath("//td[@id='VBusinessSlice.V"+controlName+"_control']");
		else
			ctrl_element = By.xpath("//td[contains(@id, 'BusinessSlice."+controlName+"_control')]");
		
		return ctrl_element;
	}
	
	public static By select_filter_value(String fltrValue)
	{
		By fltr_value;
		if (fltrValue.contains("SoldTo"))
			fltr_value = By.xpath("//div[@id='VBusinessSlice.V"+fltrValue+"_options']//tr/td");
		else
			fltr_value = By.xpath("//div[contains(@id, 'BusinessSlice."+fltrValue+"_options')]//tr/td");
	
		return fltr_value;
	}
	
	@FindBy(how=How.XPATH, using="//tr[@class='VGridA_COLHDR']//td[@class='VGridA_FACTHDR_LABEL']")
	public static List<WebElement> column_header;

	// Changed for demo
	@FindBy(how=How.XPATH, using="//div[@class='VGridA_DATA']//input[contains(@name, 'INPUT_xr0_f')][@type='text']")
	public static List<WebElement> input_columns;
	
	@FindBy(how=How.XPATH, using="//input[contains(@name, 'xr0_f0.VAmount.amount')]")
	public static WebElement upper_price_threshold;
	
	@FindBy(how=How.XPATH, using="//input[contains(@name, 'xr0_f1.VAmount.amount')]")
	public static WebElement lower_price_threshold;
	
	public static By country_list_price_element = By.xpath("//input[contains(@name, 'xr0_f1')]");
	
	@FindBy(how=How.XPATH, using="//input[contains(@id, 'xr0_f1')]")
	public static WebElement country_list_price;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id, 'xr0_f2')]")
	public static WebElement max_discount;
	
//	@FindBy(how=How.XPATH, using="//div[@class='VXTableA_DATA']/")
//	-------------------------
	
	@FindBy(how=How.XPATH, using="//span[@id='VBusinessSlice.Global']")
	public static WebElement global_value;
	
	@FindBy(how=How.XPATH, using="//span[@id='VBusinessSlice.Region']")
	public static WebElement region_value;
	
	@FindBy(how=How.XPATH, using="//span[@id='VBusinessSlice.Territory']")
	public static WebElement territory_value;

	//	---------------------------- Workspace ------------------
	
	@FindBy(how=How.XPATH, using="//tr[@id='ROW_VValiditySpec']//a[@title='Date Picker']")
	public static WebElement date_picker;
	
	public static By date_picker_popup = By.xpath("//div[@class='VxDatePicker_INFO_BORDER']");
	
	@FindBy(how=How.XPATH, using="//td[contains(@id, 'dateRange_from_value')]//input[@type='text']")
	public static WebElement from_date;
	
	@FindBy(how=How.XPATH, using="//td[contains(@id, 'dateRange_to_value')]//input[@type='text']")
	public static WebElement to_date;
//	-----------------------------------
	
	@FindBy(how=How.XPATH, using="//td[contains(@id,'VPricingCurrency_control')]")
	public static WebElement price_setting_currency_input;
	
	@FindBy(how=How.XPATH, using="//div[contains(@id,'VPricingCurrency_options')]//tr/td")
	public static List<WebElement> price_setting_currency_list;
	
	@FindBy(how=How.XPATH, using="//td[contains(@id,'VExchangeRateType_control')]")
	public static WebElement price_setting_exchange_rate_input;
	
	@FindBy(how=How.XPATH, using="//div[contains(@id,'VExchangeRateType_options')]//tr/td")
	public static List<WebElement> price_setting_exchange_rate_list;
	
	@FindBy(how=How.XPATH, using="//td[contains(@id,'PublishSAPCurrency_control')]")
	public static WebElement publish_SAP_currency_input;
	
	@FindBy(how=How.XPATH, using="//div[contains(@id,'PublishSAPCurrency_options')]//tr/td")
	public static List<WebElement> publish_SAP_currency_list;
	
	@FindBy(how=How.XPATH, using="//td[contains(@id,'PublishSAPExchangeRate_control')]")
	public static WebElement publish_SAP_exchage_rate_input;
	
	@FindBy(how=How.XPATH, using="//div[contains(@id,'PublishSAPExchangeRate_options')]//tr/td")
	public static List<WebElement> publish_SAP_exchage_rate_list;	
}
