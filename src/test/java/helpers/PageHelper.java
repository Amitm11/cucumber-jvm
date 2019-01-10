package helpers;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;

import pageobjects.VendavoCommonPage;

public class PageHelper 
{
	static int i, j;
	
	public static void waitForPageLoad(WebDriver driver) throws Exception
	{ 
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(new ExpectedCondition<Boolean>()
		{
			public Boolean apply(WebDriver driver)
			{
				((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				System.out.println("Page loaded successfully");
				return true;
			}
		});
	}
	
	public static boolean isElementPresent(WebDriver driver, By by) 
	{
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		try 
	    {
			driver.findElement(by);
	    } catch (org.openqa.selenium.NoSuchElementException e) 
	    {
	    	return false;
	    } finally {
	    	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);  
	    }
		return true;
	}
	
	public static void isPageLoaded(WebDriver driver) throws Throwable
	{
		System.out.println("Page load is in progress");
		for (int r = 0; ;r++)
		{
			if (!isElementPresent(driver, VendavoCommonPage.page_load))
				Thread.sleep(1000);
			else
			{
				System.out.println((r+1) +" - Page load processed");
				break;
			}
		}
	}
	
	public static void isRequestingDataPresent(WebDriver driver) throws Throwable
	{
		System.out.println("Requesting data is in progress");
		for (int r = 0; ;r++)
		{
			if (driver.findElement(VendavoCommonPage.req_data).isDisplayed())
				Thread.sleep(1000);
			else
			{
				System.out.println((r+1) +" - Requesting data processed");
				break;
			}
		}
	}
	
	public static void isInitializeTablePresent(WebDriver driver) throws Throwable
	{
		System.out.println("Initializing table is in progress");
		for (int r = 0; ;r++)
		{
			if (driver.findElement(VendavoCommonPage.init_table).isDisplayed())
				Thread.sleep(1000);
			else
			{
				System.out.println((r+1) +" - Initializing table processed");
				break;
			}
		}
	}
	
	public static void isLoadingPresent(WebDriver driver) throws Throwable
	{
		System.out.println("Loading list is in progress");
		for (int r = 0; ;r++)
		{
			if (isElementPresent(driver, VendavoCommonPage.loading))
				Thread.sleep(1000);
			else
			{
				System.out.println((r+1) +" - Loading list processed");
				break;
			}
		}
	}
	
	public static void isApplyingFilterPresent(WebDriver driver) throws Throwable
	{
		for (int r = 0; ;r++)
		{
			if (driver.findElement(VendavoCommonPage.apply_filter).isEnabled())
			{
				System.out.println(r+" - Filter is still applying");
				Thread.sleep(1000);
			}
			else
				break;
		}
	}
	
	public static void isInitializeGridPresent(WebDriver driver) throws Throwable
	{
		System.out.println("Initializing grid is in progress");
		for (int r = 0; ;r++)
		{
			if (isElementPresent(driver, VendavoCommonPage.init_grid))
				Thread.sleep(1000);
			else
			{
				System.out.println((r+1) +" - Initializing grid progressed");
				break;
			}
		}
	}
	
	public static void isLoadingGridCellsPresent(WebDriver driver) throws Throwable
	{
		System.out.println("Loading grid data is in progress");
		for (int r = 0; ;r++)
		{
			if (driver.findElement(VendavoCommonPage.load_grid_data).isDisplayed())
				Thread.sleep(1000);
			else
			{
				System.out.println((r+1) +" - Loading grid data progressed");
				break;
			}
		}
	}
	
	public static void waitForGridDataLoad(WebDriver driver) throws Throwable
	{
		for (int r = 0; ;r++)
		{
			if (isElementPresent(driver, VendavoCommonPage.init_grid))
			{
				System.out.println((r+1) +" - Initialize grid data is in progress");
				Thread.sleep(1000);
			}
			else if (isElementPresent(driver, VendavoCommonPage.load_grid_data))
			{
				System.out.println((r+1) +" - Grid data loading is in progress");
				Thread.sleep(1000);
			}
			else
				break;
		}
	}
	
	public static void takeEvidence(WebDriver driver, String scrName) throws IOException, InvalidFormatException, AWTException
    {
		File scrFile;
		String path, timestamp, filePath;
		
		Date date = new Date();
		timestamp = new SimpleDateFormat("MMMdd").format(date);
	    filePath = "./target/screenshots/" + timestamp;
		new File(filePath).mkdir();
		
		if (scrName.contains("PopUp"))
		{
			System.out.println("Robot is taking screenshot");
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		    Robot robot = new Robot();
		    BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
		    scrFile = new File(scrName);
		    ImageIO.write(screenFullImage, "png", scrFile);
		}
		else
		{
			TakesScreenshot scrShot = ((TakesScreenshot)driver);
			scrFile = scrShot.getScreenshotAs(OutputType.FILE);
		}	    
				
		if(filePath.contains(timestamp))
		{
			i = j + 1;
			j = i;
		}
		path = filePath + "/" + i + ". "+ scrName.replace(" ", "_") + ".png";
	    System.out.println(path);
	    System.out.println("scrName ::: " + i + ". "+ scrName.replace(" ", "_"));
	    	
	    File destFile = new File(path);
	    FileUtils.copyFile(scrFile, destFile);
	    System.out.println("Copied the image file.");
    }
	
//	public static void takePopUpScreenshot(WebDriver driver, String scrName) throws IOException, InvalidFormatException
//	{
//		try
//		{
//		    Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//		    Robot robot = new Robot();
//		    BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
//		    File scrFile = new File(scrName);
//		    ImageIO.write(screenFullImage, "png", scrFile);
//		    String path;
//		    path = "./target/screenshots/Dec17" + "/"+ scrName.replace(" ", "_") + ".png";
//		    System.out.println(path);
//		    System.out.println("scrName ::: "+ scrName.replace(" ", "_"));
//		    	
//		    File destFile = new File(path);
//		    FileUtils.copyFile(scrFile, destFile);
//		    System.out.println("Copied the image file.");
//		} catch (AWTException | IOException ex) {
//			System.err.println(ex);
//		}
//
//	}
	
//----------- Coping the evidence screenshots to a Word file ------------------------
	
	 public static void createEvidenceFile() throws Throwable
	 {
		Date date = new Date();
		String timestamp = new SimpleDateFormat("MMMdd").format(date);
		String filePath = "./target/screenshots/" + timestamp;
		   
//		Here sorting screenshots based on last modified data
	   	File evdFile = new File(filePath);
		File[] files = evdFile.listFiles();
		Arrays.sort(files, new Comparator<File>() {
			public int compare(File f1, File f2)
			{
				return Long.valueOf(f2.lastModified()).compareTo(f1.lastModified());
			}
		});
		System.out.println("Number of screenshot file ::: "+files.length);
		
		XWPFDocument docx = new XWPFDocument();
		XWPFStyles styles = docx.createStyles();
    	styles.setSpellingLanguage("English");
    	CTFonts def = CTFonts.Factory.newInstance();
    	styles.setDefaultFonts(def);
		XWPFParagraph para = docx.createParagraph();
		XWPFRun run = para.createRun();
		System.out.println("Smoke Test Evidence file is opened");
	    run.setText("Smoke Test Evidence");
		run.setFontSize(18);
		FileOutputStream out = new FileOutputStream("D:\\SeleniumInstallables\\SmokeTestEvidence.docx");
	   	docx.write(out);
	   	System.out.println("Pic Type ::: "+Document.PICTURE_TYPE_PNG);
		out.close();
//		docx.close();
		
		CustomXWPFDocument document = null;
		FileOutputStream fos = null;
		
		for (int i = files.length; i > 0; i--)
		{
//			Writing screenshot name before the image
			System.out.println("Image file name is ::: "+files[(i-1)]);
			String[] fname = files[(i-1)].getName().split(filePath);
			System.out.println("fname ::: "+fname[0]);
			
//			Adding image for the above mentioned file name
			document = new CustomXWPFDocument(new FileInputStream(new File("D:\\SeleniumInstallables\\SmokeTestEvidence.docx")));
			XWPFStyles style2 = document.createStyles();
	    	style2.setSpellingLanguage("English");
	    	CTFonts def2 = CTFonts.Factory.newInstance();
	    	style2.setDefaultFonts(def2);
			XWPFParagraph paragraph =  document.createParagraph();
			XWPFRun docRun = paragraph.createRun();
			docRun.setText(fname[0]);
			docRun.setFontSize(12);
			fos = new FileOutputStream("D:\\SeleniumInstallables\\SmokeTestEvidence.docx");
			String id = document.addPictureData(new FileInputStream(files[(i-1)]), 6);
			document.createPicture(id, 6, 680, 350);
			document.write(fos);
			System.out.println("Added image ::: "+(i-1));
			docRun.addBreak();
		}
		fos.flush();
		fos.close();
		out.close();
    }
	
//------------- Deleting the existing files from the directory ------------ 
	 public static void Deletefiles() throws Throwable
	{
		String filePath, timestamp;
		Date date = new Date();
		timestamp = new SimpleDateFormat("MMMdd").format(date);
	    filePath = "./target/screenshots/" + timestamp;
		File file = new File(filePath);
		
		String[] evdFiles;
		if (file.isDirectory())
		{
			evdFiles = file.list();
			System.out.println("number of files ::: "+evdFiles.length);
			for (int i = 0; i < evdFiles.length; i++)
			{
				File evdFile = new File(file, evdFiles[i]);
				System.out.println("File to be deleted ::: "+evdFile);
				if (!evdFile.isDirectory())
				{
					evdFile.delete();
					System.out.println("File deleted");
				}
			}
		}
		i = 0;
	}
}
