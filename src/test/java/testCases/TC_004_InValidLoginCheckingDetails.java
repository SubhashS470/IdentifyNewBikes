package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import PageObjects.InvalidLoginChecking;
import UtilityFiles.Screenshots;
import testBases.BaseClass;

public class TC_004_InValidLoginCheckingDetails extends BaseClass
{
	public static String path;

	@Test
	 public void InvalidLogin() throws IOException, InterruptedException
	 {
		  InvalidLoginChecking CIL=new InvalidLoginChecking(driver);
	   logger.info("Click the icon called 'Login/signup' in ZigWheels site ☑️");	
		  WebElement signUp=CIL.loginSignUP();
		  signUp.click();
       logger.info("Click the google sign in button for authentication ☑️");	  		  
		  WebElement googlebtn=CIL.GoogleSignIn();
		  googlebtn.click();
		  Set<String> wind=driver.getWindowHandles();
		  List<String> id=new ArrayList<String>(wind);
		  driver.switchTo().window(id.get(1));
		  WebElement txtBx=CIL.textBox();
		  txtBx.sendKeys("skrugvynwh@gmail.com");
		  WebElement nextBtn=CIL.nextBtn();
		  nextBtn.click();
		  WebElement msg=CIL.ErrorMsg();
		  path=Screenshots.screenShots(driver, "Invalid Login");
		  String errorMsg=msg.getText();
		  System.out.println(errorMsg);
		logger.info("Invalid login capture message has been captured ☑️"); 

	 }
}
