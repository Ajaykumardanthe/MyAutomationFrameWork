package utility;
import org.apache.log4j.*;

public class Log{

	static Logger logger =Logger.getLogger(Log.class.getName());

	public static void startTestCase(String testCaseName) {
		if(logger.isDebugEnabled())
		logger.info("---" + testCaseName + "---");
	}

	public static void endTestCase() {
		if(logger.isDebugEnabled())
			logger.info("---" + "END" + "---");

	}
}
