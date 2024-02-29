package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarsInChennai extends BasePage
{
	public CarsInChennai(WebDriver driver)
	{
		super(driver);
	}
	
	public WebElement usedCars()
	{
		WebElement ucars=driver.findElement(By.xpath("//a[text()='Used Cars']"));
		return ucars;
	}
	
	public WebElement usedCarsChennai()
	{
		WebElement ucarsChen=driver.findElement(By.xpath("//span[@onclick=\"goToUrl('/used-car/Chennai')\"]"));
		return ucarsChen;		
	}

	public WebElement popularModels() throws InterruptedException
	{
		WebElement popular=driver.findElement(By.xpath("//div[text()='Popular Models']"));
		Thread.sleep(2500);
		return popular;
	}
	
	public List<WebElement> maruti() throws InterruptedException
	{
		List<WebElement> elem=driver.findElements(By.xpath("//label[normalize-space()='Maruti 800'] | //label[normalize-space()='Maruti Swift Dzire'] | //label[normalize-space()='Maruti Swift'] | //label[normalize-space()='Hyundai I10'] | //label[normalize-space()='Hyundai Santro Xing'] | //label[normalize-space()='Honda City'] | //label[normalize-space()='Toyota Innova'] | //label[normalize-space()='Toyota Fortuner'] | //label[normalize-space()='Mahindra XUV500']"));
		Thread.sleep(4000);
		return elem;
		
	}
}
