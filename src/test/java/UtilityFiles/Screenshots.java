package UtilityFiles;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshots {
	
	
	public static String screenShots(WebDriver driver,String filename) throws IOException {
		TakesScreenshot ts=(TakesScreenshot)driver;
	    File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("C:\\Users\\2304153\\eclipse-workspace\\IdentifyNewBikes\\screenshots\\"+filename+".png");
		FileUtils.copyFile(src,trg);
		return trg.getAbsolutePath();

}
}
