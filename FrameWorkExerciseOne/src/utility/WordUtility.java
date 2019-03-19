package utility;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import testCases.Registration_TestNG_TC;

public class WordUtility {

	static Logger Log =Logger.getLogger(WordUtility.class.getName());
	public static XWPFDocument docx;
	public static XWPFRun run;
	public static File f;
	public static FileOutputStream out;
	public static String dirPath;
	public static int k;
	public static ExcelUtility eu2;
	public static String[] testCaseName;

	public static void createDoc(ExcelUtility e) {
		try {
			k = 1;
			dirPath = Constants.newScreenShotPath + e.getCellData(1, 0);
			f = new File(dirPath);
			if (!f.exists())
				f.mkdir();
			docx = new XWPFDocument();
			run = docx.createParagraph().createRun();
			out = new FileOutputStream(dirPath + "//" + e.getCellData(1, 0) + ".docx");
		} catch (Exception e1) {
			Log.error(e1);
		}
	}

	public static void takeScreenShot() {
		try {
			captureScreenShot(run, out, dirPath);
		} catch (Exception e1) {
			Log.error(e1);
		}
	}

	public WordUtility() {
	}

	public static void pasteScreenShot() {
		try {
			docx.write(out);
			out.flush();
			out.close();
			docx.close();
		} catch (Exception e1) {
			Log.error(e1);
		}
	}

	public static void captureScreenShot(XWPFRun run, FileOutputStream out, String dirPath) {

		try {
			if (k == 1)
				eu2 = Registration_TestNG_TC.getTestCaseObj();
			String screenshot_name = eu2.getCellData(k, 2) + ".png";
			BufferedImage image = new Robot()
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			File fi = new File(dirPath + "//" + "Screenshots//");
			fi.mkdir();
			File file = new File(dirPath + "//" + "Screenshots//" + screenshot_name);
			run.addBreak();
			run.setText(eu2.getCellData(k, 2) + ": " + eu2.getCellData(k, 3));
			HTMLReport.addTestCaseSummaryReport(eu2.getCellData(k, 0), eu2.getCellData(k, 2), "Registartion",
					eu2.getCellData(k, 3), eu2.getCellData(k, 5), eu2.getCellData(k, 6), "PASS");
			run.addBreak();
			run.setFontSize(10);
			ImageIO.write(image, "png", file);
			InputStream pic = new FileInputStream(file);
			run.addBreak();
			run.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG, screenshot_name, Units.toEMU(410), Units.toEMU(280));
			pic.close();
			k++;
		} catch (Exception e1) {
			Log.error(e1);
		}

	}

}
