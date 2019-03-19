package utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtility {

	public static void takeScreenShot(WebDriver wd) {
		File f = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);

		try {

			FileUtils.copyFile(f, new File(Constants.newScreenShotPath + timeStamp() + ".png"));
		}

		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public ScreenShotUtility() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static String timeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
}
