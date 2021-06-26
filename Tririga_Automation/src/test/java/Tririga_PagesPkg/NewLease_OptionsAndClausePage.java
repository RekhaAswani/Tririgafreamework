package Tririga_PagesPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.MouseOperations;
import com.webElementPkg.WebBrowser;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebCheckBox;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebPage;
import com.webElementPkg.WebRadiobutton;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewLease_OptionsAndClausePage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	public WebElement leaseType;
	
	public static String ContentFrame="//iframe[@id='contentFrame']";	
	public static String ChildFrame="//iframe[@id='childFrame']";
	public static String FrameMainFrame="//frame[@id='frameMain']";
	public static String innerFrame39Frame="//iframe[@id='innerFrame39']";
	
	
	public static String SelectorFrame="unMovableLayerFrame";
	
	
	@FindBy(xpath="//span[text()='Options & Clauses']")
	public WebElement OptionsClausestab;
	
	@FindBy(xpath="//span[text()='Options']")
	public WebElement Optionstab;
	
	@FindBy(xpath="//div[@id='Create']")
	public WebElement Option_Createbtn;
	
	@FindBy(xpath="//div[@id='Exercise']")
	public WebElement Option_Exercisebtn;
	
	@FindBy(xpath="//div[@id='Expire']")
	public WebElement Option_Expirebtn;
	
	@FindBy(xpath="//*[@id='Add_39']")
	public WebElement Options_Addbtn;
	
	@FindBy(xpath="//span[text()='x']")
	public WebElement Closebtn;
	
	@FindBy(xpath="//input[@id='attr_seq_1002']")
	public WebElement Nametxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1106']")
	public WebElement OptionStartDatetxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1025']")
	public WebElement OptionEndDatetxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1070']")
	public WebElement LastDayNoticetxt;
	
	@FindBy(xpath="//input[@id='filterValue0']")
	public WebElement OptionType_Filter;
	
	@FindBy(xpath="//a[@id='attr_seq_1072_selector']")
	public WebElement OptionsTypeSelector;
	
	@FindBy(xpath="//div[@id='Submit']")
	public WebElement Submitbtn;
		
	@FindBy(xpath="//div[@id='Continue']")
	public WebElement Continuebtn;
	
	@FindBy(xpath="//img[@alt='Refresh']")
	public WebElement Refreshbtn;
	
	@FindBy(xpath="//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a")
	public WebElement OptionsTableFirstRow;
	
	@FindBy(xpath="//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']")
	public WebElement OptionsTbl_ColStatus;
		
	
	public NewLease_OptionsAndClausePage(WebDriver driver,ExtentTest logger){
		
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);

	}
	
	public void SelectOptionsType(String OptionsType) throws InterruptedException{
		
		boolean FoundFlag=false;
		try{
			
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			WebButton.webButtonClick(driver, logger, OptionsTypeSelector);
			WebFrame.switchOnFrameById(driver, SelectorFrame);
			List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587458']//span[contains(@id,'CONTENT')]"));
			System.out.println("DropDown values="+DropDownValues.size());  
			for(int i=0;i<=DropDownValues.size();i++){
				WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587458']//span[contains(@id,'CONTENT')]")).get(i);
				System.out.println("Options Type="+Value.getText());
				if(Value.getText().equals(OptionsType)){
					MouseOperations.ActionMoveToElement(driver, logger, Value);
					Value.click();
					logger.log(LogStatus.INFO, "List Values- '" +  OptionsType + "' successfully selected");
					FoundFlag=true;
					Thread.sleep(2000);
					break;
				}
			}
			if (FoundFlag=false){
				logger.log(LogStatus.ERROR,"Failed to select '" + OptionsType);
			}
		}catch(Exception e){
			logger.log(LogStatus.ERROR, e.getMessage());
		}
	}
	
	public String getFutureDate(int AddDaysToCurrentDate){
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, AddDaysToCurrentDate);
		String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(c.getTime());
		System.out.println("getFutureDate="+Datestamp);
		return Datestamp;
		
	}
	
	public boolean AddOptionDetails(String OptionType){		
				
		boolean OptionsStatus=false;
		boolean Flag=true;
		String statustext=null;
		String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		
		try{	
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.activeElementPressKeyBoardKeys(driver, "TAB", OptionsClausestab);
			WebLink.clickOnWebLink(driver, OptionsClausestab, logger);
			
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			WebLink.clickOnWebLink(driver, Optionstab, logger);
			
			//WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			WebButton.webButtonClick(driver, logger,Options_Addbtn);
			
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			WebTextbox.sendTextToWebInput(OptionStartDatetxt, Datestamp, logger);
			
			WebTextbox.sendTextToWebInput(OptionEndDatetxt, getFutureDate(5), logger);
			
			WebTextbox.sendTextToWebInput(LastDayNoticetxt, getFutureDate(4), logger);
			
			//WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
			SelectOptionsType(OptionType);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, Option_Createbtn);			
			WebUtilities.waitForElementToDisappear(driver, Option_Createbtn, logger);
			
			WebBrowser.switchToParentWindow(driver);
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame);
//			List<WebElement>allelements = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a"));
//			 Thread.sleep(2000);
//			 System.out.println(allelements.size()); 	     
//			    for(int i=0;i<allelements.size();i++)
//			    { 
//			    	 WebElement Status = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a")).get(i);
//			    	 System.out.println(Status.getText());	
//			    	 statustext= Status.getText();
//				    	
//				   if(statustext.equalsIgnoreCase(OptionType))
//				   {
//					   logger.log(LogStatus.PASS, "Option Type Status is "+statustext );
//					   OptionsStatus=true;
//					   Flag=true;
//					   break;
//				    }else{
//				    	Flag=false;
//				    }
//				   
//				    	
//			    }
//			    if(!Flag){
//			    	logger.log(LogStatus.FAIL, "Option Type status is "+statustext );
//			    	OptionsStatus=false;
//			    }
			
			    OptionsStatus=true;
		}catch(Exception e){
			OptionsStatus=false;
		}

		return OptionsStatus;
		
	}
	
	public boolean ExerciseOptions(String OptionType){		
		
		boolean OptionsStatus=false;
		boolean OptionTypeFound=false;
		String ColValue=null;
		//String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		
		try{
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,innerFrame39Frame);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			Thread.sleep(2000);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			
			List<WebElement> StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
			int TotalEle=StatusEle.size();
			System.out.println("Total rows in Options tbl="+TotalEle);
			
			if(TotalEle>1){
				WebTextbox.sendTextToWebInput(OptionType_Filter, OptionType, logger);
				MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
				Thread.sleep(3000);
				StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
				TotalEle=StatusEle.size();
			}
			
		  for(int i=0;i<TotalEle;i++)
		    { 
		    	 WebElement Status = driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']")).get(i);
		    	 System.out.println(Status.getText());	
		    	 String statustext= Status.getText();
			    	
			   if(statustext.contains("Active"))
			   {
					WebFrame.switchToMainPageFromIFrame(driver);
					WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,innerFrame39Frame);
				    Status.click();				    
					logger.log(LogStatus.PASS, "Clicked successfully on "+OptionType);
					OptionTypeFound=true;
					break;
			    }
			   
			    	
		    }

//			for(WebElement element:StatusEle){
//				
//				ColValue=element.getText();
//				System.out.println("Table values="+ColValue);
//				if(ColValue.equalsIgnoreCase("Active")){
//					WebLink.clickOnWebLink(driver, element, logger);
//					logger.log(LogStatus.PASS, "Clicked successfully on "+OptionType);
//					OptionTypeFound=true;
//					break;
//				}
//				
//			}
			if(!OptionTypeFound){
				logger.log(LogStatus.FAIL, "Not found the "+OptionType);
			}
							
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, Option_Exercisebtn);	
			
			WebElement CntFrame=driver.findElement(By.xpath("//iframe[@id='contentFrame']"));
			WebUtilities.waitForElementToAppear(driver, CntFrame, logger);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,ChildFrame);
			WebButton.webButtonClick(driver, logger, Continuebtn);
			Thread.sleep(2000);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			//WebUtilities.waitForElementToDisappear(driver, Option_Exercisebtn, logger);
			WebButton.webButtonClick(driver, logger, Closebtn);
			Thread.sleep(2000);
			
			WebBrowser.switchToParentWindow(driver);
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,innerFrame39Frame);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			Thread.sleep(2000);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
			TotalEle=StatusEle.size();
			System.out.println("Total rows in Options tbl="+TotalEle);
			
			if(TotalEle>1){
				WebTextbox.clearTextBox(OptionType_Filter);
				WebTextbox.sendTextToWebInput(OptionType_Filter, OptionType, logger);
				MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
				Thread.sleep(3000);
			}
			List<WebElement> StatusEle1=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
			int TotalEle1=StatusEle1.size();
			System.out.println("Total rows in Options tbl="+TotalEle1);
			for(WebElement element:StatusEle1){
				
				ColValue=element.getText();
				if(ColValue.contains("Exercised")){
					logger.log(LogStatus.PASS, "Verified Option Status "+ColValue);
					OptionTypeFound=true;
					break;
				}
				
			}
			if(!OptionTypeFound){
				logger.log(LogStatus.FAIL, "Option status is not as expected"+ColValue);
			}

			OptionsStatus=true;
		}catch(Exception e){
			OptionsStatus=false;
		}

		return OptionsStatus;
		
	}

	public boolean ExpireOptions(String OptionType){		
		
		boolean OptionsStatus=false;
		boolean OptionTypeFound=false;
		String ColValue=null;
		//String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		
		try{
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,innerFrame39Frame);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			Thread.sleep(2000);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			
			List<WebElement> StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
			int TotalEle=StatusEle.size();
			System.out.println("Total rows in Options tbl="+TotalEle);
			
			if(TotalEle>1){
				WebTextbox.sendTextToWebInput(OptionType_Filter, OptionType, logger);
				MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
				Thread.sleep(3000);
				StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
				TotalEle=StatusEle.size();
			}
			
		  for(int i=0;i<TotalEle;i++)
		    { 
		    	 WebElement Status = driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']")).get(i);
		    	 System.out.println(Status.getText());	
		    	 String statustext= Status.getText();
			    	
			   if(statustext.contains("Active"))
			   {
					WebFrame.switchToMainPageFromIFrame(driver);
					WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,innerFrame39Frame);
				    Status.click();				    
					logger.log(LogStatus.PASS, "Clicked successfully on "+OptionType);
					OptionTypeFound=true;
					break;
			    }
			   
			    	
		    }
			if(!OptionTypeFound){
				logger.log(LogStatus.FAIL, "Not found the "+OptionType);
			}
							
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, Option_Expirebtn);	
			Thread.sleep(2000);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, Closebtn);
			Thread.sleep(2000);
			
			WebBrowser.switchToParentWindow(driver);
			WebBrowser.switchToChildWindow2(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,innerFrame39Frame);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			Thread.sleep(2000);
			WebButton.webButtonClick(driver, logger, Refreshbtn);
			StatusEle=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
			TotalEle=StatusEle.size();
			System.out.println("Total rows in Options tbl="+TotalEle);
			
			if(TotalEle>1){
				WebTextbox.clearTextBox(OptionType_Filter);
				WebTextbox.sendTextToWebInput(OptionType_Filter, OptionType, logger);
				MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
				Thread.sleep(3000);
			}
			List<WebElement> StatusEle1=driver.findElements(By.xpath("//tr[contains(@id,'queryResultRow')]//td[contains(@headers,'columnStatus')]//span[@class='bodyText']"));
			int TotalEle1=StatusEle1.size();
			System.out.println("Total rows in Options tbl="+TotalEle1);
			for(WebElement element:StatusEle1){
				
				ColValue=element.getText();
				if(ColValue.contains("Expired")){
					logger.log(LogStatus.PASS, "Verified Option Status "+ColValue);
					OptionTypeFound=true;
					break;
				}
				
			}
			if(!OptionTypeFound){
				logger.log(LogStatus.FAIL, "Option status is not as expected"+ColValue);
			}

			OptionsStatus=true;
		}catch(Exception e){
			OptionsStatus=false;
		}

		return OptionsStatus;
		
	}


}
