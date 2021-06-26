package fileReadingPkg;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Hashtable;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

public class DataUtil {

	public static Object[][] getData(Xls_Reader xls, String testCaseName){
		String sheetName=Constants.TESTDATA_SHEET;
		//String sheetName=testCaseName;
		// reads data for only testCaseName
		
		int testStartRowNum=1;
		while(!xls.getCellData(sheetName, 0, testStartRowNum).equals(testCaseName)){
			testStartRowNum++;
		}
		System.out.println(xls.getCellData(sheetName, 0, testStartRowNum)); 
		System.out.println("Test starts from row - "+ testStartRowNum);
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		// calculate rows of data
		int rows=0;
		while((!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals(""))&&(xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("Y"))){
			rows++;
		}
		System.out.println("Total rows are  - "+rows );
		
		//calculate total cols
		int cols=0;
		while(!xls.getCellData(sheetName, cols, colStartRowNum).equals("")){
			cols++;
		}
		System.out.println("Total cols are  - "+cols );
		Object[][] data = new Object[rows][1];
		//read the data
		int dataRow=0;
		Hashtable<String,String> table=null;
		for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
			table = new Hashtable<String,String>();
			for(int cNum=0;cNum<cols;cNum++){
				String key=xls.getCellData(sheetName,cNum,colStartRowNum);
				String value= xls.getCellData(sheetName, cNum, rNum);
				table.put(key, value);
				// 0,0 0,1 0,2
				//1,0 1,1
			}
			data[dataRow][0] =table;
			dataRow++;
		}
		return data;
	}
	
	public static boolean isTestExecutable(Xls_Reader xls, String testCaseName){
		int rows = xls.getRowCount(Constants.TESTCASES_SHEET);
		for(int rNum=2;rNum<=rows;rNum++){
			String tcid = xls.getCellData(Constants.TESTCASES_SHEET, "TCID", rNum);
			//if(tcid.equals(testCaseName)){
			if(tcid.contains(testCaseName)){
				String runmode = xls.getCellData(Constants.TESTCASES_SHEET, "Runmode", rNum);
				if(runmode.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
	}
	
	public static void exportResultsToExcel(String filePath, String policyNum) throws Exception{
	       
		  String excelFilePath = filePath;
		  File file = new File(filePath);
		  		  
		  try {
		                    
		       if(!file.exists()){
		         if (excelFilePath.endsWith(".xlsx"))
		               
		         {
		               XSSFWorkbook workbook = new XSSFWorkbook();
		               XSSFSheet sheet = workbook.createSheet("Sheet1");
		               int rowNum = 0;
		               XSSFRow row = sheet.createRow(rowNum++);
		               Cell cell = row.createCell(0);
		               cell.setCellValue("UniqueID");
		               Cell cell1 = row.createCell(1);
		               cell1.setCellValue("PolicyNo");
		               
		               int rowCount = sheet.getLastRowNum();
		               row = sheet.createRow(++rowCount);
		               cell = row.createCell(0);
		               cell.setCellValue(rowCount);
		               cell = row.createCell(1);
		               cell.setCellValue(policyNum);
		               //inputStream.close();
		               FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		               workbook.write(outputStream);
		               outputStream.close();
		               
		               
		         }else {
		               HSSFWorkbook workbook= new HSSFWorkbook();
		               HSSFSheet sheet=workbook.createSheet("Sheet1");
		               int rowNum = 0;
		               Row row = sheet.createRow(rowNum++);
		               Cell cell = row.createCell(0);
		               cell.setCellValue("UniqueID");
		               Cell cell1 = row.createCell(1);
		               cell1.setCellValue("PolicyNo");
		               int rowCount = sheet.getLastRowNum();
		               
		               row = sheet.createRow(++rowCount);
		               cell = row.createCell(0);
		               cell.setCellValue(rowCount);
		               cell = row.createCell(1);
		               cell.setCellValue(policyNum);
		               //inputStream.close();
		               FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		               workbook.write(outputStream);
		               outputStream.close();
		              
		         }
		       }
		         else{
		             
		             FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		             Workbook workbook = WorkbookFactory.create(inputStream);
		             Sheet sheet = workbook.getSheetAt(0);
		             int rowCount = sheet.getLastRowNum();
		             Row row = sheet.createRow(++rowCount);
		             Cell cell = row.createCell(0);
		             cell.setCellValue(rowCount);
		             cell = row.createCell(1);
		             cell.setCellValue(policyNum);
		             inputStream.close();
		             FileOutputStream outputStream = new FileOutputStream(excelFilePath);
		             workbook.write(outputStream);
		             outputStream.close();
		             
		      }
		  } catch (Exception e) {
		       
		      throw new Exception("Failed to export results");
		  }

		  }
	
}
