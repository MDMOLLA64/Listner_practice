package kohls.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TakeAutoScreenshot {

	public static WebDriver driver;

	// public static int rana;
	public static int generateRandomNumber(int rana) {
		Random random = new Random();
//		int ran = (int) Math.random();
		for (int i = 0; i < 9; i++) {
			int ran = random.nextInt();
			rana = ran;
		}
		return rana;

	}

	public static String currentTime() {

		DateTimeFormatter dnt = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String LocalTime = dnt.format(now);
		return LocalTime;

	}

	public static void capturefailed(WebDriver driver, String testname) {

		// /Users/mdmolla/eclipse-workspace/TestNgwithSelenium/ScreenShotFailTest

		// /Users/mdmolla/eclipse-workspace/TestNgwithSelenium/ScreenShotFailTest

		// "/Users/mdmolla/eclipse-workspace"
		// + "/POMProjectJCP/FaliedTestMethods/"

		String time = currentTime();

		// int num = generateRandomNumber(10);
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshotFile,
					new File("/Users/mdmolla/eclipse-workspace/TestNgwithSelenium/ScreenShotFailTest/" + testname + "_"
							+ time + ".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void failedCaption(WebDriver driver, String testname) {

		String time = currentTime();

		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		File desfile = new File("/Users/mdmolla/eclipse-workspace" + "/TestNgwithSelenium/ScreenShotFailTest/"
				+ testname + "__" + time + ".png");
		try {
			FileUtils.copyFile(screenshotFile, desfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getScreenshot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots"
		// under src folder
		String destination = System.getProperty("user.dir") + "/failTestAttachToExtentReport/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

}
