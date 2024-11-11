package utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;



public class ExtentReportUtility implements ITestListener {
	public ExtentSparkReporter sparkReporter;  // UI of the report
	public ExtentReports extent;  //populate common info on the report
	public ExtentTest test; // creating test case entries in the report and update status of the test methods
	private String repName;
	
	/* 
	 * Listener method runs at the start of the test
	 * Parameter - ITestContext context
	 * Return - N/A
	 */
	public void onStart(ITestContext context) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp
		repName = "Extent-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);// specify location of the report
			
		
		sparkReporter.config().setDocumentTitle("Coursera Automation Report"); // TiTle of report
		sparkReporter.config().setReportName("Functional Testing"); // Name of the report
		sparkReporter.config().setTheme(Theme.DARK); // Theme
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application","Coursera");
		extent.setSystemInfo("Computer Name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Tester Name","Hemachandar");
		
//		String os = context.getCurrentXmlTest().getParameter("os");
//		extent.setSystemInfo("Operating System", os);
		
		String browser = context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
		extent.setSystemInfo("Groups", includedGroups.toString());
		}
					
	}

	/* 
	 * Listener method runs at the success of the test
	 * Parameter - ITestResult result
	 * Return - N/A
	 */
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName()); // create a new entry in the report
		test.log(Status.PASS, result.getName() + " is PASSED the test."); // update status p/f/s
		
	}
	
	/* 
	 * Listener method runs at the failure of the test
	 * Parameter - ITestResult result
	 * Return - N/A
	 */
	public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}			
	}
	
	/* 
	 * Listener method runs at the end of the test
	 * Parameter - ITestContext context
	 * Return - N/A
	 */
	public void onFinish(ITestContext context) {
		
		extent.flush();
	}
}
