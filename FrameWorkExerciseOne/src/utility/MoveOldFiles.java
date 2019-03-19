package utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

public class MoveOldFiles {
	
	static Logger Log =Logger.getLogger(MoveOldFiles.class.getName());

	public static void moveFilesToOtherFolder() {

		try {
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh-mm-ss a").format(Calendar.getInstance().getTime());
			File src = new File(Constants.newScreenShotPath);
			File dest = new File(Constants.oldScreenShotPath + timeStamp);
			dest.mkdir();
			FileUtils.copyDirectory(src, dest);
			String[] entries = src.list();
			for (String s : entries) {
				File currentFile = new File(src.getPath(), s);
				currentFile.delete();
			}
		} catch (Exception e) {
			Log.error(e);
		}
	}

}
