// one_way_flightBooking class 
package com.cheapticket.grid_flightbook;

import java.io.FileInputStream;

import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.cheapticket_Driver_Class.Driver_Class;
import com.flightbooking_grid_Xlsx_Reader.Cheap_Ticket_XlsxReader;

public class FlightBookingForOneWay {
	Logger logge;

	public static RemoteWebDriver driver;
	Properties locate = new Properties();

	@BeforeClass
	@Parameters("browser")
	/**
	 * @param browser
	 * @throws IOException
	 */
	public void filesetupReady(String browser) throws IOException
	{

		FileInputStream inputdata = new FileInputStream("./src/test/resources/Locators/Locators.properties");

		locate.load(inputdata);
		driver=Driver_Class.getDriver(browser);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	/**
	 * @param drive,xpath
	 * @param arr
	 * @throws InterruptedException
	 */
	@Test(priority=2,dataProvider="XcelDataProvider",dataProviderClass=Cheap_Ticket_XlsxReader.class)
	public void readytorun(String[] arr) throws InterruptedException
	{
		logge = Logger.getLogger(FlightBookingForOneWay.class);// log4j implemented
		logge.info("Opening the homepage");
		driver.get(locate.getProperty("url"));
		driver.findElement(By.xpath(locate.getProperty("domestic"))).click();
		driver.findElement(By.xpath(locate.getProperty("oneway"))).click();
		WebElement from = waitAndGetElement(30, driver,"(//input[@class='search'])[1]");// fluent wait is used 
		from.sendKeys(arr[0]);
		Thread.sleep(1000);
		WebElement to = waitAndGetElement(30, driver,"(//input[@class='search'])[2]");
		to.sendKeys(arr[1]);
		Thread.sleep(1000);
		driver.findElement(By.xpath(locate.getProperty("depart"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(locate.getProperty("departdate"))).click();
		Thread.sleep(1000);
		Thread.sleep(1000);

		driver.findElement(By.xpath(locate.getProperty("class"))).click();
		driver.findElement(By.xpath(locate.getProperty("submit"))).click();
		Thread.sleep(3000);

		logge=Logger.getLogger(FlightBookingForOneWay.class);  // log4j implementation for storing the result 
		logge.info("  passing through the oneway_flightbooking travellersDetail ");
		WebElement book=waitAndGetElement(30,driver,"(//button[text()='BOOK'])[1]");
		book.click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(locate.getProperty("email"))).sendKeys(arr[2]);
		String phoneno=arr[3].replace(".", "").replace("E9", "");

		driver.findElement(By.xpath(locate.getProperty("mobile"))).sendKeys(phoneno);
		driver.findElement(By.xpath(locate.getProperty("continue"))).click();
		driver.findElement(By.xpath(locate.getProperty("dropdown1"))).click();
		driver.findElement(By.xpath(locate.getProperty("Mr"))).click();
		driver.findElement(By.xpath(locate.getProperty("firstname1"))).sendKeys(arr[4]);
		driver.findElement(By.xpath(locate.getProperty("lastname1"))).sendKeys(arr[5]);

		driver.findElement(By.xpath(locate.getProperty("submitlast"))).click();


	}
	/**
	 * waitAndGetElement() method is used to implement the fluentwait. 
	 * @param seconds
	 * @param driver
	 * @param xpath
	 * @return
	 */
	public WebElement waitAndGetElement(long seconds,WebDriver driver,final String xpath) {
		FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(seconds))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);

		WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver dvr) {
				return dvr.findElement(By.xpath(xpath));
			}
		});
		return element;
	}

	/**
	 * tearDown() function is created to close the browser window  
	 */
	@AfterClass
	public void quitDown()
	{
		logge = Logger.getLogger(FlightBookingForOneWay.class);
		logge.info("Passed Successfully");
		driver.quit();// close the browser window 
	}
}





