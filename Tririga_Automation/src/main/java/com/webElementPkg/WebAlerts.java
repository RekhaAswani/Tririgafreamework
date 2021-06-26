/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

// TODO: Auto-generated Javadoc
/**
 * The Class WebAlerts.
 *
 * @author 
 */
public class WebAlerts {

	/**
	 * Accept alert.
	 *
	 * @param driver the driver
	 */
	public static void acceptAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	/**
	 * Dismiss alert.
	 *
	 * @param driver the driver
	 */
	public static void dismissAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}
}
