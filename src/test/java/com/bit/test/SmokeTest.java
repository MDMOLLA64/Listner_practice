package com.bit.test;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;

@Listeners(CustomListener.class)
public class SmokeTest extends BaseTest {

	HomePage homepage;

	@Test
	public void homePageloader() {

		homepage = new HomePage(driver);
		homepage.verifyLogo();
		log.info("logo is Kohl's");
		homepage.veifyurl();
		log.info("verifyed ");

	}

	@Test()
	public void eshopper2() {
		homepage.clickOnShopByDepertment();
		log.info("heloo");

	}

	@Test()
	public void eshopper3() {
		
		System.out.println("hello001");

	}

}
