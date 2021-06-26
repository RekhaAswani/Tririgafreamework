/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class MouseOperations {
		
	public static void hoverMouseOnWebElement(WebDriver driver, ExtentTest logger, WebElement element) {
		try {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error hovering over the element</br>" + e.getCause());
		}
	}
	
	public static void ActionMoveToElement(WebDriver driver, ExtentTest logger, WebElement element){
		try {
			//WebTextbox.sendTextToWebInput(element, textToEnter, logger);
			Actions action = new Actions(driver);
			action.moveToElement(element);
			logger.log(LogStatus.INFO, "Move to element"+element.toString());
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error hovering over the element</br>" + e.getCause());
		}
		
	}
	
	public static void ActionMoveToElementInFrame(WebDriver driver, ExtentTest logger, WebElement element,String FrameName){
		try {
			driver.switchTo().frame(FrameName);
			//WebTextbox.sendTextToWebInput(element, textToEnter, logger);
			Actions action = new Actions(driver);
			action.moveToElement(element);
			logger.log(LogStatus.INFO, "Move to element"+element.toString());
		} catch (Exception e) {
			logger.log(LogStatus.ERROR, "Error hovering over the element</br>" + e.getCause());
		}
		
	}
	
	public static void PerformKeywordActions(WebDriver driver, ExtentTest logger,String keyBoardInput){
		
		
		Actions act=new Actions(driver);	
		try{
        switch(keyBoardInput) {
        case "TAB" :
        	  act.sendKeys(Keys.TAB).build().perform();
              break;
        case "ENTER" :
        	  act.sendKeys(Keys.ENTER).build().perform();
        	  Thread.sleep(2000);
              break;

        default :
              System.out.println("Invalid keyBoardInput");
        }
        //driver.switchTo().activeElement().sendKeys(Keys.SHIFT,Keys.TAB);
        Thread.sleep(2000);

		 }catch(Exception e){
		       
		        //Thread.sleep(3000);
		 }
		
	}
	
	

}
