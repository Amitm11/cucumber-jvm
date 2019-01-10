package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class VendavoCreateWorkbookPage extends BaseClass
{
	public VendavoCreateWorkbookPage(WebDriver driver) 
	{
		super(driver);
	}
	
	static String value = null;

	public static By table_title_present = By.xpath("//div[@id='Workbooks_GRIDBOX']");
	
	@FindBy(how=How.XPATH, using="//div[@id='Workbooks_GRIDBOX']")
	public static WebElement table_title;
	
	@FindBy(how=How.XPATH, using="//span[contains(@id,'NewMenus')]")
	public static WebElement new_menu;
	
	@FindBy(how=How.XPATH, using="//td/nobr[contains(text(), 'New Workbook From Template')]")
	public static WebElement new_wb_from_template;
	
	@FindBy(how=How.XPATH, using="//iframe[@class='VDialogPage']")
	public static WebElement frame_1;
	
//	@FindBy(how=How.XPATH, using="//td[@class='VXTableA_CELL']//span[@title='Price Setting']")
//	public static WebElement template_for_selection;
	
	@FindBy(how=How.XPATH, using="//span[contains(@onclick,'VxDatePicker')]//span[contains(text(),'Ok')]")
	public static WebElement Date_Picker_OK_button;	
	
	@FindBy(how=How.XPATH, using="//span[contains(text(),'Apply')]")
	public static WebElement apply_button;	
	
	public static By header_label = By.xpath("//td[@id='filter_VWorkbench_HeaderLabel']");
	
	public static By v_id = By.xpath("//input[@id='VID']");
	
	@FindBy(how=How.XPATH, using="//input[@id='VID']")
	public static WebElement wb_id;
	
	@FindBy(how=How.NAME, using="VName")
	public static WebElement wb_name;
	
//	--------------------------- Worksheets -------------------
	
	@FindBy(how=How.ID, using="WorkbookElements.WorkbookElements")
	public static List<WebElement> worksheet_list;
	
	@FindBy(how=How.XPATH, using="//div[@id='WorkbookElements.WorkbookElements']//span[contains(@id,'action')]/span[@class='VTree_label']")
	public static List<WebElement> worksheet_list_data;
	
//	@FindBy(how=How.XPATH, using="//div[@id='ApplyVBLFNPIPricingWorkbookList_DATA']//span")
//	public static List<WebElement> workbook_list;
//	
//	@FindBy(how=How.XPATH, using="//div[@id='ApplyVBLFNPIPricingWorkbookList_DATA']//span[contains(text(),'Copy of Copy of Price Setting-30Aug')]")
//	public static WebElement workbook;
	
	public static By selector_section = By.xpath("//td[preceding-sibling::td[@title='Selector'] and following-sibling::td[contains(@style,'DISPLAY: none')]]");
	
	@FindBy(how=How.XPATH, using="//td[preceding-sibling::td[@title='Selector'] and following-sibling::td[contains(@style,'DISPLAY: none')]]")
	public static WebElement selector_minimize;
	
	@FindBy(how=How.XPATH, using="//td[contains(@style,'VERTICAL-ALIGN: top')]//table[@class='VGridA']//tr/td[@class='VGridA_ROWHDR']")
	public static WebElement row_header;
	
	@FindBy(how=How.XPATH, using="//div[@class='zpMenu-top zpMenuContainer zpMenu-vertical-mode'][contains(@style, 'DISPLAY: block')]//td[@class='zpMenu-label']//span[@id='VGrid_dimensionValueMenu.Expand All Rows_SPAN']/a")
	public static WebElement expand_all;
	
	public static By node_collapse_img = By.xpath("//td[@class='VGridA_ROWHDR']/img[contains(@onclick,'nodeExpand')]");
	
	public static By node_expand_img = By.xpath("//td[@class='VGridA_ROWHDR']/img[contains(@onclick,'nodeCollapse')]");
	
//	---------------- Price List Generation ----------------
	
	public static By product_filter = By.xpath("//div[@class='VXTableA_TABLEHDR']//span[contains(text(),'Product Hierarchy')]");
	
	@FindBy(how=How.XPATH, using="//span[@id='selectEntities_ProductHierarchy_label']")
	public static WebElement add_product_button;
	
	@FindBy(how=How.ID, using="VCommonBlockerVxPHProductSelector_DIALOG")
	public static WebElement product_selector_dialog;
	
	@FindBy(how=How.XPATH, using="//div[@id='VxPHProductSelector_DIALOG']//input[@id='VxPHProductSelector_SEARCHBOX']")
	public static WebElement product_search_box;
		
	@FindBy(how=How.XPATH, using="//div[@class='VxSelector_LIST']//td[@class='VxSelector_NODE']/span")
	public static WebElement select_product_from_list;
	
	@FindBy(how=How.ID, using="VxPHProductSelector_default_ADD_IMG")
	public static WebElement add_selected_product;
	
	@FindBy(how=How.XPATH, using="//div[@class='VxSelector_RESULTLIST']//td[@class='VxSelector_NODE']/span")
	public static WebElement selected_product_result_list;
	
	@FindBy(how=How.XPATH, using="//span[@id='apply']//span[contains(text(),'OK')]")
	public static WebElement product_selector_OK;
	
	@FindBy(how=How.XPATH, using="//div[@class='VXTableA_DATA']//td[@class='VXTableA_CELL']/span")
	public static List<WebElement> product_list;
	
	@FindBy(how=How.XPATH, using="//div[@class='VXTableA_DATA']//td[@class='VXTableA_CELL']/span[contains(text(),'Crop Protection (excl. PP)')]")
	public static WebElement select_default_product;
	
	public static By default_product = By.xpath("//div[@class='VXTableA_DATA']//td[@class='VXTableA_CELL']/span[contains(text(),'Crop Protection (excl. PP)')]");
	
	@FindBy(how=How.XPATH, using="//div[@class='zpMenu-top zpMenuContainer zpMenu-vertical-mode'][contains(@style, 'DISPLAY: block')]//td[@class='zpMenu-label']//a[@id='deleteSelectedEntity']")
	public static WebElement delete_selected_product;
	
	@FindBy(how=How.ID, using="generateSchedule_label")
	public static WebElement generate_schedule_button;
	
	@FindBy(how=How.ID, using="vrefresh_label")
	public static WebElement refresh_button;
	
	@FindBy(how=How.XPATH, using="//span[@id='saveAndNoClose_label']")
	public static WebElement save_button;
	
	@FindBy(how=How.XPATH, using="//span[@id='save_label']")
	public static WebElement save_close_button;
	
	@FindBy(how=How.XPATH, using="//span[@id='submit_label']")
	public static WebElement submit_button;
	
	@FindBy(how=How.XPATH, using="//span[@id='approve_label']")
	public static WebElement approve_button;	
	
	@FindBy(how=How.XPATH, using="//div[@class='VXTableA_COLHDR']")
	public static WebElement table_col_header;
	
	public static By table_header = By.xpath("//div[@class='VXTableA_COLHDR']");
	
	public static By pl_locked_generation = By.xpath("//span[contains(text(), 'The price list is locked for generation.')]");
	
//	--------------------------- 3M and General ------------------
	
	public static By M_root_expand(String elementText)
	{
		final By M_root_toggle = By.xpath("//span[contains(@onclick, '"+elementText+"')]//preceding-sibling::span[contains(@id, 'toggle')]");
		return M_root_toggle;
	}
	
	public static By M_sub_child_element(String elementText)
	{
		final By M_sub_child = By.xpath("//span[@title='"+elementText+"']");
		return M_sub_child;
	}
	
	public static By M_template_selection(String elementText)
	{
		final By template_select_element = By.xpath("//td[@class='VXTableA_CELL']//span[contains(@title, '"+elementText+"')]");
		return template_select_element;
	}	
}
