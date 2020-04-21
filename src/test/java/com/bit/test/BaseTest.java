package com.bit.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import kohls.utility.ReadPropFile;
import kohls.utility.TakeAutoScreenshot;
import pages.HomePage;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseTest {

	public static WebDriver driver;
//public static Logger logger;
	Properties prop;

	public ExtentReports extent;
	public ExtentTest extentTest;

	// @BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/extentReport.html", true);
		// extent = new ExtentReports(System.getProperty("user.dir") +
		// "/test-output/AutomationReport.html", true); //
		extent.addSystemInfo("Host Name", "Mds-MacBook-Pro.home");
		extent.addSystemInfo("User Name", "mdmolla");
		extent.addSystemInfo("Environment", "QA");
	}

	@BeforeMethod
	public void setUp() {

//		logger = Logger.getLogger(BaseTest.class);
//		DOMConfigurator.configure("log4j.xml");

		try {
			prop = ReadPropFile.ReadFile("/Users/mdmolla/eclipse-workspace/TestNgwithSelenium/conFig.properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// logger.info("Property file reading");

		String browser = prop.getProperty("browser");
		// String browser =System.getProperty("browser");
		// logger.info("got the browser name from Property file ");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/mdmolla/Downloads/chromedriver");
			driver = new ChromeDriver();
			// logger.info(browser + " browser is open");
		} else if (browser.equals("FireFox")) {
			System.setProperty("webdriver.geko.driver", "");
			driver = new FirefoxDriver();

		} else if (browser.equals("IE")) {
			System.setProperty("webdriver.InternetExplorar.driver", "");
			driver = new InternetExplorerDriver();

		} else if (browser.equals("Edge")) {
			System.setProperty("webdriver.Edge.driver", "");
			driver = new EdgeDriver();

		} else if (browser.equals("Opera")) {
			System.setProperty("webdriver.Opera.driver", "");
			driver = new OperaDriver();

		} else if (browser.equals("safari")) {
			System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
			driver = new SafariDriver();
		}

		driver.get(prop.getProperty("url"));
		// logger.info("landed into HomePage");
		// driver.manage().window().maximize();

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws InterruptedException, IOException {
		switch (result.getStatus()) {
		case ITestResult.FAILURE: {

		
			String screenshotPath = TakeAutoScreenshot.getScreenshot(driver, result.getMethod().getMethodName());

			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath));
			TakeAutoScreenshot.getScreenshot(driver, result.getMethod().getMethodName());
		}
		case ITestResult.SKIP: {
			
			extentTest.log(LogStatus.SKIP, result.getMethod().getMethodName());
			//String SkippedMethods = result.getMethod().getMethodName();

		}
		case ITestResult.SUCCESS: {
	
			String Successmethods = result.getMethod().getMethodName();

		}

			extent.endTest(extentTest);

			Thread.sleep(5000);
			driver.quit();

		}
	}

	@AfterTest
	public void endReport() {
		extent.flush();
		extent.close();
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {

	}

}
