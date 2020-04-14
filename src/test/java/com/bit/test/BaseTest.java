package com.bit.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

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
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import kohls.utility.ReadPropFile;
import kohls.utility.TakeAutoScreenshot;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class BaseTest {

	public static WebDriver driver;
	Properties prop;
	public ExtentReports extent;
	public ExtentTest extentTest;
	public static Logger log;

	@BeforeTest
	public void setExtent() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Mds-MacBook-Pro.home");
		extent.addSystemInfo("User Name", "mdmolla");
		extent.addSystemInfo("Environment", "QA");

	}

	@Parameters("browser") // String browser
	@BeforeClass
	@BeforeMethod
	public void setUp() throws IOException {

		log = Logger.getLogger(BaseTest.class.getName());
		DOMConfigurator.configure("log4j.xml");

		prop = ReadPropFile.ReadFile("/Users/mdmolla/eclipse-workspace/TestNgwithSelenium/conFig.properties");
		String browser = prop.getProperty("browser");
		// String browser =System.getProperty("browser");
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/Users/mdmolla/Downloads/chromedriver");
			driver = new ChromeDriver();
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
		// driver.manage().window().maximize();

	}

	//@AfterMethod
	public void teaDown(ITestResult result) throws InterruptedException, IOException {
		switch (result.getStatus()) {
		case ITestResult.FAILURE: {
			TakeAutoScreenshot.getScreenshot(driver, result.getMethod().getMethodName());
		}
		case ITestResult.SKIP: {
			String SkippedMethods = result.getMethod().getMethodName();
			log.info(SkippedMethods);
		}
		case ITestResult.SUCCESS: {
			String Successmethods = result.getMethod().getMethodName();
			log.info(Successmethods);

		}

		}

	}

	@AfterClass
	public void afterClass() throws InterruptedException {

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
