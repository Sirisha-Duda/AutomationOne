package utilities;

import java.awt.Desktop;
import java.io.File;
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


public class ExtentReportManager implements ITestListener{
	String repName;
	public ExtentSparkReporter extentSparkReporter;
	public ExtentReports extent;
	
	public void onStart(ITestContext testContext) {
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-"+timestamp+".html";
		
		extentSparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);
		extentSparkReporter.config().setDocumentTitle("Opencart Automation Report");
		extentSparkReporter.config().setReportName("Open cart functional testing");
		extentSparkReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(extentSparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name",System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os= testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGrps=testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGrps.isEmpty())
			extent.setSystemInfo("Groups", includedGrps.toString());
	}
	public ExtentTest test;
	public void onTestSuccess(ITestResult res) {
		test = extent.createTest(res.getTestClass().getName());
		test.assignCategory(res.getMethod().getGroups());
		test.log(Status.PASS,res.getName()+ "got successfully executed");
		try {
			String imgPath=new BaseClass().captureScreen(res.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestFailure(ITestResult res) {
		test=extent.createTest(res.getTestClass().getName());
		test.assignCategory(res.getMethod().getGroups());
		
		test.log(Status.FAIL,res.getName()+"got failed");
		test.log(Status.INFO,res.getThrowable().getMessage() );
		try {
			String imgPath=new BaseClass().captureScreen(res.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult res) {
		test=extent.createTest(res.getTestClass().getName());
		test.assignCategory(res.getMethod().getGroups());
		test.log(Status.SKIP, res.getName()+" got skipped");
		test.log(Status.INFO, res.getThrowable().getMessage());
	}
	
	public void onFinish(ITestContext testcon) {
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		/*try {
			String imgPath=new BaseClass().captureScreen(testcon.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}*/
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
