package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import pageFactory.PFactory;
import utility.Log;
import utility.WordUtility;

public class DemoTestPage {
	public static PFactory pf;
	static Logger Log =Logger.getLogger(DemoTestPage.class.getName());

	public static void initPageFactoryClass(WebDriver wd) {
		pf = PageFactory.initElements(wd, PFactory.class);
		Log.info("PageFactory class initiated");
	}

	public DemoTestPage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static WebElement fieldFirstName(WebDriver wd) {
		try {
			initPageFactoryClass(wd);
			Log.info("firstname webelement found");
		} catch (Exception e) {
			Log.error(e);
		}
		return pf.firstName;
	}

	public static WebElement fieldLastName(WebDriver wd) {
		try {
			initPageFactoryClass(wd);
			Log.info("lastname webelement found");
		} catch (Exception e) {
			Log.error(e);
		}
		return pf.lastName;
	}

	public static WebElement buttonSubmit(WebDriver wd) {
		try {
			initPageFactoryClass(wd);
			Log.info("submit button webelement found");
		} catch (Exception e) {
			Log.error(e);
		}
		return pf.submitButton;
	}
}
