package testCases;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageObjects.HondaSelection;
import UtilityFiles.ConvertToString;
import UtilityFiles.ExcelUtilss;
import UtilityFiles.Screenshots;
import testBases.BaseClass;

@Listeners(ExtentReportManager.ExtenttReportss.class)

public class TC_001_HondaBrands extends BaseClass
{
	public static List<WebElement> BrandsInfoElement;
	public static List<String> BrandsInfo;
	ExcelUtilss eu = new ExcelUtilss();
	public static String path;
    @Test
	public void SelectingAllBikes() throws InterruptedException, IOException
	{
		logger.info("Selecting all the brands of Bikes -process started  ☑️");
		HondaSelection hs=new HondaSelection(driver);
		WebElement NewBikes=hs.NewBikes();
		Actions act=new Actions(driver);
		act.moveToElement(NewBikes).perform();
		Thread.sleep(1300);
		WebElement Brands=hs.popularBikeBrands();
		act.moveToElement(Brands).perform();
    	path=Screenshots.screenShots(driver, "All Bike Brands");
		Thread.sleep(1300);		
		WebElement AllBrands=hs.AllBrands();
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", AllBrands);
		Thread.sleep(2000);
		logger.info("Selecting all the brands of Bikes -process ended  ☑️");
        //SearchingHonda(driver);
	}
	
	public void SearchingHonda() throws InterruptedException, IOException
	{
		HondaSelection hss=new HondaSelection(driver);
        hss.SearchingBikes();
        WebElement Bikessearch=hss.Bikessearch();
        JavascriptExecutor js = (JavascriptExecutor) driver; 
		//js.executeScript("arguments[0].click();", Bikessearch);
		Thread.sleep(1800);
		Bikessearch.click();
		List<WebElement> searchbikes=hss.searchingBikes();
		BrandsInfoElement=searchbikes;
		Thread.sleep(1000);
		BrandsInfo = ConvertToString.convertData(BrandsInfoElement, BrandsInfo );
		eu.writeData("Bike Brands",BrandsInfo,0,0);
		logger.info("All brands of Bikes successfully printed in Excel sheet  ☑️");		
		WebElement referHonda=hss.searchHonda();
		Actions act=new Actions(driver);
		//act.moveToElement(referHonda).build().perform();
		//Thread.sleep(4000);
		referHonda.click();
		logger.info("Selecting the specific brand bike-'Honda'  ☑️");		
		WebElement button=hss.searchButton();
		js.executeScript("arguments[0].click();", button);
		Thread.sleep(4000);
		
	}

}
