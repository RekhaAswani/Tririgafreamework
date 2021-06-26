/**
 * 
 */
package com.webElementPkg;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class WebBrowser.
 *
 * @author 
 */
public class WebBrowser {

	/**
	 * Switch to child window.
	 *
	 * @param driver the driver
	 * @param childPagetitle the child pagetitle
	 */
	
	public static String retrieveCurrentWindowHandle(WebDriver driver, ExtentTest logger) {
		try {
			return driver.getWindowHandle();
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Failed to retrieve the Current Window Handle </br>" + e.getStackTrace().toString());
			throw e;
		}
	}
	
	/**
	 * Quit the windows associated with the driver
	 * 
	 * @param driver
	 * @param logger
	 */
	public static void quitWindows(WebDriver driver, ExtentTest logger) {
		try {
			driver.quit();
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Failed to quit the windows. </br>" + e.getStackTrace().toString());
			throw e;
		}
	}

	/**
	 * Close the Current Window
	 * 
	 * @param driver
	 * @param logger
	 */
	public static void closeWindow(WebDriver driver, ExtentTest logger) {
		try {
			driver.close();
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Failed to close the window. </br>" + e.getStackTrace().toString());
			throw e;
		}
	}
	/**
	 * Switch to Given Window Handler
	 * 
	 * @param driver
	 * @param logger
	 * @param windowHandler
	 */
	public static void switchToWindow(WebDriver driver, ExtentTest logger, String windowHandler) {
		try {
			driver.switchTo().window(windowHandler);
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Failed to switch to given window handler </br>" + e.getStackTrace().toString());
		}
	}
	
	public static void switchToChildWindow(WebDriver driver, String childPagetitle) {
		Set<String> allWin = driver.getWindowHandles();
		String parentWindow = driver.getWindowHandle();

		for (String childWindow : allWin) {
			if (!childWindow.equalsIgnoreCase(parentWindow)) {
				if (driver.switchTo().window(childWindow).getTitle().equalsIgnoreCase(childPagetitle)) {
					break;
				}
			}
		}
	}

	/**
	 * Switch to child window.
	 *
	 * @param driver the driver
	 */
	public static void switchToChildWindow(WebDriver driver) {
		Set<String> allWin = driver.getWindowHandles();
		String parentWindow = driver.getWindowHandle();
		for (String childWindow : allWin) {
			if (!childWindow.equalsIgnoreCase(parentWindow)) {
				driver.switchTo().window(childWindow);
				System.out.println("ChildWindow="+childWindow.toString());
				break;
			}
		}
	}
	
	public static void switchToChildWindow2(WebDriver driver){
		String currentWindow = driver.getWindowHandle();
		Set<String> winHandleBefore = driver.getWindowHandles();
		for(String winHandle : winHandleBefore)
		{
		   driver.switchTo().window(winHandle);
		}   
		
	}
	
	public static void switchToParentWindow(WebDriver driver){
		Set<String> winHandleBefore = driver.getWindowHandles();
		for(String winHandle : winHandleBefore)
		{
		   driver.switchTo().window(winHandle);
		   System.out.println("Switch To Parent Window="+winHandle);
		   break;
		}   
		
	}
	
}
