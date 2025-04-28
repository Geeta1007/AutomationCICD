package FrameworkDesign.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import FrameworkDesign.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentTest test;
	ExtentReports reports = ExtentReporterNG.getReportObject();   // made the method as static so here we no need to create object for the class
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();  //thread safe
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = reports.createTest(result.getMethod().getMethodName());	// test related data will get stored in result. error messages also.
		extentTest.set(test); //unique thread id(ErrorValidationTest)->test
		}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());  // it will show the error message as well
		String failpath = null;
		
		try {
			driver =  (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			failpath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(failpath, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		reports.flush();
	}

	
}
