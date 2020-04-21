package com.bit.test;

import java.util.ArrayList;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import data.driven.TestUtil;
import pages.HomePage;

public class LogInTest extends BaseTest {

	HomePage homepage;

	@DataProvider
	public Iterator<Object[]> getTestData() {

		ArrayList<Object[]> testData = TestUtil.getDataFromExel();
		return testData.iterator();
	}

	@Test(dataProvider = "getTestData")
	public void LogInTest001(String user, String pass) {
		extentTest = extent.startTest("LogInTest001");
		System.out.println("hello");
		homepage = new HomePage(driver);

		driver.findElement(By.id("kiosk_loginEmail")).clear();
		driver.findElement(By.id("kiosk_loginEmail")).sendKeys(user);

		driver.findElement(By.id("kiosk_loginPassword")).clear();
		driver.findElement(By.id("kiosk_loginPassword")).sendKeys(pass);
		driver.findElement(By.id("Profilelogin")).click();
		driver.findElement(By.xpath("//ul[@class='utility-nav-group']")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();

	}

}
