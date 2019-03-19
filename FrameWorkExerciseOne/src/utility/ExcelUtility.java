package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import testCases.Registration_TestNG_TC;

public class ExcelUtility {
	
	static Logger Log =Logger.getLogger(ExcelUtility.class.getName());
	XSSFWorkbook xw;
	XSSFSheet xs;
	XSSFCell xc;
	File f;
	public static String[] noOfTestCases;

	public ExcelUtility(String path, int sheetIndex) {
		try {
			f = new File(path);
			FileInputStream fi = new FileInputStream(f);
			xw = new XSSFWorkbook(fi);
			xs = xw.getSheetAt(sheetIndex);
		} catch (Exception e) {
			Log.error(e);
		}
	}

	public String[] setExcelFile(int sheetIndex, String path) {
		try {
			if (path.contains("TestData")) {
				int count = xs.getLastRowNum();
				noOfTestCases = new String[count];
				for (int i = 1; i <= count; i++)
					noOfTestCases[i - 1] = getCellData(i, 0);
			}
		} catch (Exception e) {
			Log.error(e);
		}
		return noOfTestCases;

	}

	public String getCellData(int rowNo, int colNo) {

		String cellData = null;
		try {

			xc = xs.getRow(rowNo).getCell(colNo);

			cellData = xc.getStringCellValue();
		} catch (Exception e) {
			Log.error(e);
		}

		return cellData;

	}

	public void setCellData(int rowNo, int colNo, String string) throws IOException {

		try {

			xc = xs.getRow(rowNo).getCell(colNo);

			xc.setCellValue(string);

			FileOutputStream fo = new FileOutputStream(Registration_TestNG_TC.filePath);

			xw.write(fo);

			fo.flush();

			fo.close();
		} catch (Exception e) {
			Log.error(e);
		}

	}

	public int getlastRowNum() {
		return xs.getLastRowNum();
	}

}
