package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HondaSelection extends BasePage
{
	public HondaSelection(WebDriver driver)
	{
		super(driver);
	}
	
	public WebElement NewBikes()
	{
		WebElement NewBikes=driver.findElement(By.linkText("New Bikes"));
		return NewBikes;		
	}
	
	public WebElement popularBikeBrands()
	{
		WebElement brands=driver.findElement(By.linkText("Popular Bike Brands"));
		return brands;
		
	}
	
	public WebElement AllBrands()
	{
		WebElement allBrands=driver.findElement(By.xpath("//span[text()=' All Bike Brands']"));
		return allBrands;
	}
	
	public void SearchingBikes() throws InterruptedException
	{
		WebElement BikeSearch=driver.findElement(By.xpath("//h2[text()='Search New Bikes' and @class='ml-0']"));
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].scrollIntoView(true);", BikeSearch);
		Thread.sleep(1500);		
	}
	
	public WebElement Bikessearch()
	{
		WebElement searchh=driver.findElement(By.xpath("//select[@id='byBrandMake']"));
		return searchh;
	}
	
	public List<WebElement> searchingBikes()
	{
		List<WebElement> listt=driver.findElements(By.xpath("//select[@id='byBrandMake']//option"));
		return listt;
	}
	
	public WebElement searchHonda()
	{
		WebElement refere=driver.findElement(By.xpath("//select[@id='byBrandMake']//option[4]"));
		return refere;
	}

	
	public WebElement searchButton()
	{
		WebElement refer=driver.findElement(By.xpath("//button[text()='Search']"));
		return refer;
	}

}////option[text()='Honda']
