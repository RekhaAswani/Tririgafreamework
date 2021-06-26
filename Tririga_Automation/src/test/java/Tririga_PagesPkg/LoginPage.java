package Tririga_PagesPkg;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.webElementPkg.WebButton;
import com.webElementPkg.WebDropdown;
import com.webElementPkg.WebFrame;
import com.webElementPkg.WebTextbox;
import com.webElementPkg.WebUtilities;

//import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
//import org.apache.xmlbeans.impl.tool.XSTCTester.TestCase;
//import org.junit.Assert;

public class LoginPage {

	private WebDriver driver = null;
	private ExtentTest logger = null;

	public static final String MainFrame = "//iframe[@title='IBM TRIRIGA Index Page']";
	public static final String LoginFrame = "//iframe[@id='loginMain']";

	@FindBy(name = "USERNAME")
	WebElement LoginName;

	@FindBy(name = "PASSWORD")
	WebElement LoginPwd;

	// @FindBy(xpath="//input[@title='Log In']")
	// WebElement btnLogin;

	@FindBy(xpath = "//input[@class='loginButton']") // changed by Rekha
	WebElement btnLogin;

	// Changing xpath "//input[@class='loginButton']" old

	// constructor of login page
	public LoginPage(WebDriver driver, ExtentTest logger) {

		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);

	}

	private WebElement getUserName() {
		WebElement element = null;
		if (WebUtilities.waitForElementToAppear(driver, LoginName, logger)) {
			element = LoginName;
		}
		return element;
	}

	public boolean Tririga_Login(String User, String Pwd) throws InterruptedException, IOException {

		boolean LoginStatus = false;
		WebFrame.switchOnMultipleFramesByXpath(driver, MainFrame, LoginFrame);

		WebTextbox.sendTextToWebInput(LoginName, User, logger);
		WebTextbox.sendTextToWebInput(LoginPwd, Pwd, logger);
		WebButton.webButtonClick(driver, logger, btnLogin);
		System.out.println("Home page title=" + driver.getTitle());
		if (driver.getTitle().contains("Home")) {
			logger.log(LogStatus.PASS, "Verify Tririga Login", "Successfully logged into application");
			LoginStatus = true;
		} else {
			logger.log(LogStatus.FAIL, "Verify Tririga Login", "NOT logged into application");
			LoginStatus = false;
		}

		return LoginStatus;

	}

}
