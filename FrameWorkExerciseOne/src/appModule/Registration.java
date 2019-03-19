package appModule;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.DemoTestPage;
import utility.Log;
import utility.WordUtility;

public class Registration {

	static Logger Log = Logger.getLogger(Registration.class.getName());

	public Registration(WebDriver wd, String firstName, String lastName) {
		try {

			if (DemoTestPage.fieldFirstName(wd) == null)
				wd.close();
			else {
				WordUtility.takeScreenShot();

				DemoTestPage.fieldFirstName(wd).sendKeys(firstName);

				Log.info("Firstname entered");

				Thread.sleep(1000);

				WordUtility.takeScreenShot();

				DemoTestPage.fieldLastName(wd).sendKeys(lastName);

				Thread.sleep(1000);

				WordUtility.takeScreenShot();

				Log.info("Lastname entered");

				DemoTestPage.buttonSubmit(wd).click();

				Log.info("Clicked on Submit button");

				WordUtility.takeScreenShot();

			}
		} catch (Exception e) {
			Log.error(e);
		}

	}

	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

}
