package kohls.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Helper {

	public static void mouseOver(WebDriver driver, WebElement target) {
		Actions a = new Actions(driver);
		a.moveToElement(target).build().perform();
	}

	public static void clickOnElement(WebElement element) {
		waitForElement(element).click();
		// element.click();

	}

	public static boolean verifyElement(WebElement element) {
		boolean result = waitForElement(element).isDisplayed();
		return result;
	}

	public static WebElement waitForElement(WebElement element) {
		ExpectedConditions.invisibilityOf(element);
		return element;
	}

	public static void selectElement(WebElement element, String text) {
		new Select(element).selectByVisibleText(text);

	}

}
