package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import org.testng.ITestResult;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import appModule.Registration;
import configurations.Parameters;
import utility.Constants;
import utility.ExcelUtility;
import utility.HTMLReport;
import utility.Log;
import utility.MoveOldFiles;
import utility.WordUtility;

public class Registration_TestNG_TC {
	
	static Logger Log =Logger.getLogger(Registration_TestNG_TC.class.getName());

	public static WebDriver wd = null;

	public static String firstName = null;

	public static String lastName = null;

	public static ExcelUtility eu, eu1;

	public static String filePath, result, tid, tname;

	public static int c = 1, m = 1;

	ExtentReports report;

	ExtentTest testReport;

	//static Logger Log =Logger.getLogger(Registration_TestNG_TC.class);
	
	@Test(dataProvider = "excelData")
	public void registration(String testCaseId, String testCase) {

		try {

			HTMLReport.setTestCaseSummaryHTML(testCaseId, testCase);

			filePath = Constants.testDataFilePath + testCaseId + ".xlsx";

			eu1 = new ExcelUtility(filePath, 0);

			WordUtility.createDoc(eu1);

			Registration r = new Registration(wd, eu1.getCellData(Constants.row_FirstName, 4), eu1.getCellData(Constants.row_LastName, 4));

			Log.info("Result updated");

			WordUtility.pasteScreenShot();

			tid = eu1.getCellData(1, 0);
			tname = eu1.getCellData(1, 1);
		} catch (Exception e) {
			Log.error(e);
		}

	}

	public static ExcelUtility getTestCaseObj() {
		return eu1;
	}

	public static ExcelUtility getTestScenObj() {
		try {
			eu = new ExcelUtility(Constants.testScenarioFilePath, 0);
		} catch (Exception e) {
			Log.error(e);
		}
		return eu;
	}

	@BeforeTest
	public void beforeTest() {
		
			PropertyConfigurator.configure("./log4j.properties");
			MoveOldFiles.moveFilesToOtherFolder();
			HTMLReport.setSummaryHTML();
		
	}

	public Registration_TestNG_TC() {

	}

	@BeforeMethod
	public void beforeMethod() {
		try {

			
			
			File f1 = new File(Constants.propertiedFilePath);

			FileInputStream fi = new FileInputStream(f1);

			Properties p = new Properties();

			p.load(fi);

			wd = Parameters.browserLaunch(p.getProperty("browser"));
			Log.info("Launching " + p.getProperty("browser") + " browser");

			wd.get(Constants.URL);

			Log.info("Opening web application");
		} catch (Exception e) {
			Log.error(e);
		}

	}

	@DataProvider
	public Object[][] excelData() {

		String data[][] = null;
		try {

			int totlaRowCount = getTestScenObj().getlastRowNum();

			data = new String[totlaRowCount][2];

			for (int i = 0; i < totlaRowCount; i++) {
				for (int j = 1; j <= 2; j++) {
					data[i][j - 1] = getTestScenObj().getCellData(i + 1, j - 1);
				}
			}
		} catch (Exception e) {
			Log.error(e);
		}
		return data;

	}

	@AfterMethod
	public void afterMethod(ITestResult r) throws IOException, InterruptedException {
		try {

			wd.close();

			if (r.getStatus() == ITestResult.FAILURE)
				result = "FAIL";
			else
				result = "PASS";
			// getTestScenObj().setCellData(m, 4, result);
			m++;
			HTMLReport.writeTestCaseSummaryReport();
			HTMLReport.addSummaryReport(tid, tname, result);
		} catch (Exception e) {
			Log.error(e);
		}

	}

	@AfterTest
	public void afterTest() throws IOException {
		try {
			HTMLReport.writeSummaryReport();
		} catch (Exception e) {
			Log.error(e);
		}
	}

}
