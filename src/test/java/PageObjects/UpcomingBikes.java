package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UpcomingBikes extends BasePage
{
	public UpcomingBikes(WebDriver driver)
	{
		super(driver);
	}
	
	public WebElement NewBikess()
	{
		WebElement vehicle=driver.findElement(By.linkText("New Bikes"));
		return vehicle;
	}
	
	public WebElement clickUpcomingBikes()
	{
		WebElement bikee=driver.findElement(By.xpath("//span[text()='Upcoming Bikes']"));
		return bikee;
	}
	
	public WebElement clickManufacturers()
	{
		WebElement manufacture=driver.findElement(By.xpath("//*[@id=\"makeId\"]"));
		return manufacture;
	}
	
	public WebElement clickReadMore()
	{
		WebElement Read=driver.findElement(By.xpath("//span[text()='...Read More']"));
		return Read;
	}
	////span[text()='...Read More']
	
	public List<WebElement> tablehead()
	{
		List<WebElement> head=driver.findElements(By.xpath("//select[@data-track-component=\"bike-upcoming\"]"));
		return head;
	}
	
	public List<WebElement> tableRowsPRICE()
	{
		List<WebElement> rowsPrice=driver.findElements(By.xpath("//tr[@class='ml-15 ']//td[2]"));
		return rowsPrice;
	}
	
	public List<WebElement> tableRowsBIKES()
	{
		List<WebElement> rowsBikes=driver.findElements(By.xpath("//tr[@class='ml-15 ']//td[1]"));
		return rowsBikes;
	}
	
	public List<WebElement> tableRowsLaunch()
	{
		List<WebElement> rowsLaunch=driver.findElements(By.xpath("//tr[@class='ml-15 ']//td[3]"));
		return rowsLaunch;
	}
	

}
