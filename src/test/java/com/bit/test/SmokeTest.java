package com.bit.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;

@Listeners(CustomListener.class)
public class SmokeTest extends BaseTest {

	HomePage homepage;

	@Test()
	public void homePageloader() {
		extentTest = extent.startTest("homePageloader");

		homepage = new HomePage(driver);
		homepage.verifyLogo();
		//logger.info("logo is Kohl's");
		homepage.veifyurl();
		//logger.info("verifyed the url");

	}

	@Test(enabled = false)
	public void functionalTest() {
		//extentTest = extent.startTest("functionalTest");

		//logger.info("monday");

	}

	@Test(enabled = false)
	public void designTest() {
		//extentTest = extent.startTest("eshopper3");

		System.out.println("sunday");

	}

	@Test(enabled = false)
	public void LigIn_001() {
		//extentTest = extent.startTest("LigIn_001");
		System.out.println("saterday");

	}

}
