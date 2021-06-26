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
import com.webElementPkg.WebTable;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Portfolio_LocationsPage {
	
	private WebDriver driver=null;
	private ExtentTest logger=null;
	public WebElement leaseType;
	public static boolean FoundFlag=false;
	public static String FloorName;
	public static String BuildingName;
	public static final String Locations_MainFrame="//iframe[@class='gwt-Frame GDNOS4QBO-']";
	public static final String Locations_TreeFrame1="//iframe[@id='treeFrame']";
	public static final String Locations_TreeFrame2="//iframe[@id='tree']";
	public static final String Locations_ContentFrame="//iframe[@id='contentFrame']";
	public static final String Locations_ObjectFrame="//iframe[@id='objectsFrame']";
	public static final String Locations_ListFrame="//iframe[@id='listFrame']";
	public static final String Locations_ChildFrame="//iframe[@id='childFrame']";
	public static final String Locations_frameMainFrame="//frame[@id='frameMain']";
	public static String SelectorFrame="unMovableLayerFrame";
	
	
	
	@FindBy(xpath="//ul[@id='hierarcyTree213004']")
	public WebElement LocationsHierarchy;
	
	@FindBy(xpath="//a[text()='New']")
	public WebElement Newlnk;	
	
	@FindBy(xpath="//a[text()='Open']")
	public WebElement Openlnk;
	
	@FindBy(xpath="//span[text()='Floor']")
	public WebElement Tree_Floorlnk;
	
	@FindBy(xpath="//span[text()='Space']")
	public WebElement Tree_Spacelnk;
	
	@FindBy(xpath="//span[text()='Building']")
	public WebElement Tree_Buildinglnk;
	
	@FindBy(xpath="//span[text()='Land']")
	public WebElement Tree_Landlnk;
	
	@FindBy(xpath="//input[@id='attr_seq_1044']")
	public WebElement IDtxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1045']")
	public WebElement Nametxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1126']")
	public WebElement FloorLeveltxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1154']")
	public WebElement GrossAreatxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1153']")
	public WebElement GrossMeasuredAreatxt;
	
	@FindBy(xpath="//*[@id='Create_Draft']/span")
	public WebElement CreateDraftbtn;
	
	@FindBy(xpath="//*[@id='Activate']/span")
	public WebElement Activatebtn;
	
	@FindBy(xpath="//input[@id='filterValue1']")
	public WebElement NameFilter;
	
	@FindBy(xpath="//input[@id='attr_seq_1045']")
	public WebElement SpaceNametxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1299']")
	public WebElement InServicetxt;
	
	@FindBy(xpath="//input[@id='attr_seq_1055']")
	public WebElement CleanUpAssetschk;
	
	@FindBy(xpath="//input[@id='attr_seq_1051']")
	public WebElement CleanUpPeoplechk;
	
	@FindBy(xpath="//input[@id='attr_seq_1052']")
	public WebElement CleanUpOrgAssignmentschk;
	
	
	@FindBy(xpath="//a[@id='attr_seq_1404_selector']")
	public WebElement SpaceUsageTypeSelector;
	
	@FindBy(xpath="//div[@id='Find_798']")
	public WebElement Findbtn;
	
	@FindBy(xpath="//input[@type='radio' and @name='sNo']")
	public WebElement RadioBtn;
	
	@FindBy(xpath="//table[@id='actionsTable']//span[@class='actionButtonText' and text()='OK']")
	public WebElement Okbtn;
	
	@FindBy(xpath="//span[@class='tririgaResultsActionButton' and text()='Clear Filters']")
	public WebElement ClearFilterlnk;
	
	@FindBy(xpath="//table[@id='dataTab']//tbody//tr[@id='queryResultRow_0']//td[@headers='row_id_1 columnStatus']//span[@class='bodyText']//a")
	public WebElement StatusValue;
	
	@FindBy(xpath="//a[@id='attr_seq_1106_selector']")
	public WebElement LandClassSelector;
	
	@FindBy(xpath="//a[@id='attr_seq_1047_selector']")
	public WebElement DispositionMethodSelector;
	
	@FindBy(xpath="//div[@id='OverflowBtn']")
	public WebElement Morebtn;
	
	@FindBy(xpath="//span[text()='Delete']")
	public WebElement Deletebtn;
	
	@FindBy(xpath="//span[text()='Retire']")
	public WebElement Retirebtn;
	
	@FindBy(xpath="//span[@id='attr_seq_1046display']")
	public WebElement Statuslbl;
	
	@FindBy(xpath="//div[@id='x']")
	public WebElement Closebtn;
	
	@FindBy(xpath="//div[@id='Create']")
	public WebElement Createbtn;
	
	public Portfolio_LocationsPage(WebDriver driver,ExtentTest logger){
		
		this.driver=driver;
		this.logger=logger;
		FloorName=null;
		BuildingName=null;
		PageFactory.initElements(driver, this);

	}
	
	public void ExpandProperty() throws InterruptedException{		
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> PropertyValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//img[@title='Expand']"));
		System.out.println("No. Tree values="+PropertyValues.size());  
		for(int i=0;i<=PropertyValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//img[@title='Expand']")).get(i);
			System.out.println("Tree Value="+Value.getText());
			//Value.click();
			WebButton.webButtonClick(driver, logger, Value);
			FoundFlag=true;
			break;
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Expand Property value'");
		}
	}
	
	public void SelectProperty() throws InterruptedException{		
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> BuildingValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Property')]"));
		System.out.println("No. Tree values="+BuildingValues.size());  
		for(int i=0;i<=BuildingValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Property')]")).get(i);
			System.out.println("Tree value="+Value.getText());
			WebButton.webButtonClick(driver, logger, Value);
			FoundFlag=true;
			break;
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Expand Property value'");
		}
	}
	
	public void SelectSpaceFromTree(String SpaceName) throws InterruptedException{		
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> SpaceValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Space')]"));
		System.out.println("No. Tree values="+SpaceValues.size());  
		for(int i=0;i<=SpaceValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Space')]")).get(i);
			System.out.println("Tree value="+Value.getText());
			if(Value.getText().contains(SpaceName)){
				WebButton.webButtonClick(driver, logger, Value);
				FoundFlag=true;
				break;
			}

		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Select Space value'");
		}
	}
	
	public void SelectLandFromTree(String LandName) throws InterruptedException{		
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> LandValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Land')]"));
		System.out.println("No. Tree values="+LandValues.size());  
		for(int i=0;i<=LandValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Land')]")).get(i);
			System.out.println("Tree value="+Value.getText());
			if(Value.getText().contains(LandName)){
				WebButton.webButtonClick(driver, logger, Value);
				FoundFlag=true;
				break;
			}

		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Select Space value'");
		}
	}
	public void SelectLandClass(String LandClass) throws InterruptedException{
		
		//boolean FoundFlag=false;
		
		WebButton.webButtonClick(driver, logger, LandClassSelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587447']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_1587447']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("Land Class="+Value.getText());
			if(Value.getText().equals(LandClass)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  LandClass + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + LandClass);
		}
	}
	
	public void SelectDispositionMethod(String DisMethod) throws InterruptedException{
		
		//boolean FoundFlag=false;
		
		WebButton.webButtonClick(driver, logger, DispositionMethodSelector);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree_3526262']//span[contains(@id,'CONTENT')]"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree_3526262']//span[contains(@id,'CONTENT')]")).get(i);
			System.out.println("Disposition Method="+Value.getText());
			if(Value.getText().equals(DisMethod)){
				MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				logger.log(LogStatus.INFO, "List Values- '" +  DisMethod + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + DisMethod);
		}
	}

	public void SelectBuilding() throws InterruptedException{		
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> BuildingValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Building')]"));
		System.out.println("No. Tree values="+BuildingValues.size());  
		for(int i=0;i<=BuildingValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Building')]")).get(i);
			System.out.println("Tree value="+Value.getText());
			WebButton.webButtonClick(driver, logger, Value);
			FoundFlag=true;
			break;
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Expand Property value'");
		}
	}
	
	public void SelectFloor(String FlrName) throws InterruptedException{		
		
		FoundFlag=false;
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> FloorValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Floor')]"));
		System.out.println("No. Tree values="+FloorValues.size());  
		for(int i=0;i<=FloorValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Floor')]")).get(i);
			System.out.println("Tree value="+Value.getText());
			if(Value.getText().contains(FlrName)){
				WebButton.webButtonClick(driver, logger, Value);
				FoundFlag=true;
				break;
			}

		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Expand Property value'");
		}
	}
	
	public void SelectANDExpandBuilding(String BuildingName) throws InterruptedException{		
		
		FoundFlag=false;
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> BuildingValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Building')]"));
		System.out.println("No. Tree values="+BuildingValues.size());  
		for(int i=0;i<=BuildingValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Building')]")).get(i);
			System.out.println("Tree value="+Value.getText());
			if(Value.getText().contains(BuildingName)){
				WebButton.webButtonClick(driver, logger, Value);
				String id=Value.getAttribute("id");
				String Temp=id.replace("true_CONTENT", "true_ACTIONIMG");
				System.out.println("Dynamic xpath="+"//img[@id='"+Temp+"']");
				driver.findElement(By.xpath("//img[@id='"+Temp+"']")).click();
				//img[@id="hierarcyTree213004_129277715*10002100*10002954*true_ACTIONIMG"]
				//WebElement Expand =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//img[@title='Expand']")).get(i);
				//Expand.click();
				FoundFlag=true;
				break;
			}

		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Expand Building value'");
		}
	}
	
	public void SelectANDExpandFloor(String FloorName) throws InterruptedException{		
		
		FoundFlag=false;
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);

		List<WebElement> BuildingValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Floor')]"));
		System.out.println("No. Tree values="+BuildingValues.size());  
		for(int i=0;i<=BuildingValues.size();i++){
			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Floor')]")).get(i);
			System.out.println("Tree value="+Value.getText());
			if(Value.getText().contains(FloorName)){
				WebButton.webButtonClick(driver, logger, Value);
				String id=Value.getAttribute("id");
				String Temp=id.replace("true_CONTENT", "true_ACTIONIMG");
				System.out.println("Dynamic xpath="+"//img[@id='"+Temp+"']");
				driver.findElement(By.xpath("//img[@id='"+Temp+"']")).click();
				FoundFlag=true;
				break;
			}

		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to Expand Building value'");
		}
	}

//	public void SelectANDExpandFloor1(String FlrName) throws InterruptedException{		
//		
//		FoundFlag=false;
//		
//		WebFrame.switchToMainPageFromIFrame(driver);
//		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
//
//		List<WebElement> FloorValues = driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Floor')]"));
//		System.out.println("No. Tree values="+FloorValues.size());  
//		for(int i=0;i<=FloorValues.size();i++){
//			WebElement Value =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//span[contains(text(),'Floor')]")).get(i);
//			System.out.println("Tree value="+Value.getText());
//			if(Value.getText().contains(FlrName)){
//				WebButton.webButtonClick(driver, logger, Value);
//				WebElement Expand =driver.findElements(By.xpath("//ul[@id='hierarcyTree213004']//img[@title='Expand']")).get(i);
//				Expand.click();
//				FoundFlag=true;
//				break;
//			}
//
//		}
//		if (FoundFlag=false){
//			logger.log(LogStatus.ERROR,"Failed to Expand Property value'");
//		}
//	}
	

	public void SelectSpaceUsageType(String UsageType) throws InterruptedException{
		
		//boolean FoundFlag=false;
		try{
			
		WebButton.webButtonClick(driver, logger, SpaceUsageTypeSelector);
		Thread.sleep(3000);
		WebFrame.switchOnFrameById(driver, SelectorFrame);
		List<WebElement> DropDownValues = driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']"));
		System.out.println("DropDown values="+DropDownValues.size());  
		for(int i=0;i<=DropDownValues.size();i++){
			//WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			WebElement Value =driver.findElements(By.xpath("//table[@id='listValueTable']//span[@class='bodyText']")).get(i);
			System.out.println("UsageType="+Value.getText());
			if(Value.getText().contains(UsageType)){
				//MouseOperations.ActionMoveToElement(driver, logger, Value);
				Value.click();
				//WebButton.webButtonClick(driver, logger, Value);
				//Actions act1=new Actions(driver);
				//act1.sendKeys(Keys.TAB).build().perform();
				logger.log(LogStatus.INFO, "List Values- '" +  UsageType + "' successfully selected");
				FoundFlag=true;
				Thread.sleep(2000);
				WebFrame.switchToMainPageFromIFrame(driver);
				break;
			}
		}
		if (FoundFlag=false){
			logger.log(LogStatus.ERROR,"Failed to select '" + UsageType);
		}
		}catch(Exception e){
			logger.log(LogStatus.ERROR,"Failed to select Space Usage Type '" + e.getMessage());
		}
		
		
	}
	
	public boolean CreateNewBuilding(String dServiceDate) throws InterruptedException{		
		
		boolean NewFloor=false;
		BuildingName="Building_"+WebUtilities.GetTimeStamp();
		try{
			
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
		//ExpandProperty();
		SelectProperty();		
		WebLink.clickOnWebLink(driver, Newlnk, logger);
		WebLink.clickOnWebLink(driver, Tree_Buildinglnk, logger);
		
		WebBrowser.switchToChildWindow(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
		WebTextbox.sendTextToWebInput(Nametxt, BuildingName, logger);		
		WebTextbox.sendTextToWebInput(InServicetxt, dServiceDate, logger);
		Thread.sleep(2000);
//		WebFrame.switchToMainPageFromIFrame(driver);
		WebPage.scrollingToBottomOfAPage(driver);
		WebButton.webButtonClick(driver, logger, CreateDraftbtn);
		WebButton.webButtonClick(driver, logger, CreateDraftbtn);
		WebUtilities.waitForElementToAppear(driver, Activatebtn, logger);
		WebUtilities.waitForElementToAppear(driver, Activatebtn, logger);
		String BuildingId=WebTextbox.GetAttributeValueFromInputbox(driver, IDtxt, logger);
		if(BuildingId.length()>0){
			logger.log(LogStatus.PASS, "Floor ID '" + BuildingId + "' successfully created");
		}else{
			logger.log(LogStatus.FAIL, "Floor ID '" + BuildingId + "' not created");
		}
		WebButton.webButtonClick(driver, logger, Activatebtn);
		WebUtilities.waitForElementToDisappear(driver, Activatebtn, logger);

		WebBrowser.switchToParentWindow(driver);
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_ObjectFrame,Locations_ListFrame);
		WebTextbox.sendTextToWebInput(NameFilter, BuildingName, logger);
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		String FloorStatus=WebTextbox.GetTextFromWebInput(driver, StatusValue, logger);
		System.out.println("Floor Status="+FloorStatus);
			if(FloorStatus.equalsIgnoreCase("Active")){
				logger.log(LogStatus.PASS, "Floor Status '" + FloorStatus + "' successfully created");
				NewFloor=true;
			}else{
				logger.log(LogStatus.FAIL, "Floor Status is '" + FloorStatus);
				NewFloor=false;
			}
		}catch(Exception e){
			NewFloor=false;
		}
		return NewFloor;
	}
	
	public boolean CreateNewLand(String LandCls) throws InterruptedException{		
		
		boolean NewFloor=false;
		String LandName="Land_"+WebUtilities.GetTimeStamp();
		try{
		
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
		SelectProperty();		
		WebLink.clickOnWebLink(driver, Newlnk, logger);
		WebLink.clickOnWebLink(driver, Tree_Landlnk, logger);
		
		WebBrowser.switchToChildWindow(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
		WebTextbox.sendTextToWebInput(Nametxt, LandName, logger);
		SelectLandClass(LandCls);
		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		WebButton.webButtonClick(driver, logger, CreateDraftbtn);
		WebUtilities.waitForElementToAppear(driver, Activatebtn, logger);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
		String FloorId=WebTextbox.GetAttributeValueFromInputbox(driver, IDtxt, logger);
		if(FloorId.length()>0){
			logger.log(LogStatus.PASS, "Floor ID '" + FloorId + "' successfully created");
		}else{
			logger.log(LogStatus.FAIL, "Floor ID '" + FloorId + "' not created");
		}
		driver.switchTo().defaultContent();
		WebButton.webButtonClick(driver, logger, Activatebtn);
		WebUtilities.waitForElementToDisappear(driver, Activatebtn, logger);

		WebBrowser.switchToParentWindow(driver);
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_ObjectFrame,Locations_ListFrame);
		WebTextbox.sendTextToWebInput(NameFilter, LandName, logger);
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		String FloorStatus=WebTextbox.GetTextFromWebInput(driver, StatusValue, logger);
		System.out.println("Floor Status="+FloorStatus);
			if(FloorStatus.equalsIgnoreCase("Active")){
				logger.log(LogStatus.PASS, "Floor Status '" + FloorStatus + "' successfully created");
				NewFloor=true;
			}else{
				logger.log(LogStatus.FAIL, "Floor Status is '" + FloorStatus);
				NewFloor=false;
			}
		}catch(Exception e){
			NewFloor=false;
		}
		return NewFloor;
	}


	public boolean CreateNewFloor(String Floorlevel,String GrssArea,String GrssMeaArea) throws InterruptedException{		
		
		boolean NewFloor=false;
		FloorName="Floor_"+WebUtilities.GetTimeStamp();
		try{
		
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
		ExpandProperty();
		SelectBuilding();		
		WebLink.clickOnWebLink(driver, Newlnk, logger);
		WebLink.clickOnWebLink(driver, Tree_Floorlnk, logger);
		
		WebBrowser.switchToChildWindow(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
		WebTextbox.sendTextToWebInput(Nametxt, FloorName, logger);
		WebTextbox.sendTextToWebInput(FloorLeveltxt, Floorlevel, logger);
		WebTextbox.sendTextToWebInput(GrossAreatxt, GrssArea, logger);
		WebTextbox.sendTextToWebInput(GrossMeasuredAreatxt, GrssMeaArea, logger);
		Thread.sleep(2000);
		WebButton.webButtonClick(driver, logger, CreateDraftbtn);
		WebUtilities.waitForElementToAppear(driver, Activatebtn, logger);
		String FloorId=WebTextbox.GetAttributeValueFromInputbox(driver, IDtxt, logger);
		if(FloorId.length()>0){
			logger.log(LogStatus.PASS, "Floor ID '" + FloorId + "' successfully created");
		}else{
			logger.log(LogStatus.FAIL, "Floor ID '" + FloorId + "' not created");
		}
		WebButton.webButtonClick(driver, logger, Activatebtn);
		WebUtilities.waitForElementToDisappear(driver, Activatebtn, logger);

		WebBrowser.switchToParentWindow(driver);
		WebFrame.switchToMainPageFromIFrame(driver);
		WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_ObjectFrame,Locations_ListFrame);
		WebTextbox.sendTextToWebInput(NameFilter, FloorName, logger);
		MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
		String FloorStatus=WebTextbox.GetTextFromWebInput(driver, StatusValue, logger);
		System.out.println("Floor Status="+FloorStatus);
			if(FloorStatus.equalsIgnoreCase("Active")){
				logger.log(LogStatus.PASS, "Floor Status '" + FloorStatus + "' successfully created");
				NewFloor=true;
			}else{
				logger.log(LogStatus.FAIL, "Floor Status is '" + FloorStatus);
				NewFloor=false;
			}
		}catch(Exception e){
			NewFloor=false;
		}
		return NewFloor;
	}
	
	public boolean CreateNewSpace(String FloorName,String strSpaceUsageType) throws InterruptedException{		
		
		boolean NewFloor=false;
		String SpaceName="Space_"+WebUtilities.GetTimeStamp();
		try{
		
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			ExpandProperty();
			SelectFloor(FloorName);		
			WebLink.clickOnWebLink(driver, Newlnk, logger);
			WebLink.clickOnWebLink(driver, Tree_Spacelnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			driver.manage().window().maximize();
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			WebTextbox.sendTextToWebInput(SpaceNametxt, SpaceName, logger);
			SelectSpaceUsageType(strSpaceUsageType);
			
			//WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			WebPage.scrollingToBottomOfAPage(driver);
			WebButton.webButtonClick(driver, logger, Findbtn);
			Thread.sleep(4000);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame,Locations_ChildFrame,Locations_frameMainFrame,Locations_ContentFrame);
			WebButton.webButtonClick(driver, logger, RadioBtn);
			WebButton.webButtonClick(driver, logger, Okbtn);
			
			WebButton.webButtonClick(driver, logger, CreateDraftbtn);
			WebUtilities.waitForElementToAppear(driver, Activatebtn, logger);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			String FloorId=WebTextbox.GetAttributeValueFromInputbox(driver, IDtxt, logger);
			if(FloorId.length()>0){
				logger.log(LogStatus.PASS, "Space ID '" + FloorId + "' successfully created");
			}else{
				logger.log(LogStatus.FAIL, "Space ID '" + FloorId + "' not created");
			}
			WebButton.webButtonClick(driver, logger, Activatebtn);
			WebUtilities.waitForElementToDisappear(driver, Activatebtn, logger);
	
			WebBrowser.switchToParentWindow(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			SelectFloor(FloorName);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_ObjectFrame,Locations_ListFrame);
			//WebTextbox.sendTextToWebInput(NameFilter, SpaceName, logger);
			//MouseOperations.PerformKeywordActions(driver, logger, "ENTER");
			String SpaceStatus=WebTextbox.GetTextFromWebInput(driver, StatusValue, logger);
			System.out.println("Space Status="+SpaceStatus);
				if(SpaceStatus.equalsIgnoreCase("Active")){
					logger.log(LogStatus.PASS, "Space Status '" + SpaceStatus + "' successfully created");
					NewFloor=true;
				}else{
					logger.log(LogStatus.FAIL, "Space Status is '" + SpaceStatus);
					NewFloor=false;
				}
		}catch(Exception e){
			NewFloor=false;
			logger.log(LogStatus.FAIL, "Space Creation failed '" + e.getMessage());
		}
		return NewFloor;
	}
	
	public boolean RetireSpace(String BuildingName,String FloorName,String SpaceName){
		
		boolean RetireFloor=false;
		
		try{
		
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			ExpandProperty();
			SelectANDExpandBuilding(BuildingName);
			SelectANDExpandFloor(FloorName);		
			SelectSpaceFromTree(SpaceName);
			WebLink.clickOnWebLink(driver, Openlnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			WebButton.webButtonClick(driver, logger, Morebtn);
			WebButton.webButtonClick(driver, logger, Retirebtn);
			Thread.sleep(2000);
			String Status=WebTextbox.GetTextFromWebInput(driver, Statuslbl, logger);
			if(Status.equalsIgnoreCase("Retired")){
				logger.log(LogStatus.PASS, "Space is successfully RETIRED");
				RetireFloor=true;
			}else{
				logger.log(LogStatus.FAIL, "Space Status is "+Status);
				RetireFloor=false;
			}
			WebButton.webButtonClick(driver, logger, Closebtn);
			Thread.sleep(2000);
			WebBrowser.switchToParentWindow(driver);
		}catch(Exception e){
			RetireFloor=false;
		}
		return RetireFloor;
			
	}
	public boolean RetireFloor(String FloorName,String DispMethod){
		
		boolean RetireFloor=false;
		
		try{
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			//ExpandProperty();
			//SelectANDExpandBuilding(BuildingName);
			SelectFloor(FloorName);		
			WebLink.clickOnWebLink(driver, Openlnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			WebButton.webButtonClick(driver, logger, Morebtn);
			WebButton.webButtonClick(driver, logger, Retirebtn);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame,Locations_ChildFrame,Locations_ContentFrame);
			SelectDispositionMethod(DispMethod);
			
//			WebButton.webButtonClick(driver, logger, CleanUpAssetschk);
//			WebButton.webButtonClick(driver, logger, CleanUpPeoplechk);
//			WebButton.webButtonClick(driver, logger, CleanUpOrgAssignmentschk);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame,Locations_ChildFrame);
			WebButton.webButtonClick(driver, logger, Createbtn);
			
			WebBrowser.switchToParentWindow(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			SelectFloor(FloorName);		
			WebLink.clickOnWebLink(driver, Openlnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			String Status=WebTextbox.GetTextFromWebInput(driver, Statuslbl, logger);
			if(Status.equalsIgnoreCase("Retired")){
				logger.log(LogStatus.PASS, "Floor is successfully RETIRED");
				RetireFloor=true;
			}else{
				logger.log(LogStatus.FAIL, "Floor Status is "+Status);
				RetireFloor=false;
			}
			WebButton.webButtonClick(driver, logger, Closebtn);
			WebBrowser.switchToParentWindow(driver);
			
		}catch(Exception e){
			RetireFloor=false;
		}
		return RetireFloor;
			
	}
	
	public boolean RetireBuilding(String BuildingName,String DispMethod){
		
		boolean RetireFloor=false;
		
		try{
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			ExpandProperty();
			SelectANDExpandBuilding(BuildingName);
			WebLink.clickOnWebLink(driver, Openlnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			WebButton.webButtonClick(driver, logger, Morebtn);
			WebButton.webButtonClick(driver, logger, Retirebtn);
			
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame,Locations_ChildFrame,Locations_ContentFrame);
			SelectDispositionMethod(DispMethod);
						
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame,Locations_ChildFrame);
			WebButton.webButtonClick(driver, logger, Createbtn);
			Thread.sleep(2000);
			
			WebBrowser.switchToParentWindow(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			SelectANDExpandBuilding(BuildingName);		
			WebLink.clickOnWebLink(driver, Openlnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			String Status=WebTextbox.GetTextFromWebInput(driver, Statuslbl, logger);
			if(Status.equalsIgnoreCase("Retired")){
				logger.log(LogStatus.PASS, "Building is successfully RETIRED");
				RetireFloor=true;
			}else{
				logger.log(LogStatus.FAIL, "Building Status is "+Status);
				RetireFloor=false;
			}
			WebButton.webButtonClick(driver, logger, Closebtn);
			WebBrowser.switchToParentWindow(driver);
			
		}catch(Exception e){
			RetireFloor=false;
		}
		return RetireFloor;
			
	}
	
	public boolean RetireLand(String LandName){
		
		boolean RetireFloor=false;
		
		try{
		
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
//			ExpandProperty();
//			SelectANDExpandBuilding(BuildingName);
//			SelectANDExpandFloor(FloorName);		
			SelectLandFromTree(LandName);
			WebLink.clickOnWebLink(driver, Openlnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			WebButton.webButtonClick(driver, logger, Morebtn);
			WebButton.webButtonClick(driver, logger, Retirebtn);
			Thread.sleep(2000);
			
			WebBrowser.switchToParentWindow(driver);
			WebFrame.switchToMainPageFromIFrame(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_MainFrame,Locations_TreeFrame1,Locations_TreeFrame2);
			SelectLandFromTree(LandName);		
			WebLink.clickOnWebLink(driver, Openlnk, logger);
			
			WebBrowser.switchToChildWindow(driver);
			WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			//WebFrame.switchOnMultipleFramesByXpath(driver, Locations_ContentFrame);
			String Status=WebTextbox.GetTextFromWebInput(driver, Statuslbl, logger);
			if(Status.equalsIgnoreCase("Retired")){
				logger.log(LogStatus.PASS, "Land is successfully RETIRED");
				RetireFloor=true;
			}else{
				logger.log(LogStatus.FAIL, "Land Status is "+Status);
				RetireFloor=false;
			}
			WebButton.webButtonClick(driver, logger, Closebtn);
			Thread.sleep(2000);
			WebBrowser.switchToParentWindow(driver);
		}catch(Exception e){
			RetireFloor=false;
		}
		return RetireFloor;
			
	}
}
