package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import kohls.utility.Helper;

public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='middle-menu-title']")
	WebElement mainManu;
	@FindBy(id = "logo")
	WebElement logo;

	public void verifyLogo() {
		Helper.verifyElement(logo);

	}

	public void veifyurl() {
		String Actualurl = driver.getCurrentUrl();
		Assert.assertEquals(Actualurl, "https://www.kohls.com/");

	}

	public void clickOnShopByDepertment() {
		Helper.clickOnElement(mainManu);

	}

}
