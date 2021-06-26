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
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AllLeaseContractsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	public WebElement leaseType;
	public static String LeaseID1;
	
	public static final String AllLeaseContracts_MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String AllLeaseContracts_TableFrame="//iframe[@id='objectsFrame']";
	public static final String AllLeaseContracts_ListFrame="//iframe[@id='listFrame']";
	public static final String ContentFrame="//iframe[@id='contentFrame']";
	public static final String ChildFrame="//iframe[@id='childFrame']";
	public static String SelectorFrame="unMovableLayerFrame";
	
	@FindBy(xpath="//a[@id='refreshIcon']")
	public WebElement Refreshbtn;
	
	@FindBy(xpath="//input[@id='filterValue0']")
	public WebElement LeaseID_Filter;	
	
	@FindBy(xpath = "//*[@name='filterValue1' and contains(@title,'Name')]")
	public WebElement LeaseName_Filter;
	
	@FindBy(xpath="//input[@id='filterValue2']")
	public WebElement LeaseType_Filter;
	
	@FindBy(xpath="//input[@id='filterValue8']")
	public WebElement ExpirationDate_Filter;
	
	@FindBy(xpath="//input[@id='filterValue13']")
	public WebElement LeaseStatus_Filter;
	
	@FindBy(xpath = "//*[@id='applyFiltersLink']")
	public WebElement Apply_Filter;
	
	@FindBy(xpath="//a[text()='Draft Accounting In Review']")
	public WebElement LeaseStatus;
	
	
	@FindBy(xpath="//tr[@id='queryResultRow_0']//td[@headers='row_id_1 columnStatus']//span[@class='bodyText']")
	public WebElement LeaseStatusInFirstRow;
	
	
	@FindBy(xpath="//div[@id='Activate']")
	public WebElement Activatelnk;
	
	@FindBy(xpath="//div[@id='Expire']")
	public WebElement Expirebtn;
	
	@FindBy(xpath="//div[@id='Terminate']")
	public WebElement Terminatebtn;
	
	@FindBy(xpath="//div[@id='Contract_Revise']")
	public WebElement ContractRevisebtn;
	
	@FindBy(xpath="//div[@id='Continue']")
	public WebElement Continuebtn;
		
	@FindBy(xpath="//input[@id='attr_seq_1079']")
	public WebElement ExpirationEffectiveDatetxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1079']")
	public WebElement TerminationEffectiveDatetxt;
	
	@FindBy(xpath="//a[@id='attr_seq_1071_selector']")
	public WebElement CommentTypeSelector;
	
	@FindBy(xpath="//a[@id='attr_seq_1058_selector']")
	public WebElement ChangeTypeSelector;
	
	@FindBy(xpath="//a[@id='attr_seq_1313_selector']")
	public WebElement ClauseTypeSelector;
		
	@FindBy(xpath="//textarea[@id='attr_seq_1039']")
	public WebElement ExpirationCommentstxt;
	
	
	@FindBy(xpath="//span[@class='tririgaResultsActionButton' and text()='Clear Filters']")
	public WebElement ClearFilterlnk;
	
	@FindBy(xpath="//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a")
	public WebElement LeaseFilterResults;

	@FindBy(xpath = "//*[contains(@id,'queryResultRow')]")
	public List<WebElement> search_Results;
	
	@FindBy(xpath = "//table[@id='mainTable']//tr[contains(@id,'query')]")
	List<WebElement> allElements;

	By lease_Num_Col = By.xpath(".//td[contains(@headers,'columnID')]//a");
	By lease_Status_Col = By.xpath(".//td[contains(@headers,'columnStatus')]//a");

	
	public AllLeaseContractsPage(WebDriver driver,ExtentTest logger){
		
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);

	}
	
	public boolean ActivateLeaseContract(String LeaseID) throws InterruptedException{		
		
		boolean ActivateContract=false;
		
        Set<String> allWindows1=driver.getWindowHandles();
        Iterator<String> it1=allWindows1.iterator();
        String main=it1.next();
        System.out.println("Window="+main.toString());
		driver.switchTo().window(main);			
		driver.switchTo().defaultContent();
		WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
		WebLink.clickOnWebLink(driver, Refreshbtn, logger);
		
		driver.switchTo().defaultContent();
		WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
		
		WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		Thread.sleep(2000);
		
		driver.switchTo().defaultContent();
		WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
		WebLink.clickOnWebLink(driver, Refreshbtn, logger);
		
		WebLink.clickOnWebLink(driver, LeaseStatus, logger);
		
		//WebBrowser.switchToChildWindow(driver);
	    Set<String> allWindows2=driver.getWindowHandles();
	    Iterator<String> it2=allWindows2.iterator();
	    String main2=it2.next();
	    String sub3=it2.next();
	    System.out.println("Main2="+main2.toString());
	    System.out.println("Child2="+sub3.toString());
	    driver.switchTo().window(sub3);
		driver.switchTo().defaultContent();
		//WebLink.clickOnWebLink(driver, Activatelnk, logger);
		driver.findElement(By.xpath("//div[@id='Activate']")).click();
		Thread.sleep(10000);
		//driver.close();
		//parentWindow = driver.getWindowHandle(); 
//        Set<String> allWindows3=driver.getWindowHandles();
//        Iterator<String> it3=allWindows3.iterator();
//        String main3=it3.next();
//        System.out.println("main1"+main3.toString());
//		
//        System.out.println("main"+main2.toString());
       
//		driver.switchTo().window(main1);
        driver.switchTo().window(main);
		driver.switchTo().defaultContent();
		WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
		WebLink.clickOnWebLink(driver, ClearFilterlnk, logger);
		
		WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		Thread.sleep(2000);
		WebLink.clickOnWebLink(driver, Refreshbtn, logger);
		 List<WebElement>allelements = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a"));
		 Thread.sleep(2000);
		 System.out.println(allelements.size()); 	     
		    for(int i=0;i<allelements.size();i++)
		    { 
		    	 WebElement Status = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a")).get(i);
		    	 System.out.println(Status.getText());	
		    	 String  statustext= Status.getText();
			    	
			   if(statustext.equalsIgnoreCase("Active"))
			   {
				   ActivateContract=true;
			    }else{
			    	ActivateContract=false;
			    }
			    	
		    }
		
		    return ActivateContract;
	}
	
	public boolean ExpireLeaseContract(String LeaseID,String strLeaseType) throws InterruptedException{		
		
		boolean ExpireStatus=false;
		boolean Flag=true;
		String  statustext=null;
		
		String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		String LeaseID1;
		
		WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
		
		if(LeaseID.length()>0){
			WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
		}else{
			WebTextbox.sendTextToWebInput(LeaseType_Filter, strLeaseType, logger);
			WebTextbox.sendTextToWebInput(ExpirationDate_Filter, Datestamp, logger);
			WebTextbox.sendTextToWebInput(LeaseStatus_Filter, "Active", logger);
		}
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		Thread.sleep(2000);
		
		driver.switchTo().defaultContent();
		WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
		WebLink.clickOnWebLink(driver, LeaseStatusInFirstRow, logger);
		
		WebBrowser.switchToChildWindow2(driver);
		if(LeaseID.length()==0){
			NewLease_GeneralPage GeneralPg=new NewLease_GeneralPage(driver,logger);
			LeaseID1=GeneralPg.GetLeaseID();
		}else{
			LeaseID1=LeaseID;
		}
		System.out.println("Lease id ="+LeaseID1);
		driver.switchTo().defaultContent();
		WebButton.webButtonClick(driver, logger, Expirebtn);
		Thread.sleep(4000);
		//WebElement Frame=driver.findElement(By.xpath(ChildFrame));
		//WebUtilities.waitForElementToAppear(driver, Frame, logger);
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,ChildFrame,ContentFrame);
		WebTextbox.clearTextBox(ExpirationEffectiveDatetxt);
		WebTextbox.sendTextToWebInput(ExpirationEffectiveDatetxt, Datestamp, logger);
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,ChildFrame,ContentFrame);
		WebTextbox.sendTextToWebInput(ExpirationCommentstxt, WebUtilities.GetTimeStamp(), logger);
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,ChildFrame);
		WebButton.webButtonClick(driver, logger, Continuebtn);
		
		
        WebBrowser.switchToParentWindow(driver);
		driver.switchTo().defaultContent();
		WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
		WebLink.clickOnWebLink(driver, ClearFilterlnk, logger);
		
		WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID1, logger);
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		Thread.sleep(2000);
		WebLink.clickOnWebLink(driver, Refreshbtn, logger);
		 List<WebElement>allelements = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a"));
		 Thread.sleep(2000);
		 System.out.println(allelements.size()); 	     
		    for(int i=0;i<allelements.size();i++)
		    { 
		    	 WebElement Status = driver.findElements(By.xpath("//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//span[@class='bodyText']//a")).get(i);
		    	 System.out.println(Status.getText());	
		    	 statustext= Status.getText();
			    	
			   if(statustext.equalsIgnoreCase("Expired"))
			   {
				   logger.log(LogStatus.PASS, "Lease Status is "+statustext );
				   ExpireStatus=true;
				   Flag=true;
			    }else{
			    	Flag=false;
			    }
			   
			    	
		    }
		    if(!Flag){
		    	logger.log(LogStatus.FAIL, "Lease Status is "+statustext );
		    	ExpireStatus=false;
		    }
		    return ExpireStatus;
		}
	
	public boolean CreateContractReviseLease(String LeaseID,String strLeaseType,String ChangeType) throws InterruptedException{		
		
		boolean Status=false;
		boolean Flag=true;
		String  statustext=null;
		
		String Datestamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		//String LeaseID1;
		
		try{		
		
			WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
			
			if(LeaseID.length()>0){
				WebTextbox.sendTextToWebInput(LeaseID_Filter, LeaseID, logger);
				WebTextbox.sendTextToWebInput(LeaseType_Filter, strLeaseType, logger);
				WebTextbox.sendTextToWebInput(LeaseStatus_Filter, "Active", logger);
			}else{
				WebTextbox.sendTextToWebInput(LeaseType_Filter, strLeaseType, logger);
				WebTextbox.sendTextToWebInput(LeaseStatus_Filter, "Active", logger);
			}
			MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
			Thread.sleep(2000);
			
			driver.switchTo().defaultContent();
			WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame,AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
			WebLink.clickOnWebLink(driver, LeaseStatusInFirstRow, logger);
			
			WebBrowser.switchToChildWindow2(driver);
			if(LeaseID.length()==0){
				NewLease_GeneralPage GeneralPg=new NewLease_GeneralPage(driver,logger);
				LeaseID1=GeneralPg.GetLeaseID();
			}else{
				LeaseID1=LeaseID;
			}
			System.out.println("Lease id ="+LeaseID1);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, ContractRevisebtn);
			Thread.sleep(4000);
			//WebElement Frame=driver.findElement(By.xpath(ChildFrame));
			//WebUtilities.waitForElementToAppear(driver, Frame, logger);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,ChildFrame,ContentFrame);
			SelectChangeType(ChangeType);
					
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame,ChildFrame);
			WebButton.webButtonClick(driver, logger, Continuebtn);
			Thread.sleep(5000);
			//WebUtilities.waitForElementToDisappear(driver, Continuebtn, logger);
			//WebFrame.switchToMainPageFromIFrame(driver);
			Status=true;
		}catch(Exception e){
			Status=false;
		}
        
			return Status;
	}
	
	
	public void SelectChangeType(String ChangeType) throws InterruptedException{
		
		boolean FoundFlag=false;
		try{
			
		WebButton.webButtonClick(driver, logger, ChangeTypeSelector);
		Thread.sleep(3000);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			//WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			System.out.println("UsageType="+Value.getText());
			if(Value.getText().contains(ChangeType)){
				//MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				//WebButton.webButtonClick(driver, logger, Value);
				//Actions act1=new Actions(driver);
				//act1.sendKeys(Keys.TAB).build().perform();
				logger.log(LogStatus.INFO, "List Values- '" +  ChangeType + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				WebFrame.switchToMainPageFromIFrame(driver);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + ChangeType);
		}
		}catch(Exception e){
			logger.log(LogStatus.ERROR,"Failed to select Space Usage Type '" + e.getMessage());
		}
		
		
	}
	
	public void apply_LeaseContractFilters(String leaseID, String LeaseType, String status) {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame, AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
			WebButton.webButtonClick(driver, logger, ClearFilterlnk);
			Thread.sleep(2000);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame, AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
			//WebUtilities.waitForElementToAppear(driver, LeaseID_Filter, logger);
			if(leaseID.length()>0){
				WebTextbox.sendTextToWebInput(LeaseID_Filter, leaseID, logger);
			}
						
			//WebTextbox.sendTextToWebInput(LeaseID_Filter, leaseID, logger);
			//WebTextbox.sendTextToWebInput(LeaseName_Filter, LeaseType, logger);
			WebTextbox.sendTextToWebInput(LeaseType_Filter, LeaseType, logger);
			WebTextbox.sendTextToWebInput(LeaseStatus_Filter, status, logger);
			WebButton.webButtonClick(driver, logger, Apply_Filter);
			logger.log(LogStatus.PASS, "Applied Filter in Home  ->  All Lease Contracts Page");

		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Failed to apply filters in Home  ->  All Lease Contracts - " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public void Select_Lease_From_Result(int rowID) {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, AllLeaseContracts_MainFrame, AllLeaseContracts_TableFrame,AllLeaseContracts_ListFrame);
			if (search_Results.size() > 0) {
				logger.log(LogStatus.PASS, "Selected the payment line from search results");
				WebElement ele = search_Results.get(rowID - 1)
						.findElement(By.xpath(".//td[contains(@headers,'columnID')]//a"));
				WebButton.webButtonClick(driver, logger, ele);
				logger.log(LogStatus.PASS, "Select the Lease Contract -> " + ele.getText() + " Successfullly.");
			} else {
				logger.log(LogStatus.FAIL, "Failed to select the lease from search results.No Results are found.");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL,
					"Failed to select the lease contract from search results. " + e.getStackTrace().toString());
			e.printStackTrace();
		}
	}

	public String getExisitingLeaseStatus(String leaseID, String leaseName) {

		String lease_Status = "";
		try {
			apply_LeaseContractFilters(leaseID, leaseName, "");
			Thread.sleep(5000);
			WebElement leaseElementStatus = allElements.get(0).findElement(lease_Status_Col);
			lease_Status = leaseElementStatus.getText().trim();
			logger.log(LogStatus.INFO, " Lease ID - " + leaseID + " Status - " + lease_Status);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to find the Lease ID to get the status - " + leaseID);
			logger.log(LogStatus.FAIL,
					"Failed to find the Lease ID to get the status - " + leaseID + e.getStackTrace().toString());
			e.printStackTrace();
		}
		return lease_Status;
	}


	}
