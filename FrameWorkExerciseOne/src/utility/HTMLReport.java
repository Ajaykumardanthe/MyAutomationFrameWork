package utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class HTMLReport {
	static Logger Log =Logger.getLogger(HTMLReport.class.getName());
	public static BufferedWriter bf,bf1;
	public static int counter = 1,c=1;
	public static String timeStamp = new SimpleDateFormat("E,dd MMM yyyy hh:mm:ss a")
			.format(Calendar.getInstance().getTime());

	public static String 	 style = "<html>"
			+ "<head>"
			+ "<style>"
			+ " table,th,td"
			+ "{"
			+ "border:2px solid #17202A; "
			+ " border-collapse:collapse;"
			+ "}"
			+"table#t01 th {"
			+ "  background-color: #9AC9BE;color:#C70039  " + "}" + "th, td {border: 2px solid #17202A;}td#t02  {  padding: 5px;text-align:left;background-color: #eee;height: 2px;align='left';valign='middle';"
			+"}"
			+ "</style></head>";
	public static String tableHeader = "<body bgcolor='#17202A'>";

	public static String summaryBody1 = "<h2><center><font color='#C1BEBD'>Test Execution Summary Result</font></center></h2>"
			+ "<table id='t01' width='100%'>"
			+ "<tr><th colspan='2'>Module Name</th><th colspan='2'><font color='#17202A'>Login</font></th></tr><tr><th>Executed By</th><th><font color='#17202A'>Ajay Kumar</font></th><th>Executed Date&Time</th><th><font color='#17202A'>";
	public static String summaryBody2 =  "</font></th></tr>" + "  <tr>" + "   <th>S.No</th>" + "  <th>Test case ID</th> "
			+ " <th>Test case Description</th>" + " <th>Status</th>" + "</tr>" + "<tr>";
	public static String testCaseSummaryBody1 = "<h2><center><font color='#C1BEBD'>TestCase Execution Summary Result</font></center></h2>"
			+ "<table id='t01'>" + "<tr><th>TestCase ID</th><th><font color='#17202A'>";

	public static String testCaseSummaryBody2 = "</font></th><th colspan='2'>Module Name</th><th colspan='2'><font color='#17202A'>Login</font></th></tr><tr><th>Executed By</th><th><font color='#17202A'>Ajay Kumar</font></th><th colspan='2'>Executed Date&Time</th><th colspan='2'><font color='#17202A'>";
	public static String testCaseSummaryBody3 = "</font></th></tr><tr><th>TestCase Description</th><th align='left' colspan='5'><font color='#17202A'>";

	public static String testCaseSummaryBody4 = "</font></th></tr><tr>" + "   <th bgcolor='#DCB361'>S.No</th>" + "  <th bgcolor='#DCB361'>Screen Name</th> "
			+ " <th bgcolor='#DCB361'>Test Step Description</th>"+ " <th bgcolor='#DCB361'>Expected Result</th>"  + " <th bgcolor='#DCB361'>Actual Result</th>" + " <th bgcolor='#DCB361'>Status</th>" + "</tr>" + "<tr>";

	public static void addSummaryReport(String testCaseID, String testCaseName, String Result){

		String color;
		try
		{

		if (Result.equals("PASS"))
			color = "#1E8449";
		else
			color = "#C0392B";

		bf.write("  <td id='t02' width='5%'><center>" + counter + "</center></td>"
				+ "  <td id='t02' width='8%' valign='middle'><center><a target='_blank' href='"+Constants.newScreenShotPath+"//"+testCaseID+"//"+testCaseID+".html"+"'><h4>" + testCaseID + "<h4></a></center></td>"
				+ "  <td id='t02' width='70%'>" + testCaseName + "</td>" + "  <td id='t02' width='17%'><h4><font color='"
				+ color + "'><center>" + Result + "</center></font></h4></td>" + "</tr>");

		counter++;
		}
		catch(Exception e)
		{
			Log.error(e);
		}

	}

	public static void writeSummaryReport(){
		try
		{
		bf.write("</table>" + "</body>" + "</html>");
		bf.close();
		}
		catch(Exception e)
		{
			Log.error(e);
		}
	}

	public static void setSummaryHTML(){
		try
		{
		File f = new File(Constants.newScreenShotPath + "Summary.html");

		FileWriter fw = new FileWriter(f);

		bf = new BufferedWriter(fw);

		bf.write(style + tableHeader + summaryBody1+timeStamp+summaryBody2);
		}
		catch(Exception e)
		{
			Log.error(e);
		}

	}
	public static void setTestCaseSummaryHTML(String testId,String testCaseName ){
		
		try
		{
		File f1 = new File(Constants.newScreenShotPath+testId);
		
		if(!f1.exists())
			f1.mkdir();
		
		File f2 = new File(Constants.newScreenShotPath+testId+"//"+testId + ".html");

		FileWriter fw = new FileWriter(f2);

		bf1= new BufferedWriter(fw);

		bf1.write(style + tableHeader +testCaseSummaryBody1+testId+testCaseSummaryBody2+timeStamp+testCaseSummaryBody3+testCaseName+testCaseSummaryBody4);

		}
		catch(Exception e)
		{
			Log.error(e);
		}
	}
	public static void writeTestCaseSummaryReport()
{
		try
		{
		bf1.write("</table>" + "</body>" + "</html>");
		bf1.close();
		}
		catch(Exception e)
		{
			Log.error(e);
		}
	}
	public static void addTestCaseSummaryReport(String testCaseId,String testStepID,String screenName, String testStepName,String expectedResults,String actualResults, String Result) throws IOException {

		String color;
		try
		{

		if (Result.equals("PASS"))
			color = "#1E8449";
		else
			color = "#C0392B";

		bf1.write("  <td id='t02' width='4%'><center>" + c + "</center></td>"
				+ "  <td id='t02' width='10%'><center>" + screenName + "</center></td>"
				+ "  <td id='t02' width='30%'>" + testStepName + "</td>" + "  <td align='left' id='t02' width='20%'>" + expectedResults + "</td>"+ "  <td align='left' id='t02' width='21%'>" + actualResults + "</td>"+ "  <td id='t02' valign='middle' width='5%'><h4><a target='_blank' href='"+Constants.newScreenShotPath+testCaseId+"//Screenshots//"+testStepID+".png'"+"><font color='"
				+ color + "'><center>" + Result + "</center></font></a></h4></td>" + "</tr>");

		c++;
		}
		catch(Exception e)
		{
			Log.error(e);
		}

	}
	static Logger logger =Logger.getLogger(HTMLReport.class);
	 public static void main(String args[]) throws IOException {
	 HTMLReport h = new HTMLReport();
	 BasicConfigurator.configure();
//	 h.setTestCaseSummaryHTML("TC01", "testCaseName");
//	 h.addTestCaseSummaryReport("TC01", "TS01", "testStepName", "PASS");
//	 h.writeTestCaseSummaryReport();
	 h.meth1();


	 }
	 public void meth1()
	 {
		 logger.info("Hi");
		 logger.info("Hiiiii");
	 }
//
//public void meth() throws IOException
//{
//	File f = new File(Constants.newScreenShotPath + "test.html");
//
//	FileWriter fw = new FileWriter(f);
//
//	BufferedWriter bf4 = new BufferedWriter(fw);
//	
//	bf4.write("<html>"
//			+ "<head>"
//			+ "<style>"
//			+ " table,th,td"
//			+ "{"
//			+ "border:2px solid black; "
//			+ " border-collapse:collapse;"
//			+ "}"
//			+ "</style></head>"
//			+ "<body>"
//			+ "<table width='100%' >"
//			+ "<tr><th>H1</th> <th>H2</th></tr>"
//			+ "<tr><td>R1</td><td>R2</td></tr>"
//			+ "<tr><td>R3</td><td>R4</td></tr>"
//			+ "</table>"
//			+ "</body>"
//			+ "</html");
//	bf4.close();
//}
}
