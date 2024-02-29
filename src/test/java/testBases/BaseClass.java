package testBases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
//import ExtentReportManager.ExtenttReportss;

@Listeners(ExtentReportManager.ExtenttReportss.class)
public class BaseClass {
	
	
	public static WebDriver driver;
	public static Logger logger;//for logging
	public ResourceBundle rb;//to read config.properties file
	Properties p;
	@BeforeClass
	public void setup() throws IOException
	{
		ChromeOptions options=new ChromeOptions();
		options.setExperimentalOption("excludeSwitches",new String[] {"enable-automation"});
		//rb=ResourceBundle.getBundle("config");
		logger=LogManager.getLogger(this.getClass());//for logger
		//driver=new ChromeDriver();//...
		//driver.get(rb.getString("WebsiteURL"));//get URL from config.properties file
		FileReader file = new FileReader("C:\\Users\\2304153\\eclipse-workspace\\IdentifyNewBikes\\src\\test\\resources\\config.properties");
		p = new Properties();
		p.load(file);
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabalities = new DesiredCapabilities();
			//os
			if(p.getProperty("os").equalsIgnoreCase("windows")) {
				capabalities.setPlatform(Platform.WIN11);
			}
			else if (p.getProperty("os").equalsIgnoreCase("mac")) {
				capabalities.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("no matching os .....");
				return;
			}
			//browser
			if(p.getProperty("browser").equalsIgnoreCase("chrome")) {
				capabalities.setBrowserName("chrome");
			}
			else if(p.getProperty("browser").equalsIgnoreCase("edge")) {
				capabalities.setBrowserName("MicrosoftEdge");
			}
			else {
				System.out.println("no matching browser .....");
				return;
			}
			 driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub") , capabalities);
		}
		else if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			
			if(p.getProperty("browser").equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
				logger.info("Chrome browser opened successfully");
			}
			else if(p.getProperty("browser").equalsIgnoreCase("edge")){
				driver = new EdgeDriver();
				logger.info("Edge browser opened successfully");
			}
			else {
				System.out.println("no matching browser......");
				logger.info("no matching browser......");
				return;
			}
		}
		rb=ResourceBundle.getBundle("config");
		driver.get(rb.getString("WebsiteURL"));//get URL from config.properties file
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	@AfterClass
	public void terminateWholeBrowser()
	{
		driver.quit();
		
	}
	
	@AfterClass
	public String captureScreen() 
	{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd.hh.mm.ss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFile=System.getProperty("user.dir")+"\\ScreenCapture\\" + timeStamp + ".png";
		try
		{
			FileUtils.copyFile(sourceFile,new File(targetFile));
		}
		catch(Exception e)
		{
			e.getMessage();
		}
		return targetFile;
	}
}
