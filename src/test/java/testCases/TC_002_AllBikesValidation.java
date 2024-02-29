package testCases;

import testBases.BaseClass;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageObjects.HondaPriceSelection;
import PageObjects.HondaSelection;
import UtilityFiles.ConvertToString;
import UtilityFiles.ExcelUtilss;
import UtilityFiles.Screenshots;

@Listeners(ExtentReportManager.ExtenttReportss.class)

public class TC_002_AllBikesValidation extends BaseClass
{
	public static List<WebElement> BrandsInfoBikes;
	public static List<String> BrandsBikes;
	public static List<WebElement> BrandsInfoPrices;
	public static List<String> BrandPrice;
	public static List<WebElement> BrandsInfoMiles;
	public static List<String> BrandMiles;
	ExcelUtilss eu = new ExcelUtilss();
	public static String path;
    @Test
	public void HondaUnder4Lakhs() throws InterruptedException, IOException
	{
		logger.info("Clicking the New Bikes icon in ZigWheels site ☑️");
		HondaPriceSelection hss=new HondaPriceSelection(driver);
		WebElement NewBikes=hss.ClickNewBikes();
		Actions act=new Actions(driver);
		act.moveToElement(NewBikes).perform();
		Thread.sleep(1300);
		logger.info("Clicking the icon called 'Bikes By Budeget' ☑️");		
		WebElement Brands=hss.ClickPrice();
		act.moveToElement(Brands).perform();
		Thread.sleep(1300);		
		logger.info("Clicking the price under 2 Lakhs ☑️");		
		WebElement AllBrands=hss.priceRange();
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", AllBrands);
    	path=Screenshots.screenShots(driver, "All Bikes Validation");
		Thread.sleep(2000);
		HondaEncode2(driver);
	}
	
	public void HondaEncode2(WebDriver driver) throws InterruptedException, IOException
	{
		HondaPriceSelection hss=new HondaPriceSelection(driver);
		logger.info("Scrolling the cursor upto bikes budget selection ☑️");		
        hss.SearchingBikes();
		logger.info("Click the icon called 'More Options' ☑️");		
        WebElement Bikessearch=hss.ClickMoreOptions();
        JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", Bikessearch);
		Thread.sleep(1800);
		logger.info("Select the company brand called 'Honda' ☑️");		
		WebElement button=hss.clickHonda();
		js.executeScript("arguments[0].click();", button);
		Thread.sleep(1200);
		logger.info("Select the price range 'under 4 lakhs' ☑️");		
		WebElement bn=hss.clickRanges();
		Select bnn=new Select(bn);
        bnn.selectByVisibleText("4 Lakh");
		logger.info("Click the icon called 'LoadMore' for loading more bikes ☑️");		
		WebElement load=hss.LoadMore();
		load.click();
		Thread.sleep(500);	
		hss.startPoint();
		hondaPrinting(driver);
		
	}
	
	public void hondaPrinting(WebDriver driver) throws IOException
	{
		HondaPriceSelection hss=new HondaPriceSelection(driver);		
		List<WebElement> names=hss.bikes();
		BrandsInfoBikes=names;
		BrandsBikes = ConvertToString.convertData(BrandsInfoBikes, BrandsBikes );
		eu.writeData("Launched Bikes",BrandsBikes,0,1);
		logger.info("Names of Honda bikes under 400000 has printed in Excelsheet ☑️");		
		
		List<WebElement> price=hss.price();
		BrandsInfoPrices=price;
		BrandPrice=ConvertToString.convertData(BrandsInfoPrices, BrandPrice );
		eu.writeData("Launched Bikes",BrandPrice,0,2);
		logger.info("Prices has been printed in Excelsheet ☑️");	
		
		List<WebElement> miles=hss.mileage();
		BrandsInfoMiles=miles;
		BrandMiles=ConvertToString.convertData(BrandsInfoMiles, BrandMiles );
		eu.writeData("Launched Bikes",BrandMiles,0,3);
		logger.info("Mileage of bikes under 400000 has been printed in Excelsheet ☑️");	

	}
 
	
	

}
