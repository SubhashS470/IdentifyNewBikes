package ExtentReportManager;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.TC_001_HondaBrands;
import testCases.TC_002_AllBikesValidation;
import testCases.TC_003_UpcomingBikes;
import testCases.TC_004_InValidLoginCheckingDetails;
import testCases.TC_005_CarsInChennai;

public class ExtenttReportss implements ITestListener
{
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	

	public void onStart(ITestContext context)
	{
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String myReport="Test-Report-"+timestamp+".html";
		sparkreporter =new ExtentSparkReporter(".\\Reporter\\"+myReport);
		//sparkreporter=new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setReportName("Functional Testing");
		sparkreporter.config().setTheme(Theme.STANDARD);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkreporter);
		extent.setSystemInfo("Computer Name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("Te]ster Name","Subhash S");
		extent.setSystemInfo("OS","Windows10");
		extent.setSystemInfo("Browser name","Chrome,Firefox,Edge");
	}
	
	public void onTestStart(ITestResult result)
	{ 
		System.out.println("On Test Started.....");
	}
	public void onTestSuccess(ITestResult result)
	{
		String path;
		      if(result.getName().equals("SelectingAllBikes")) {
			  path=TC_001_HondaBrands.path;
			  test=extent.createTest(result.getName()).addScreenCaptureFromPath( path,result.getName());
			  }
			  else if(result.getName().equals("HondaUnder4Lakhs")) 
			  {
			  path=TC_002_AllBikesValidation.path;
			  test=extent.createTest(result.getName()).addScreenCaptureFromPath( path,result.getName()); 
			  }
			  else if(result.getName().equals("HondaManufacturer")) 
			  {
			  path=TC_003_UpcomingBikes.path;
			  test=extent.createTest(result.getName()).addScreenCaptureFromPath( path,result.getName());			  
			  } 
			  else if(result.getName().equals("InvalidLogin")) 
			  {
			  path=TC_004_InValidLoginCheckingDetails.path;
			  test=extent.createTest(result.getName()).addScreenCaptureFromPath( path,result.getName());			  
			  } 
			  else if(result.getName().equals("usedcars")) 
			  {
			  path=TC_005_CarsInChennai.path;
			  test=extent.createTest(result.getName()).addScreenCaptureFromPath( path,result.getName());			  
			  }
		test=extent.createTest(result.getName());
		test.log(Status.PASS, "Test case Passed is:"+result.getName());
	}
	
	public void onTestFailure(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is:"+result.getName());
		test.log(Status.FAIL, "Test casec FAILED CAUSE is:"+result.getThrowable());

	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is:"+result.getName());
		
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		System.out.println("onTestFailedButWithinSuccessPercentage...........");
	}
	
	public void onFinish(ITestContext context)
	{
		extent.flush();
	}

}
