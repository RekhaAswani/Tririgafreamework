/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class WebLink.
 *
 * @author 
 */
public class WebLink {

	/**
	 * Click on web link.
	 *
	 * @param driver the driver
	 * @param element the element
	 */
	public static void clickOnWebLink(WebDriver driver, WebElement element,ExtentTest logger) {
		try {
			if (element != null) {
				element.click();
				logger.log(LogStatus.PASS, "Link- '" +  element.toString() + "' successfully clicked");
				Thread.sleep(4000);
			}else {
				System.out.println("WebLink is empty. Cannot click");
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Failed to click '" + element.toString() + "' </br>" + e.getStackTrace().toString());
			System.out.println("Error occured while clicking on weblink..\n");
			e.printStackTrace();
		}
	}
}
