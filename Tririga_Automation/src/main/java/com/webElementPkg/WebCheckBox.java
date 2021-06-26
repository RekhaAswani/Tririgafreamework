/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/**
 * The Class WebCheckBox.
 *
 * @author 
 */
public class WebCheckBox {
	
	public static void webCheckBoxClick(WebDriver driver, ExtentTest logger, WebElement element) {
		try {
			if (element != null && element.isEnabled()) {
				element.click();
				logger.log(LogStatus.PASS, "Checkbox- '" +  element.toString() + "' successfully clicked");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Failed to click '" + element.toString() + "' </br>" + e.getStackTrace().toString());
		}
	}

}
