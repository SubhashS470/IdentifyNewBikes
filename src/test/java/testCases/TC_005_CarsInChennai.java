package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import PageObjects.CarsInChennai;
import UtilityFiles.ExcelUtilsD;
import UtilityFiles.ExcelUtilss;
import UtilityFiles.Screenshots;
import testBases.BaseClass;

public class TC_005_CarsInChennai extends BaseClass
{
	List<String> c;
	String arr[]=new String[9];
    static int i;
	public static String path;


	@Test
	public void usedcars() throws InterruptedException, IOException
	{
		CarsInChennai car=new CarsInChennai(driver);
		logger.info("Click the icon called 'Used Cars' in ZigWheels site ☑️");
		WebElement usedCar=car.usedCars();
		Actions act=new Actions(driver);
		act.moveToElement(usedCar).build().perform();
		logger.info("Select the icon called 'Used Cars In Chennai' ☑️");
		WebElement carChennai=car.usedCarsChennai();
		JavascriptExecutor jav=(JavascriptExecutor)driver;
		jav.executeScript("arguments[0].click();", carChennai);
		logger.info("Get all the datas of popular models Used in Chennai ☑️");
		WebElement models=car.popularModels();
		Thread.sleep(10000);
		//JavascriptExecutor jav=(JavascriptExecutor)driver;
		jav.executeScript("arguments[0].scrollIntoView(true);", models);
    	path=Screenshots.screenShots(driver, "Popular Cars In Chennai");
		List<WebElement> done=car.maruti();
		Thread.sleep(2500);
		for(WebElement don:done)
		{
			System.out.println("Hello loop");
			String value=don.getText();
			arr[i]=value;
			i++;
			System.out.println(value);
		}
		
		ExcelUtilsD euv=new ExcelUtilsD();
		euv.writeDData("PopularCarsInCHENNAI",arr , 0, 2);
		logger.info("Popular models could printed in Excel sheet ☑️");
		logger.info("Entire project has been successfully completed ☑️");
	}
	
}
