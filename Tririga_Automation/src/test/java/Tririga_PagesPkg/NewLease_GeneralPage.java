package Tririga_PagesPkg;

import java.util.List;

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
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebLink;
import com.webElementPkg.WebPage;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

public class NewLease_GeneralPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	public boolean FoundFlag=false;
	public WebElement leaseType;
	public static String LeaseNo;
	public static String SelectorFrame="unMovableLayerFrame";
	
	public static final String ChildFrame = "//iframe[@name='childFrame']";
	public static final String ContentFrame = "//iframe[@name='contentFrame']";
	public static final String unMovableLayerFrame = "//iframe[@name='unMovableLayerFrame']";
	
	//@FindBy(xpath="//span[@class='x-tab-strip-text ' and text()='General']")
	@FindBy(xpath="//span[text()='General']")
	public WebElement Generaltab;
	
	@FindBy(xpath="//span[@id='attr_seq_2381']")
	public WebElement LeaseIdlbl;
	
	@FindBy(xpath="//*[@id='attr_seq_1163']")
	public WebElement LeaseName;
	
	@FindBy(xpath="//a[@id='attr_seq_1308_selector']")
	public WebElement LeaseTypeSelector;
	
	@FindBy(xpath="//a[@id='attr_seq_2730_selector']")
	public WebElement SecondaryLeaseTypeSelector;
	
	@FindBy(xpath="//a[@id='attr_seq_2052_selector']")
	public WebElement ContractStatusSelector;	
	
	@FindBy(xpath="//span[text()='Income Lease']")
	public WebElement IncomeLease;
	
	@FindBy(xpath="//span[text()='Expense Lease']")
	public WebElement ExpenseLease;	

	@FindBy(xpath="//span[text()='Tenant Lease']")
	public WebElement TenantLease;
	
	@FindBy(xpath="//span[text()='Landlord Lease']")
	public WebElement LandlordLease;
	
	
	@FindBy(xpath="//input[@id='attr_seq_2730']")
	public WebElement SecondaryLeaseType;
	
	@FindBy(xpath="//span[text()='Active']")
	public WebElement ContractStatusActive;
	
	@FindBy(xpath="//input[@id='attr_seq_1182']")
	public WebElement CommenceDate;
	
	@FindBy(xpath="//input[@name='attr_seq_1172']")
	public WebElement ExpireDate;
	
	@FindBy(xpath="//input[@name='attr_seq_2663']")
	public WebElement BusinessUnit;
	
	@FindBy(xpath="//span[text()='Contact Details']")
	public WebElement ContactDetailstab;
	
	@FindBy(xpath="//div[@id='Submit']")
	public WebElement Lease_Submitbtn;
	
	@FindBy(xpath = "//span[text()='Terminate']")
	public WebElement Terminatebtn;

	@FindBy(xpath = "//span[text()='Contract Revise']")
	public WebElement contractReviseBtn;

	@FindBy(xpath = "//a[@name='attr_seq_1058_selector']")
	public WebElement contractRevise_ChangeType;
	
	@FindBy(xpath = "//*[@id='BottomPageNav']//*[text()='Continue']")
	public WebElement contractRevise_Continue_Btn;

	String contractRevise_Options = "//span[contains(text(),'PLACEHOLDER')]";
	
	public NewLease_GeneralPage(WebDriver driver,ExtentTest logger){
		
		this.driver=driver;
		this.logger=logger;
		LeaseNo=null;
		PageFactory.initElements(driver, this);

	}
	
	public WebElement setLeaseTypeLocator(String txtLeaseType){
		
		String xpath="//span[text()='"+txtLeaseType+"']";
		leaseType=driver.findElement(By.xpath(xpath));
		
		return leaseType;
		
	}
	
	public WebElement getLeaseTypeLocator(){
		
		return leaseType;
	}
	
	public WebElement getElementByXpathContainsText(String Text) throws InterruptedException
    {
		String xpath="//span[text()='"+Text+"']";
		return driver.findElement(By.xpath(xpath));
    }
	

	public void SelectLeaseType(String LeaseType) throws InterruptedException{
		
		//boolean FoundFlag=false;		
		//WebFrame.switchToMainPageFromIFrame(driver);
		//WebFrame.switchOnFrameById(driver, "contentFrame");
		WebButton.webButtonClick(driver, logger, LeaseTypeSelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587450']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587450']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(LeaseType)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + LeaseType);
		}
	}
	
	public void SelectSecondaryLeaseType(String SecLeaseType) throws InterruptedException{
		
		//boolean FoundFlag=false;		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnFrameById(driver, "contentFrame");
		WebButton.webButtonClick(driver, logger, SecondaryLeaseTypeSelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_129209376']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_129209376']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(SecLeaseType)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + SecLeaseType);
		}
	}
	
	public void SelectContractStatus(String ContrStatus) throws InterruptedException{
		
		//boolean FoundFlag=false;	
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnFrameById(driver, "contentFrame");
		WebButton.webButtonClick(driver, logger, ContractStatusSelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_2260056']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_2260056']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("PaymentType="+Value.getText());
			if(Value.getText().equals(ContrStatus)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  Value.getText() + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + ContrStatus);
		}
	}
	
	public boolean EnterExpenseLease_ActiveContract_Details(String txtLeaseName,String txtLeaseType,String txtSecLeaseType,String Contrtatus,String attCommenceDate,String attExpireDate,String BusUnit){
		
				
		boolean LeaseGenPgStatus=false;		
		try{
			WebBrowser.switchToChildWindow(driver);
			driver.manage().window().maximize();
			WebFrame.switchOnFrameById(driver, "contentFrame");
			WebTextbox.sendTextToWebInput(LeaseName, txtLeaseName, logger);
			
			//setLeaseTypeLocator(txtLeaseType);
			//WebElement element=getElementByXpathContainsText(txtLeaseType);
			//String locator="//span[text()='Expense Lease']";
			//WebElement element= driver.findElement(By.xpath(locator));
			
//			WebButton.webButtonClick(driver, logger, LeaseTypeSelector);
//			MouseOperations.ActionMoveToElementInFrame(driver, logger, ExpenseLease, SelectorFrame);
//			WebLink.clickOnWebLink(driver, ExpenseLease, logger);
//			
//			WebFrame.switchToMainPageFromIFrame(driver);
//			WebFrame.switchOnFrameById(driver, "contentFrame");
//			WebButton.webButtonClick(driver, logger, SecondaryLeaseTypeSelector);
//			MouseOperations.ActionMoveToElementInFrame(driver, logger, LandlordLease, SelectorFrame);
//			WebLink.clickOnWebLink(driver, LandlordLease, logger);
//			
//			WebFrame.switchToMainPageFromIFrame(driver);
//			WebFrame.switchOnFrameById(driver, "contentFrame");
//			WebButton.webButtonClick(driver, logger, ContractStatusSelector);
//			MouseOperations.ActionMoveToElementInFrame(driver, logger, ContractStatusActive, SelectorFrame);
//			WebLink.clickOnWebLink(driver, ContractStatusActive, logger);
			
			SelectLeaseType(txtLeaseType);
			SelectSecondaryLeaseType(txtSecLeaseType);
			SelectContractStatus(Contrtatus);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnFrameById(driver, "contentFrame");
			WebTextbox.sendTextToWebInput(CommenceDate, attCommenceDate, logger);
			WebTextbox.sendTextToWebInput(ExpireDate, attExpireDate, logger);
			
			WebPage.scrollingToBottomOfAPage(driver);
			Thread.sleep(3000);
			WebLink.clickOnWebLink(driver, BusinessUnit, logger);
			WebLink.clickOnWebLink(driver, BusinessUnit, logger);
			WebTextbox.sendTextToWebInput(BusinessUnit, BusUnit, logger);
			String ActualBusUnit=WebUtilities.getText(driver, BusinessUnit, "value");
			System.out.println("Business Unit="+ActualBusUnit);

			//MouseOperations.PerformKeywordActions(driver, logger, "TAB");
			Actions act1=new Actions(driver);
			act1.sendKeys(Keys.TAB).build().perform();
			
			LeaseGenPgStatus=true;
		}catch(Exception e){
			LeaseGenPgStatus=false;
		}

		
		return LeaseGenPgStatus;
		
	}
	
	public String GetLeaseID() throws InterruptedException{
		
		System.out.println("Get the lease id");
		WebFrame.switchToMainPageFromIFrame(driver);
		WebButton.activeElementPressKeyBoardKeys(driver, "TAB", Generaltab);
		WebLink.clickOnWebLink(driver, Generaltab, logger);	
		try {
			Thread.sleep(3000);
			driver.switchTo().frame("contentFrame");
			LeaseNo=WebTextbox.GetTextFromWebInput(driver, LeaseIdlbl, logger);
			if (LeaseNo.length()>0){
				System.out.println("lease id ="+LeaseNo);
				logger.log(LogStatus.PASS, "Lease ID -"+ LeaseNo+",is created successfully");
			}else{
				logger.log(LogStatus.FAIL, "Lease ID is not created");
			}
				
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return LeaseNo;
		
	}
	
	public void ClickOnLeaseSubmitbtn(){
		
		WebBrowser.switchToChildWindow2(driver);
		WebFrame.switchToMainPageFromIFrame(driver);
		WebButton.webButtonClick(driver, logger, Lease_Submitbtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//WebUtilities.waitForElementToDisappear(driver, Lease_Submitbtn, logger);
		
	}
	
	public boolean ClickOnTerminate() {

		boolean TerminatePgStatus;
		try {
			WebBrowser.switchToChildWindow(driver);
			driver.manage().window().maximize();
			WebUtilities.waitForElementToAppear(driver, Terminatebtn, logger);
			WebButton.webButtonClick(driver, logger, Terminatebtn);
			Thread.sleep(6000);
			TerminatePgStatus = true;
		} catch (Exception e) {
			TerminatePgStatus = false;
		}
		return TerminatePgStatus;
	}

	public boolean ClickOnContractRevise() {

		boolean contractReviseBtnStatus;
		try {
			WebBrowser.switchToChildWindow(driver);
			driver.manage().window().maximize();
			WebUtilities.waitForElementToAppear(driver, contractReviseBtn, logger);
			WebButton.webButtonClick(driver, logger, contractReviseBtn);
			Thread.sleep(6000);
			contractReviseBtnStatus = true;
		} catch (Exception e) {
			contractReviseBtnStatus = false;
		}
		return contractReviseBtnStatus;
	}

	public void selectContractReviseChangeType(String changeType) {

		try {
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame, ChildFrame,ContentFrame);
			WebLink.clickOnWebLink(driver, contractRevise_ChangeType, logger);
			WebFrame.switchOnMultipleFramesByXpath(driver, unMovableLayerFrame);
			WebElement element = driver.findElement(By.xpath(contractRevise_Options.replace("PLACEHOLDER", changeType)));
			WebUtilities.waitForElementToAppear(driver, element, logger);
			element.click();
			Thread.sleep(100);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, ContentFrame, ChildFrame,ContentFrame);
			logger.log(LogStatus.INFO, " Selected Change Type - " + changeType);
			WebButton.webButtonClick(driver, logger, contractRevise_Continue_Btn);
			logger.log(LogStatus.INFO, "Clicked on Continue Button.");
			Thread.sleep(6000);
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Failed to select option -  " + changeType + e.getMessage());
			e.printStackTrace();
		}

	}

	

}
