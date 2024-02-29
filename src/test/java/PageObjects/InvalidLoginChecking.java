package PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InvalidLoginChecking extends BasePage
{
  public InvalidLoginChecking(WebDriver driver)
  {
     super(driver);

  }

  public WebElement loginSignUP()
  {
    WebElement loginBtn=driver.findElement(By.xpath("//*[@id='des_lIcon']"));
    return loginBtn;
  }

  public WebElement GoogleSignIn() throws InterruptedException
  {
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='lgn-sp s ggle']")));
    WebElement signInBtn=driver.findElement(By.xpath("//*[@class='lgn-sp s ggle']"));
    Thread.sleep(2000);
    return signInBtn;
  }

  public WebElement textBox()
  {
    WebElement textBx=driver.findElement(By.id("identifierId"));
    return textBx;
  }

  public WebElement nextBtn()
  {
    WebElement button=driver.findElement(By.xpath("//span[text()='Next']"));
    return button;
  }

  public WebElement ErrorMsg()
  {
    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Couldn’t find your Google Account']")));
    WebElement error=driver.findElement(By.xpath("//div[text()='Couldn’t find your Google Account']"));
    return error;
  }

}