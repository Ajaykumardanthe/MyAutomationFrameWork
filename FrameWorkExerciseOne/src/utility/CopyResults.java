package utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.google.common.io.Files;

import testCases.Registration_TestNG_TC;

public class CopyResults {
	static Logger Log =Logger.getLogger(CopyResults.class.getName());

	public static void copyFiles()

	{
		
		try {

			File f1 = new File("D:/Ajay/Ajay/SeleniumWorkspace/FrameWorkExerciseOne/test-output/emailable-report.html");

			File f2 = new File("D:/Ajay/Ajay/SeleniumWorkspace/FrameWorkExerciseOne/test-output/index.html");

			File f3 = new File("D:\\Ajay\\Ajay\\Selenium\\FrameWorkExerciseOne\\TestResults\\NewResults\\");

			FileUtils.copyFileToDirectory(f1, f3);
			FileUtils.copyFileToDirectory(f2, f3);
		} catch (Exception e) {
			Log.error(e);
		}
	}

	public CopyResults() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

}
