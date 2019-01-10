package helpers;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

public class DataHelper 
{
	public static List<HashMap<String,String>> mydata = new ArrayList<HashMap<String, String>>();
	public static String header;
	
	public static void data(String sheetName, String fileName)
	{
//		List<HashMap<String,String>> mydata = new ArrayList<HashMap<String, String>>();
		
		try
		{
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//"+"src//test//resources//testData//"+fileName+".xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);
			for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
			{
				Row currentRow = sheet.getRow(i);
				HashMap<String,String> currentHash = new HashMap<String,String>();
				for(int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++)
				{
					Cell currentCell = currentRow.getCell(j);
					System.out.print("Current Cell Value ::: "+currentCell.getStringCellValue() + "\t");
					String header = HeaderRow.getCell(j).getStringCellValue();
					currentHash.put(header, currentCell.getStringCellValue());
				}
				if (currentHash.containsValue("Y") || !currentHash.containsKey("Flag"))
				{
					mydata.add(currentHash);
					System.out.println("\nMyData values are ::: "+mydata);
				}
			}
			workbook.close();
			fs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void wbData(String sheetName, String fileName, String wbName)
	{
		try
		{
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//"+"src//test//resources//testData//"+fileName+".xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
//			Row HeaderRow = sheet.getRow(0);
			System.out.println("Total number of Rows ::: " +sheet.getPhysicalNumberOfRows());
			
			for(int i = 0; i < sheet.getPhysicalNumberOfRows(); i = i+4)
			{
				Row wbRow = sheet.getRow(i);
				System.out.println("WB name row ::: "+wbRow.getCell(0).getStringCellValue());
				
				if (wbRow.getCell(0).getStringCellValue().contains(wbName))
				{
					Row currentRow = sheet.getRow(i+1);
					HashMap<String, String> currentHash = new HashMap<String, String>();
					
					for(int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++)
					{
						Row currentNewRow = sheet.getRow(i+2);
						Cell currentCell = currentNewRow.getCell(j);
	
						switch (currentCell.getCellType())
						{
							case Cell.CELL_TYPE_STRING:
							System.out.print("Current Cell Value ::: "+currentCell.getStringCellValue() + "\t");
							currentHash.put(currentRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
						}
					}
					mydata.add(currentHash);
					System.out.println("\nMyData values are ::: "+mydata);
					break;
				}
			}
			workbook.close();
			fs.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void wsData(String sheetName, String fileName, String wbName)
	{
		try
		{
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"//"+"src//test//resources//testData//"+fileName+".xlsx");

			XSSFWorkbook workbook = new XSSFWorkbook(fs);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			Row HeaderRow = sheet.getRow(0);
			System.out.println("Total number of Rows ::: " +sheet.getPhysicalNumberOfRows());
			
			for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++)
			{
				Row currentRow = sheet.getRow(i);
				HashMap<String,String> currentHash = new HashMap<String,String>();
				for(int j = 0; j < currentRow.getPhysicalNumberOfCells(); j++)
				{
					Cell currentCell = currentRow.getCell(j);

					switch (currentCell.getCellType())
					{
						case Cell.CELL_TYPE_STRING:
							System.out.print("Current Cell Value ::: "+currentCell.getStringCellValue() + "\t");
							currentHash.put(HeaderRow.getCell(j).getStringCellValue(), currentCell.getStringCellValue());
							break;
					}
				}
				mydata.add(currentHash);
				System.out.println("\nMyData values are ::: "+mydata);
			}
			workbook.close();
			fs.close();
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	
	public static void writeToDoc(WebDriver driver) throws Throwable
	{
		String filename = "./target/screenshots/SmokeTest.docx";
		BufferedWriter bw = null;
		FileWriter fw = null;
		fw = new FileWriter(filename);
		bw = new BufferedWriter(fw);
		bw.write(filename);
		bw.close();
		fw.close();
	}
}
