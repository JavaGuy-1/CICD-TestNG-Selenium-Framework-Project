package myporto.testcomponent;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import myporto.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentThreadLocal = new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentThreadLocal.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		extentThreadLocal.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		extentThreadLocal.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentThreadLocal.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
		//attach screenshot
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
	
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
	
	
	
	
	

}
