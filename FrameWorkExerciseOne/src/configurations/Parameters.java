package configurations;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import pageObjects.DemoTestPage;
import utility.Log;

public class Parameters {
	
	static Logger Log =Logger.getLogger(Parameters.class.getName());

	public static WebDriver wd = null;

	public static String browser = null;

	public static WebDriver browserLaunch(String browser) {

		String path;
		try {
			switch (browser) {
			case "Chrome": {
				path = "D:\\Ajay\\Ajay\\Selenium\\chromedriver\\chromedriver.exe";

				System.setProperty("webdriver.chrome.driver", path);

				wd = new ChromeDriver();

				break;
			}
			case "IE":

			{
				path = "D:\\Ajay\\Ajay\\Selenium\\ie\\iedriver.exe";

				System.setProperty("webdriver.ie.driver", path);

				wd = new InternetExplorerDriver();

				break;
			}
			}
			wd.manage().window().maximize();

			wd.manage().deleteAllCookies();

			wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		} catch (Exception e) {
			Log.error(e);
		}
		return wd;
	}

	public Parameters() {
		super();
		// TODO Auto-generated constructor stub
	}

}
