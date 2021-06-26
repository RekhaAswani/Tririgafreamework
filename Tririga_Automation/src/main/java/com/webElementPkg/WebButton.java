/**
 * 
 */
package com.webElementPkg;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

// TODO: Auto-generated Javadoc
/**
 * The Class WebButton.
 *
 * @author 
 */
public class WebButton {

	/**
	 * Web button click.
	 *
	 * @param driver the driver
	 * @param logger the logger
	 * @param element the element
	 */
	public static void webButtonClick(WebDriver driver, ExtentTest logger, WebElement element) {
		try {
			if (element != null && element.isEnabled()) {
				element.click();
				logger.log(LogStatus.PASS, "Button- '" +  element.toString() + "' successfully clicked");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			logger.log(LogStatus.ERROR,
					"Failed to click '" + element.toString() + "' </br>" + e.getStackTrace().toString());
		}
	}

	/**
	 * Web button click.
	 *
	 * @param driver the driver
	 * @param logger the logger
	 * @param element the element
	 */
	public static void webButtonDoubleClick_JS(WebDriver driver, ExtentTest logger, WebElement element) {
		try {
			if (element != null && element.isEnabled()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JavascriptExecutor js = (JavascriptExecutor) driver;js.executeScript("arguments[0].click();", element);

			}
		} catch (Exception e) {
		}
	}
	
	public static void activeElementPressKeyBoardKeys(WebDriver driver,String Value,WebElement element) throws InterruptedException {
        //Accepts input in format Keys.TAB
        
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        try{

              String keyBoardInput = Value;

              switch(keyBoardInput) {
              case "TAB" :
                    driver.switchTo().activeElement().sendKeys(Keys.TAB);
                    break;
              case "SHIFT_TAB" :
                    driver.switchTo().activeElement().sendKeys(Keys.SHIFT,Keys.TAB);
                    break;
              case "ARROW_DOWN"  :
                    driver.switchTo().activeElement().sendKeys(Keys.ARROW_DOWN);
                    break;
              case "SPACE" :
                    driver.switchTo().activeElement().sendKeys(Keys.SPACE);
                    break;
              case "PAGE_DOWN" :
                    driver.switchTo().activeElement().sendKeys(Keys.PAGE_DOWN);
                    break;
              case "ARROW_UP" :
                    driver.switchTo().activeElement().sendKeys(Keys.ARROW_UP);
                    break;
              case "PAGE_UP" :
                    driver.switchTo().activeElement().sendKeys(Keys.PAGE_UP);
                    break;
              case "ENTER" :
                    driver.switchTo().activeElement().sendKeys(Keys.ENTER);
                    break;
              case "DELETE" :
                    driver.switchTo().activeElement().sendKeys(Keys.DELETE);
                    break;
              case "BACK_SPACE" :
                    driver.switchTo().activeElement().sendKeys(Keys.BACK_SPACE);
                    break;
              default :
                    System.out.println("Invalid keyBoardInput");
              }
              //driver.switchTo().activeElement().sendKeys(Keys.SHIFT,Keys.TAB);
              Thread.sleep(2000);
     
              //Thread.sleep(3000);
        }catch(Exception e){
             
              //Thread.sleep(3000);
        }
  }

	
}
