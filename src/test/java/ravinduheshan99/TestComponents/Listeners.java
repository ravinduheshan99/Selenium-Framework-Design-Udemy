package ravinduheshan99.TestComponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import ravinduheshan99.resources.ExtentReporterNG;

public class Listeners implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	@Override
    public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
    	test.fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Invoked each time a test method is skipped.
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Invoked each time a test method fails but is within the success percentage.
    }

    @Override
    public void onStart(ITestContext context) {
        // Invoked before any test method belonging to the classes inside the <test> tag is run.
    }

    @Override
    public void onFinish(ITestContext context) {
        // Invoked after all the test methods belonging to the classes inside the <test> tag have run.
    }

	

}
