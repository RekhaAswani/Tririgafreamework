package Tririga_PagesPkg;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebUtilities;



public class ModificationHistoryPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	
	public static final String CriticalReport_MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	
		
	@FindBy(xpath="//thead[@id='fh-header']//th[@name='th-parent']//div[@name='th-child']")
	public static WebElement TableColumnHeaders;
	
	public String[] ColumnNames={"Contract ID","Contract Name","Revision Date",
								"Change Type","Description","Revised By"};
		
	public ModificationHistoryPage(WebDriver driver,ExtentTest logger){
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);
	}
	
	public boolean VerifyModificationHistoryReportColumnNames() {
		
		boolean Status=false;
		boolean NotFoundStatus=true;
		
		try{
			
			WebFrame.switchOnMultipleFramesByXpath(driver, CriticalReport_MainFrame);
			WebUtilities.waitForElementToAppear(driver, TableColumnHeaders, logger);
			List<WebElement> TotalCols= driver.findElements(By.xpath("//thead[@id='fh-header']//th[@name='th-parent']//div[@name='th-child']"));
			System.out.println("Total Column nos="+TotalCols.size());
			int i=0;
			for(WebElement colname:TotalCols){
				String ColName=colname.getText();
				if(ColumnNames[i].equalsIgnoreCase(ColName)){
					logger.log(LogStatus.PASS, "Verify Column Name:<br></br>"
							+ "Expected Column Name="+ColumnNames[i]+"<br></br>"
							+ "Actual Column Name="+ColName);
					Status=true;
				}else{
					logger.log(LogStatus.FAIL, "Verify Column Name:<br></br>"
							+ "Expected Column Name="+ColumnNames[i]+"<br></br>"
							+ "Actual Column Name="+ColName);
					NotFoundStatus=false;
				}
				i=i+1;
			}
			if (!NotFoundStatus){
				Status=false;
			}
		}catch(Exception e){
			logger.log(LogStatus.FAIL, "Failed to find the 'All Lease Contracts'" + e.getMessage());
			e.printStackTrace();
		}
		return Status;
	} 
	
	
}
