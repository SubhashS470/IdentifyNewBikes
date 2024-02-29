package PageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HondaPriceSelection extends BasePage
{
	public HondaPriceSelection(WebDriver driver)
	{
		super(driver);
	}
	
	public WebElement ClickNewBikes()
	{
		WebElement NewBikes=driver.findElement(By.linkText("New Bikes"));
		return NewBikes;		
	}

	public WebElement ClickPrice()
	{
		WebElement Budgetdetails=driver.findElement(By.linkText("Bikes By Budget")); 
		return  Budgetdetails;
	}
	
	public WebElement priceRange()
	{
		WebElement priceRange=driver.findElement(By.xpath("//span[text()='Bikes Above 5 Lakhs']"));
		return priceRange;
	}
	
	public void SearchingBikes() throws InterruptedException
	{
		WebElement BikeSearch=driver.findElement(By.xpath("//li[text()='Bikes by Budget' and @class='gsc-ta-active']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", BikeSearch);
		Thread.sleep(500);		
	}
	
	public WebElement ClickMoreOptions()
	{
		WebElement MoreOptions=driver.findElement(By.xpath("(//div[@id=\"search-electric\"]//a[@title=\"More Bike Search Options\"])[1]//strong"));
		return MoreOptions;
	}
	
	public WebElement clickHonda() throws InterruptedException
	{
		Thread.sleep(2500);
		WebElement Search=driver.findElement(By.xpath("//div//span[text()='Brand']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", Search);
		Thread.sleep(500);	
		WebElement honda=driver.findElement(By.xpath("//label[@for='bymakeid53']"));
		Thread.sleep(2500);
		return honda;
	}
	
	public WebElement clickRanges() throws InterruptedException
	{
		WebElement Bike=driver.findElement(By.xpath("//span[text()='Price Range']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", Bike);
		Thread.sleep(500);	
		WebElement hondas=driver.findElement(By.xpath("//select[@id='maxPrice']"));
		return hondas;
	}
	
	public WebElement LoadMore() throws InterruptedException
	{
		//WebElement Search=driver.findElement(By.xpath("//span[text()='Load More']"));
		try
		{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Load More']")));
		WebElement Search=driver.findElement(By.xpath("//span[text()='Load More']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", Search);
		Thread.sleep(500);	
		WebElement more=driver.findElement(By.xpath("//span[text()='Load More']"));
		return more;
		}
		catch(StaleElementReferenceException e)
		{
			WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Load More']")));
			Thread.sleep(1000);	
			WebElement Search=driver.findElement(By.xpath("//span[text()='Load More']"));
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("arguments[0].scrollIntoView(true);", Search);
			Thread.sleep(500);	
			WebElement more=driver.findElement(By.xpath("//span[text()='Load More']"));
			return more;	
		}
	}
	
	public void startPoint() throws InterruptedException
	{
		WebElement Search=driver.findElement(By.xpath("//span[text()='Sort By:']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", Search);
		Thread.sleep(500);	
	}
	
	//span[text()='Sort By:']
	/*
         Select hondass=new Select(hondas);
         hondass.SelectByVisibleText("4 Lakh");	*/
	//span[text()='Load More']
	
	//label[@for="bymakeid53"]
	
	public List<WebElement> bikes()
	{
		List<WebElement> bikess=driver.findElements(By.xpath("//strong[@class=\"lnk-hvr block of-hid h-height\"]"));
		return bikess;
		
	}
	
	public List<WebElement> price()
	{
		List<WebElement> pricess=driver.findElements(By.xpath("//div[@class=\"clr-bl p-5\"]"));
		return pricess;
	}
	
	public List<WebElement> mileage()
	{
		List<WebElement> miles=driver.findElements(By.xpath("//div[@class=\"clr-try fnt-14 pb-10 h-height of-hid\"]"));
	    return miles;
	}
}
