/**
 * 
 */
package com.webElementPkg;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// TODO: Auto-generated Javadoc
/**
 * The Class WebFrame.
 *
 * @author
 */
public class WebFrame {

	/**
	 * Gets the all frames on page.
	 *
	 * @param driver
	 *            the driver
	 * @return the all frames on page
	 */
	public static List<WebElement> getAllFramesOnPage(WebDriver driver) {

		List<WebElement> iFrameList = new ArrayList<WebElement>();

		iFrameList = driver.findElements(By.tagName("iframe"));

		System.out.println("Total No. of Frames=" + iFrameList.size());
		return iFrameList;
	}

	/**
	 * Switch on frame by name.
	 *
	 * @param driver
	 *            the driver
	 * @param frameName
	 *            the frame name
	 * @throws InterruptedException
	 */
	/*
	 * public static void switchOnFrameByName(WebDriver driver, String frameName) {
	 * List<WebElement> iFrameList = getAllFramesOnPage(driver); for(WebElement
	 * iframe : iFrameList){ System.out.println("Switch to Parent Frame"+iframe);
	 * driver.switchTo().frame(iframe); List<WebElement> iChldFrameList =
	 * getAllFramesOnPage(driver); for(WebElement iChldFrame:iChldFrameList){
	 * if(iChldFrame.getAttribute("id")==frameName){
	 * driver.switchTo().frame(frameName);
	 * System.out.println("Switch To Frame="+frameName); } } }
	 * 
	 * /* if (iFrameList != null) { driver.switchTo().frame(frameName); }
	 * 
	 * }
	 */
	public static void switchOnMultipleFramesByXpath(WebDriver driver, String... strings) {
		String[] frames = strings;
		for (String iframe : frames) {

			try {
				WebElement Frame = driver.findElement(By.xpath(iframe));
				driver.switchTo().frame(Frame);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Switch To Frame=" + iframe);
		}

	}

	/**
	 * Switch on frame by xpath.
	 *
	 * @param driver
	 *            the driver
	 * @param frameId
	 *            the frame id
	 */
	public static void switchOnFrameByXpath(WebDriver driver, String frameXpath) {
		List<WebElement> iFrameList = getAllFramesOnPage(driver);

		if (iFrameList != null) {
			// driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[@src='https://tssstrpms501.corp.trelleborg.com:12001/teamworks/process.lsw?zWorkflowState=1&zTaskId=4581&zResetContext=true&coachDebugTrace=none']")));
			WebElement Frame = driver.findElement(By.xpath(frameXpath));
			try {
				driver.switchTo().frame(Frame);
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	/**
	 * Switch on frame by id.
	 *
	 * @param driver
	 *            the driver
	 * @param frameId
	 *            the frame id
	 */
	public static void switchOnFrameById(WebDriver driver, String frameId) {
		List<WebElement> iFrameList = getAllFramesOnPage(driver);

		if (iFrameList != null) {
			driver.switchTo().frame(frameId);
		}
	}

	/**
	 * Switch to main page from iFrame.
	 *
	 * @param driver
	 *            the driver
	 */
	public static void switchToMainPageFromIFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the iFrame count.
	 *
	 * @param driver
	 *            the driver
	 * @return the i frame count
	 */
	public static int getIFrameCount(WebDriver driver) {
		return getAllFramesOnPage(driver).size();
	}
}
