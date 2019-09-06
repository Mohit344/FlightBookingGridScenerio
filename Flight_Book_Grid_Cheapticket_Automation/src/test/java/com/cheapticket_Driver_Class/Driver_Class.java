package com.cheapticket_Driver_Class;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver_Class {
	/**
	 * RemoteWebDriver getDriver function is use to upload the browser.
	 * @param browser
	 * @return
	 * @throws MalformedURLException
	 */
	public static RemoteWebDriver getDriver(String browser) throws MalformedURLException // RemoteWebdriver is class use for running the grid
	{
		return new RemoteWebDriver(new URL("http://55.55.54.125:2000/wd/hub"), getBrowsersetUp(browser));
	}

	private static DesiredCapabilities getBrowsersetUp(String browserType) {
		switch (browserType) // browsertype is to choose the type of browser
		{
		case "firefox":
			System.out.println(" Ready  to  Opening OneWay in firefox driver");
			DesiredCapabilities fir= new DesiredCapabilities();
			fir.setCapability(CapabilityType.BROWSER_NAME, "firefox");
			return fir;
		case "chrome":
			System.out.println(" Ready to Opening TwoWay in chrome driver");
			DesiredCapabilities chm= new DesiredCapabilities();
			chm.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			return chm;
		case "InternetExplorer":
			System.out.println(" Ready to Opening TwoWay in internetexplorer");
			DesiredCapabilities ie= new DesiredCapabilities();
			ie.setCapability(CapabilityType.BROWSER_NAME, "internetexplorer");
			return ie;

		default:
			System.out.println(" the browser : " + browserType + " is invalid, Firefox  lauching ");
			DesiredCapabilities capfirefox= new DesiredCapabilities();
			capfirefox.setCapability(CapabilityType.BROWSER_NAME, "firefox");
			return capfirefox;

		}

	}




}
