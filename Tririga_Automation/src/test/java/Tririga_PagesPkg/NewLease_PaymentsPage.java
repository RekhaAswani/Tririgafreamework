package Tririga_PagesPkg;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
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

import corePkg.BaseClass;
import Tririga_PagesPkg.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NewLease_PaymentsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	public WebElement leaseType;
	public static String LeaseNo=null;
	boolean FoundFlag=false;
	
	public static String PaymentsMainFrame="//iframe[@id='contentFrame']";	
	public static String PaymentssMain1Frame="//iframe[@id='childFrame']";
	public static String PaymentsChld1Frame="//frame[@id='frameMain']";
	public static String PaymentsChld2Frame="//iframe[@id='contentFrame']";
	public static String PaymentsChld3Frame="//iframe[@id='innerFrame7']";
	public static String PaymentsInnerFrame = "//iframe[@id='innerFrame103']";
	public static String Payment_Schedule_InnerFrame = "//iframe[@id='innerFrame129']";
	public static String Payment_Process_InnerFrame = "//iframe[@id='innerFrame158']";
	public static String Payment_Instructions_InnerFrame = "//iframe[@id='innerFrame214']";
	public static String InnerFrame11 = "//iframe[@id='innerFrame11']";
	
	public static String SelectorFrame="unMovableLayerFrame";
	
	@FindBy(xpath="//span[text()='Payments']")
	public WebElement Paymentstab;
	
	@FindBy(xpath = "//span[text()='Payment Schedules']")
	public WebElement PaymentSchedulesTab;
	
	@FindBy(xpath="//span[@class='actionButtonText' and contains(text(),'Generate Payment Schedules')]")
	public WebElement GeneratePaymentSchedulesbtn;	
	
	@FindBy(xpath = "//*[@id='upperPageNav']//div[@id='Activate']")
	public WebElement activate_Btn;
	
	@FindBy(xpath="//input[@id='attr_seq_1086']")
	public WebElement PaymentType;
	
	@FindBy(xpath="//a[@id='attr_seq_1086_selector']")
	public WebElement PaymentTypeSelector;
	
	@FindBy(xpath="//ul[@id='hierarcyTree_1587460']//span[contains(@id,'CONTENT')]")
	public WebElement PaymentTypeListValues;
	
	@FindBy(xpath="//span[text()='Association [PAY][NI]']")
	public WebElement PaymentType_AssociationPayIn;
	
	@FindBy(xpath="//a[@id='attr_seq_1166_selector']")
	public WebElement InvoiceTypeSelector;
	
	@FindBy(xpath="//span[@class='bodyText' and contains(text(),'NOPRINT')]")
	public WebElement InvoiceTypeNoPrint;
	
	@FindBy(xpath="//span[@class='bodyText' and contains(text(),'LANDLORD1')]")
	public WebElement InvoiceTypeLandLord1;
	
	@FindBy(xpath="//span[@class='bodyText' and contains(text(),'TENANT1')]")
	public WebElement InvoiceTypeTenant1;	
	
	@FindBy(xpath="//input[@id='attr_seq_1166']")
	public WebElement InvoiceType;
	
	@FindBy(xpath="//input[@id='attr_seq_1126']")
	public WebElement Frequency;
	
	@FindBy(xpath="//a[@id='attr_seq_1126_selector']")
	public WebElement FrequencySelector;
	
	@FindBy(xpath="//a[@id='attr_seq_1134_selector']")
	public WebElement PaymentDueOn_Selector;
	
	@FindBy(xpath="//span[contains(text(),'First Day of Period')]")
	public WebElement PaymentDueOn_FirstDay;
	
	@FindBy(xpath="//input[@id='attr_seq_1134']")
	public WebElement PaymentDueOn;	

	@FindBy(xpath="//input[@id='attr_seq_1105']")
	public WebElement NoOfSchedules;
	
	@FindBy(xpath="//input[@id='attr_seq_1103']")
	public WebElement MonthsPerSchedule;
	
	@FindBy(xpath="//input[@id='attr_seq_1100']")
	public WebElement Amount;
	
	@FindBy(xpath="//input[@id='attr_seq_1133']")
	public WebElement FullPaymentStartDate;
	
	@FindBy(xpath="//input[@id='attr_seq_1102']")
	public WebElement FromDate;
	
	@FindBy(xpath="//span[@class='actionButtonText'  and contains(text(),'Find')]")
	public WebElement CostCenter_Findbtn;
	
	@FindBy(xpath="//input[@type='radio' and @name='sNo']")
	public WebElement RadioBtn;
	
	@FindBy(xpath="//table[@id='actionsTable']//span[@class='actionButtonText' and text()='OK']")
	public WebElement OkBtn;
	
	@FindBy(xpath="//input[@name='attr_seq_1150']")
	public WebElement TaxGroup;
	
	//Added for amount
	
	@FindBy(xpath="//div[@id='Generate Schedule(s)_11']")
	public WebElement GenerateSchedulesbtn;
	
	//@FindBy(xpath="//a[@title='YC00']")
	@FindBy(xpath="//a[@title='Z000' or @title='YC00']")
	public WebElement PayInstructionsTable;
	
	@FindBy(xpath="//input[@name='attr_seq_1256']")
	public WebElement PaymentMethod;
	
	@FindBy(xpath="//div[@id='Save_&_Close']")
	public WebElement SaveAndCloseBtn;
	
	@FindBy(xpath="//div[@id='Create_Schedule(s)'")
	public WebElement CreateSchedulesBtn;
	
	@FindBy(xpath="//a[@id='childLayerCloseX']")
	public WebElement CloseBtn;
	
	@FindBy(xpath="//a[@id='refreshIcon'")
	public WebElement RefreshIcon;
	
	@FindBy(xpath="//div[@id='Submit']")
	public WebElement Lease_Submitbtn;
	
	@FindBy(xpath = "//input[@name='repSubList']//following-sibling::a")
	public WebElement RelatedReportsLink;

	@FindBy(xpath = "//*[contains(@id,'queryResultRow')]//ancestor::table[@id='dataTab']")
	public WebElement payments_Table;

	@FindBy(xpath = "//span[contains(text(),'Remove')]")
	public WebElement payment_Schedule_Remove_Btn;

	String Payment_Options = "//span[contains(text(),'PLACEHOLDER')]";

	@FindBy(xpath = "//*[contains(@title,'Start Date')]")
	public WebElement payment_Start_Date_Filter;

	@FindBy(xpath = "//*[contains(@title,'End Date')]")
	public WebElement payment_End_Date_Filter;

	@FindBy(xpath = "//*[contains(@title,'Cost Center')]")
	public WebElement payment_costCenter_Filter;

	@FindBy(xpath = "//*[contains(@title,'Payment Type')]")
	public WebElement payment_Type_Filter;

	@FindBy(xpath = "//*[contains(@title,'Due Date')]")
	public WebElement payment_Due_Date;

	@FindBy(xpath = "//*[contains(@title,'Status')]")
	public WebElement payment_Status_Filter;

	@FindBy(xpath = "//*[@id='applyFiltersLink']")
	public WebElement apply_Filter;

	@FindBy(xpath = "//*[@id='clearFiltersLink']")
	public WebElement clear_Filter;

	@FindBy(xpath = "//*[contains(@id,'queryResultRow')][1]//td//following-sibling::input[contains(@type,'checkbox')]")
	public WebElement first_Result_Checkbox_Ele;

	@FindBy(xpath = "(//*[contains(@id,'queryResultRow')][1]//td//following::span[contains(@aria-label,'Payment Type')])[1]")
	public WebElement first_Result_Link_Ele;

	@FindBy(xpath = "//*[contains(@id,'queryResultRow')]//ancestor::table[@id='dataTab']//tr[contains(@class,'resultRow')]")
	public List<WebElement> payment_Schedule_Results;

	@FindBy(xpath = "(//*[@class='wizardSectionLabel'])[2]")
	public WebElement wizard_Title;

	
	
	public NewLease_PaymentsPage(WebDriver driver,ExtentTest logger){
		
		this.driver=driver;
		this.logger=logger;
		PageFactory.initElements(driver, this);

	}
	
	public void SelectPaymentType(String PayType) throws InterruptedException{
		
		//boolean FoundFlag=false;
		
		WebButton.webButtonClick(driver, logger, PaymentTypeSelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587460']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587460']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(PayType)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + PayType);
		}
	}
	
	public void SelectInvoiceType(String InvoiceType) throws InterruptedException{
		
		//boolean FoundFlag=false;
		WebButton.webButtonClick(driver, logger, InvoiceTypeSelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		//List<WebElement> DropDownValues = driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']"));
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//table[@id='listValueTable']//input[contains(@id,'englishValue')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			//WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//input[contains(@id,'englishValue')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(InvoiceType)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				//Actions act1=new Actions(driver);
				//act1.sendKeys(Keys.TAB).build().perform();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + InvoiceType);
		}
	}
	
	public void SelectFrequency(String Freq) throws InterruptedException{
		
		//boolean FoundFlag=false;
		WebButton.webButtonClick(driver, logger, Frequency);
		WebButton.webButtonClick(driver, logger, FrequencySelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_7411334']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_7411334']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(Freq)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + Freq);
		}
	}
	
	public void SelectPaymentDueOn(String PayDueOn) throws InterruptedException{
		
		//boolean FoundFlag=false;
		
		WebButton.webButtonClick(driver, logger, PaymentDueOn_Selector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(PayDueOn)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + PayDueOn);
		}
	}
	
	public boolean ClickCreateSchedule(String paymethod) throws InterruptedException{
		 	
			boolean MethodStatus=true;
			
			driver.switchTo().defaultContent();
	        //find all windows list and switch on that
	        Set<String> allWindows1=driver.getWindowHandles();
	        Iterator<String> it1=allWindows1.iterator();
	        String main1=it1.next();
	        String sub1=it1.next();
	        String sub2=it1.next();
	        
	        driver.switchTo().window(sub2);        
	        driver.manage().window().maximize();
	        
	        driver.switchTo().defaultContent();
	        driver.switchTo().frame("contentFrame");

	        driver.findElement(By.xpath("//input[@name='attr_seq_1256']")).sendKeys(paymethod);
	        Actions act18=new Actions(driver);	        
	        act18.sendKeys(Keys.TAB).build().perform();       
	        Thread.sleep(4000); 
	        driver.findElement(By.xpath("//div[@id='Save_&_Close']")).click();
	        Thread.sleep(2000);    
	        System.out.println("Payment Instructions Updated");
	        driver.switchTo().window(sub1);    
	        driver.switchTo().frame("contentFrame");
	        driver.switchTo().frame("childFrame");
	        driver.findElement(By.xpath("//div[@id='Create_Schedule(s)']")).click();
	       // driver.findElement(By.xpath("//div[@id='Create_Schedule(s)']")).click();
	        System.out.println("Created Schedule");
	        Thread.sleep(20000);   
	        
	        /////////////////////////////////////////////////////////////////////////////
	        driver.switchTo().defaultContent();        
	        driver.switchTo().frame("contentFrame");
	        //driver.switchTo().frame("closeModalPageFrame");
	       driver.findElement(By.xpath("//a[@id='childLayerCloseX']")).click();
	       // driver.findElement(By.xpath("//a[@id='childLayerCloseX']")).click();
	        Thread.sleep(4000);
	        System.out.println("Window closed");	        
	//////////////////////////////////////////////////////////////////////////////refresh page///////////////////////////////////// 
	        Thread.sleep(1000);      
	        driver.switchTo().defaultContent();        
	        driver.switchTo().frame("contentFrame");
	        driver.switchTo().frame("innerFrame129");
	        Thread.sleep(2000);  
	        driver.findElement(By.xpath("//a[@id='refreshIcon']")).click();
	        Thread.sleep(2000);    
	        System.out.println("Checking Payment Schedule");
	        MethodStatus=true;
	        
	        return MethodStatus;

	}
	
//	public String GetLeaseIDANDSubmitLease() throws InterruptedException{
//		
//		System.out.println("Get Lease method");
//		driver.switchTo().defaultContent();
//		driver.switchTo().frame("contentFrame");
//		Thread.sleep(2000);
//		NewLease_GeneralPage GeneralPg=new NewLease_GeneralPage(driver,logger);
//		WebElement GenTab=GeneralPg.Generaltab;
//		WebElement LeaseSubmit=GeneralPg.Lease_Submitbtn;
//		System.out.println("General class init");
//		GenTab.click();
//		try {
//			Thread.sleep(3000);
//			LeaseNo=GeneralPg.LeaseIdlbl.getText();
//			if (LeaseNo.length()>0){
//				logger.log(LogStatus.PASS, "Lease ID -"+ LeaseNo+",is created successfully");
//				LeaseSubmit.click();
//				Thread.sleep(3000);
//			}else{
//				logger.log(LogStatus.FAIL, "Lease ID is not created");
//			}
//				
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return LeaseNo;
//		
//	}
	public boolean GeneratePaymentsSchedules(String PayType,String strInvoiceType,String strFrequency,String strPaymentDueOn,String PayStartDate,String intNoofSchedule,String monthperschedule,String strTaxGroup,String Amountperbasis,String strPaymentMethod)
	{					
		boolean PaymentsPgStatus=false;		
		try
		{
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.activeElementPressKeyBoardKeys(driver, "TAB", Paymentstab);
			WebLink.clickOnWebLink(driver, Paymentstab, logger);
			String paymentsWindow = driver.getWindowHandle();
			System.out.println("Window Name="+paymentsWindow.toString());
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
			WebButton.webButtonClick(driver, logger,GeneratePaymentSchedulesbtn);
			
			WebFrame.switchOnMultipleFramesByXpath(driver,PaymentssMain1Frame,PaymentsChld2Frame);
			//Thread.sleep(3000);
			SelectPaymentType(PayType);	
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			//Thread.sleep(3000);
			SelectFrequency(strFrequency);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			//Thread.sleep(3000);
			//SelectInvoiceType(strInvoiceType);
			WebButton.webButtonClick(driver, logger, InvoiceTypeSelector);
			WebFrame.switchOnFrameById(driver, SelectorFrame);
			//WebElement invoiceType=driver.findElement(By.xpath("//span[@class='bodyText' and contains(text(),'NOPRINT')]"));
			if (strInvoiceType.contains("TENANT1"))
			{
				MouseOperations.ActionMoveToElement(driver, logger, InvoiceTypeTenant1);
				WebLink.clickOnWebLink(driver, InvoiceTypeTenant1, logger);
			}
			else
			{
				MouseOperations.ActionMoveToElement(driver, logger, InvoiceTypeLandLord1);
				WebLink.clickOnWebLink(driver, InvoiceTypeLandLord1, logger);
			}

			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			//Thread.sleep(3000);
			//SelectPaymentDueOn(strPaymentDueOn);
			WebButton.webButtonClick(driver, logger, PaymentDueOn_Selector);
			WebFrame.switchOnFrameById(driver, SelectorFrame);
			//WebElement invoiceType=driver.findElement(By.xpath("//span[@class='bodyText' and contains(text(),'NOPRINT')]"));
			MouseOperations.ActionMoveToElement(driver, logger, PaymentDueOn_FirstDay);
			WebLink.clickOnWebLink(driver, PaymentDueOn_FirstDay, logger);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			//Thread.sleep(2000);
			WebTextbox.sendTextToWebInput(FullPaymentStartDate, PayStartDate, logger);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			//Thread.sleep(2000);
			WebTextbox.clearTextBox(NoOfSchedules);
			Thread.sleep(2000);
			WebTextbox.sendTextToWebInput(NoOfSchedules, intNoofSchedule, logger);
///////////////////////// added new code by rekha aswani  for months per schedules ///////////////////////////////////////////////////////////////
			
			WebTextbox.clearTextBox(MonthsPerSchedule);
			WebTextbox.sendTextToWebInput(MonthsPerSchedule, monthperschedule, logger);
			
			WebTextbox.clearTextBox(Amount);
			WebTextbox.sendTextToWebInput(Amount, Amountperbasis, logger);
			
			WebPage.scrollingToBottomOfAPage(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			Thread.sleep(2000);
			WebButton.webButtonClick(driver, logger, CostCenter_Findbtn);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentssMain1Frame,PaymentsChld1Frame,PaymentsChld2Frame);
			Thread.sleep(3000);
			WebRadiobutton.selectRadioButton(driver, logger, RadioBtn);
			WebButton.webButtonClick(driver, logger, OkBtn);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			Thread.sleep(3000);
			WebTextbox.sendTextToWebInput(TaxGroup, strTaxGroup, logger);
			//MouseOperations.ActionMoveToElement(driver, logger, TaxGroup);
		    Actions act17=new Actions(driver);
	        Thread.sleep(2000);
	        act17.sendKeys(Keys.TAB).build().perform();
	        Thread.sleep(4000);
	        
			WebPage.scrollingToBottomOfAPage(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame);
			Thread.sleep(3000);
			WebButton.webButtonClick(driver, logger, GenerateSchedulesbtn);
			Thread.sleep(3000);
			
///////////////////////////  Removed for time being   ///////////////////////////////////////////////////////
			
//			///Below Code is added on 10th Jan 20
//			for(int i=1;i<=Integer.parseInt(intNoofSchedule);i++)
//			{
//				WebFrame.switchToMainPageFromIFrame(driver);
//				WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame,InnerFrame11);
//				String xpathtxt="//*[contains(@id,'queryResultRow')]["+i+"]//td[contains(@headers,'columnAmount')]//input[contains(@id,'fld')]["+i+"]";
//				driver.findElement(By.xpath(xpathtxt)).clear();
//				driver.findElement(By.xpath(xpathtxt)).sendKeys(Amount);
//				logger.log(LogStatus.INFO, "Entered amount in Payments"+Amount);
//			}
//			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame,PaymentsChld2Frame,PaymentsChld3Frame);
			Thread.sleep(2000);
			WebButton.webButtonClick(driver, logger, PayInstructionsTable);
			Thread.sleep(2000);
			
			PaymentsPgStatus=ClickCreateSchedule(strPaymentMethod);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebButton.webButtonClick(driver, logger, Lease_Submitbtn);
	
			//GetLeaseIDANDSubmitLease();
			//ClickCreateSchedule();	 
			
//			WebFrame.switchToMainPageFromIFrame(driver);
//			//WebBrowser.switchToChildWindow2(driver);
//			 //find all windows list and switch on that
//	        Set<String> allWindows1=driver.getWindowHandles();
//	        Iterator<String> it1=allWindows1.iterator();
//	        String main1=it1.next();
//	        String sub1=it1.next();
//	        String sub2=it1.next();
//	        
//	        driver.switchTo().window(sub2);        
//	        driver.manage().window().maximize();
//			
//			WebFrame.switchToMainPageFromIFrame(driver);
//			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsChld2Frame);
//			WebTextbox.sendTextToWebInput(PaymentMethod, strPaymentMethod, logger);
//			//MouseOperations.PerformKeywordActions(driver, logger, "TAB");
//					        
//			//WebBrowser.switchToChildWindow2(driver);
//			//Thread.sleep(2000);
//			WebFrame.switchToMainPageFromIFrame(driver);
//			WebButton.webButtonClick(driver, logger, SaveAndCloseBtn);
//			WebButton.webButtonClick(driver, logger, SaveAndCloseBtn);
//			Thread.sleep(3000);
//			
//			//driver.switchTo().window(paymentsWindow);
//		    //driver.switchTo().defaultContent();
//			//WebBrowser.switchToChildWindow(driver);
//			driver.switchTo().window(sub1); 
//			//driver.switchTo().window(sub1);
//			//WebBrowser.switchToChildWindow(driver);
//			//WebFrame.switchToMainPageFromIFrame(driver);
//			//WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame,PaymentssMain1Frame);
//	        driver.switchTo().frame("contentFrame");
//	        driver.switchTo().frame("childFrame");
//			Thread.sleep(3000);
//			WebButton.webButtonClick(driver, logger, CreateSchedulesBtn);		
			
		}
		catch(Exception e)
		{
			PaymentsPgStatus=false;
		}

		return PaymentsPgStatus;
	}	
	public void clickOnPaymentsTab() 
	{
		try {
			WebBrowser.switchToChildWindow(driver);
			driver.manage().window().maximize();
			WebButton.activeElementPressKeyBoardKeys(driver, "TAB", Paymentstab);
			WebLink.clickOnWebLink(driver, Paymentstab, logger);
			String paymentsWindow = driver.getWindowHandle();
			System.out.println("Window Name=" + paymentsWindow.toString());
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
			WebLink.clickOnWebLink(driver, Paymentstab, logger);
			logger.log(LogStatus.INFO, " Clicked on Payments -> Payments Tab ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on Payments -> Payments Tab " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This method is used Click on Main Payments Tab
	 */
	public void clickOnMainPaymentsTab() {
		try {
			Thread.sleep(1000);
			WebBrowser.switchToChildWindow(driver);
			WebUtilities.waitForElementToAppear(driver, Paymentstab, logger);
			WebLink.clickOnWebLink(driver, Paymentstab, logger);
			logger.log(LogStatus.INFO, " Clicked on Main Payments Tab ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on Main Payments Tab " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used Click on Payment Schedules Tab
	 */
	public void clickOnPaymentSchedulesTab() {
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
			WebUtilities.waitForElementToAppear(driver, PaymentSchedulesTab, logger);
			WebLink.clickOnWebLink(driver, PaymentSchedulesTab, logger);
			logger.log(LogStatus.INFO, " Clicked on Payment Schedules Tab ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on Payment Schedules Tab " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectRelatedReportOptions(String reportName) {
		driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
		try {
			if (reportName.contains("Payment Schedules")) {
				WebFrame.switchOnMultipleFramesByXpath(driver, Payment_Schedule_InnerFrame);
			} else if (reportName.contains("Payments")) {
				WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsInnerFrame);
			} else if (reportName.contains("Instructions")) {
				WebFrame.switchOnMultipleFramesByXpath(driver, Payment_Instructions_InnerFrame);
			} else {
				WebFrame.switchOnMultipleFramesByXpath(driver, Payment_Process_InnerFrame);
			}
			WebLink.clickOnWebLink(driver, RelatedReportsLink, logger);
			WebElement element = driver.findElement(By.xpath(Payment_Options.replace("PLACEHOLDER", reportName)));
			WebUtilities.waitForElementToAppear(driver, element, logger);
			element.click();
			Thread.sleep(3000);
			logger.log(LogStatus.INFO, " Selected Related Report Option - " + reportName);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to select option -  " + reportName + e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectPaymentSchedulesCheckBox() {

		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
		if (wizard_Title.getText().contains("Payments")) {
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsInnerFrame);
		} else if (wizard_Title.getText().contains("Payment Schedules")) {
			WebFrame.switchOnMultipleFramesByXpath(driver, Payment_Schedule_InnerFrame);
		}
		try {
			if (first_Result_Checkbox_Ele.isDisplayed()) {
				WebButton.webButtonClick(driver, logger, first_Result_Checkbox_Ele);
				logger.log(LogStatus.PASS, "Successfully selected the payment schedule from the table.");
			} else {
				logger.log(LogStatus.FAIL, "Failed to select result from the search results. ");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to select payment schedules from the table. ");
		}
	}

	public void selectPaymentSchedulesLink() {

		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
		if (wizard_Title.getText().contains("Payments")) {
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsInnerFrame);
		} else if (wizard_Title.getText().contains("Payment Schedules")) {
			WebFrame.switchOnMultipleFramesByXpath(driver, Payment_Schedule_InnerFrame);
		}
		try {
			if (first_Result_Link_Ele.isDisplayed()) {
				WebButton.webButtonClick(driver, logger, first_Result_Link_Ele);
				logger.log(LogStatus.PASS, "Successfully selected the payment schedule from the table.");
			} else {
				logger.log(LogStatus.FAIL, "Failed to select result from the search results. ");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to select payment schedules from the table. ");
		}
	}

	public void applyFilterinPayments(Map<String, String> data) {

		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
		if (wizard_Title.getText().contains("Payments")) {
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsInnerFrame);
		} else if (wizard_Title.getText().contains("Payment Schedules")) {
			WebFrame.switchOnMultipleFramesByXpath(driver, Payment_Schedule_InnerFrame);
		}
		try {
			if (apply_Filter.isDisplayed()) {
				WebButton.webButtonClick(driver, logger, clear_Filter);
				WebUtilities.waitForElementToAppear(driver, payment_Type_Filter, logger);
				WebTextbox.sendTextToWebInput(payment_Type_Filter, data.get("Payment Type"), logger);
				WebUtilities.waitForElementToAppear(driver, payment_Start_Date_Filter, logger);
				WebTextbox.sendTextToWebInput(payment_Start_Date_Filter, data.get("Start Date"), logger);
				WebUtilities.waitForElementToAppear(driver, payment_End_Date_Filter, logger);
				WebTextbox.sendTextToWebInput(payment_End_Date_Filter, data.get("End Date"), logger);
				WebUtilities.waitForElementToAppear(driver, payment_costCenter_Filter, logger);
				WebTextbox.sendTextToWebInput(payment_costCenter_Filter, data.get("Cost Center"), logger);
				WebUtilities.waitForElementToAppear(driver, payment_Status_Filter, logger);
				WebTextbox.sendTextToWebInput(payment_Status_Filter, data.get("Status"), logger);
				try {
					if (payment_Due_Date.isDisplayed()) {
						WebTextbox.sendTextToWebInput(payment_Due_Date, data.get("Due Date"), logger);
					}
				} catch (Exception e) {
				}
				WebButton.webButtonClick(driver, logger, apply_Filter);
				logger.log(LogStatus.PASS, "Applied filters successfully in Payment Schedule. ");
			} else {
				logger.log(LogStatus.INFO, "Apply filter is not available in payment schedules from the table. ");
			}
		} catch (NoSuchElementException e) {
			logger.log(LogStatus.INFO, "Apply filter is not available in payments table. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to apply filter in payment schedules from the table. ");
		}
	}

	public boolean Verify_Payment_Status(String status) {

		boolean status_Check = false;
		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
			if (wizard_Title.getText().contains("Payments")) {
				WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsInnerFrame);
			} else if (wizard_Title.getText().contains("Payment Schedules")) {
				WebFrame.switchOnMultipleFramesByXpath(driver, Payment_Schedule_InnerFrame);
			}
			List<WebElement> rows = payments_Table.findElements(By.xpath(".//tr[contains(@class,'resultRow')]"));
			if (rows.size() > 0) {
				logger.log(LogStatus.PASS, "Payment found in the Payments table.");
				String status1 = rows.get(0).findElement(By.xpath(".//td[contains(@headers,'columnStatus')]"))
						.getText();
				if (status1.trim().equalsIgnoreCase(status.trim())) {
					status_Check = true;
					logger.log(LogStatus.PASS, "Payment Status is Validated Successfully.Status is " + status1);
				} else {
					logger.log(LogStatus.FAIL,
							"Expected Payment Status is NOT EQUAL to Actual.Actual Status is   " + status1);
				}
			} else {
				logger.log(LogStatus.FAIL, "Payment NOT found in the Payments table.");
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to verify payment status " + e.getStackTrace());
			e.printStackTrace();
		}
		return status_Check;
	}

	public void clickonRemoveBtn() {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, PaymentsMainFrame);
			WebUtilities.waitForElementToAppear(driver, payment_Schedule_Remove_Btn, logger);
			WebButton.webButtonClick(driver, logger, payment_Schedule_Remove_Btn);
			Thread.sleep(2000);
			logger.log(LogStatus.PASS, "Clicked on Remove Button Successfully. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on remove button. " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public void clickonSubmitBtn() {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebUtilities.waitForElementToAppear(driver, Lease_Submitbtn, logger);
			WebButton.webButtonClick(driver, logger, Lease_Submitbtn);
			Thread.sleep(1000);
			logger.log(LogStatus.PASS, "Clicked on Submit Button Successfully. ");
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on submit button. " + e.getStackTrace());
			e.printStackTrace();
		}
	}

	public boolean clickonActivateBtn() {
		boolean status = false;
		try {
			WebBrowser.switchToChildWindow(driver);
			driver.manage().window().maximize();
			WebUtilities.waitForElementToAppear(driver, activate_Btn, logger);
			WebButton.webButtonClick(driver, logger, activate_Btn);
			Thread.sleep(3000);
			logger.log(LogStatus.PASS, "Clicked on Activate Button Successfully. ");
			status = true;
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to click on activate button. " + e.getStackTrace());
			e.printStackTrace();
		}
		return status;
	}

}
