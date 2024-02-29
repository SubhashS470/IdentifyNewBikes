package Step_Definitions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;

import PageObjects.CarsInChennai;
import PageObjects.HondaPriceSelection;
import PageObjects.HondaSelection;
import PageObjects.InvalidLoginChecking;
import PageObjects.UpcomingBikes;
import UtilityFiles.ConvertToString;
import UtilityFiles.ExcelUtilsD;
import UtilityFiles.ExcelUtilss;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Stteps 
{
	WebDriver driver;
	Logger logger;
	ResourceBundle rb;
	String br;
	public static List<WebElement> BrandsInfoElement; //1
	public static List<String> BrandsInfo; //1
	//............................................................................................1
	public static List<WebElement> BrandsInfoBikes; //2
	public static List<String> BrandsBikes; //2
	public static List<WebElement> BrandsInfoPrices; //2
	public static List<String> BrandPrice; //2
	public static List<WebElement> BrandsInfoMiles; //2
	public static List<String> BrandMiles; //2
	//............................................................................................2
	ExcelUtilsD ed=new ExcelUtilsD();
	int count=0;
	//int str[]=new int[40]; 
    //float arr[]=new float[10];
	String brr[]=new String[10];
	String prr[]=new String[10];
	String rrr[]=new String[10];
	String bbr[]=new String[10];
	String ppr[]=new String[10];
	String rrrr[]=new String[10];
	int j=0;
	int s=0;
	int u=0;
	List<String> bikeNames=new ArrayList<String>();
	List<String> bikePrices=new ArrayList<String>();
	List<String> bikeReleaseDates=new ArrayList<String>();
	Map<String,String[]> bikeUnder4Lac=new HashMap<String,String[]>();
	Map<String,String[]> bikeAbove4Lac=new HashMap<String,String[]>();;	
 	static int r=0;
 	static int q=0;
 	static int w=0;
	ExcelUtilss eu = new ExcelUtilss();
	//...............................................................................................3
	List<String> c;
	String arr[]=new String[9];
    static int i;
    //.................................................................................................5
	@Before
	public void setup() throws IOException
	{
		
		logger=LogManager.getLogger(this.getClass());
		rb=ResourceBundle.getBundle("config");
		br=rb.getString("browser");	
	}
	
	@After
	public void tearDown(Scenario scenario)
	{
		System.out.println("Scenario status======>"+ scenario.getStatus());
		if(scenario.isFailed())
		{
			TakesScreenshot ts=(TakesScreenshot)driver;
			byte[] screenshot=ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image", scenario.getName());
		}	
	}
	
	@Given("Selecting the browser we want")
	public void Selecting_the_browser_we_want()
	{
		logger.info("Chrome Window has been opened ☑️");
		if(br.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			logger.info("Firefox Window has been opened ☑️");	
			driver=new FirefoxDriver();
		}
		else if(br.equals("edge"))
		{
			logger.info("Edge Window has been opened ☑️");
			driver=new EdgeDriver();
		}
	}
	
	@And("Navigate to ZigWheels site")
	public void Navigate_to_ZigWheels_site()
	{
		logger.info("Website ZigWheels has been navigated successfully ☑️");
		driver.get("https://www.zigwheels.com/");
		driver.manage().window().maximize();
	}
	
	@When("Selection of all the brands shown in ZigWheels")
	public void Selection_of_all_the_brands_shown_in_ZigWheels() throws InterruptedException
	{
		logger.info("Selecting all the brands of Bikes -process started  ☑️");
		HondaSelection hs=new HondaSelection(driver);
		WebElement NewBikes=hs.NewBikes();
		Actions act=new Actions(driver);
		act.moveToElement(NewBikes).perform();
		Thread.sleep(1300);
		WebElement Brands=hs.popularBikeBrands();
		act.moveToElement(Brands).perform();
		Thread.sleep(1300);		
		WebElement AllBrands=hs.AllBrands();
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", AllBrands);
		Thread.sleep(2000);
		logger.info("Selecting all the brands of Bikes -process ended  ☑️");
		
	}

	@And("All the brands sent to Excel Sheet")
	public void All_the_brands_sent_to_Excel_Sheet() throws InterruptedException, IOException
	{
		logger.info("All brands of Bikes starts printing in Excel sheet  ☑️");		
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
	
	@Then("Quitting the driver")
	public void Quitting_the_driver()
	{
		driver.quit();
		
	}
	
	@When("Selecting the all brands viewed in ZigWheels site")
	public void Selecting_the_all_brands_viewed_in_ZigWheels_site() throws InterruptedException
	{
		HondaPriceSelection hss=new HondaPriceSelection(driver);
		logger.info("Clicking the New Bikes icon in ZigWheels site ☑️");
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
		Thread.sleep(2000);
	}
	
	@And("Search the bikes less than 4 lakhs")
	public void Search_the_bikes_less_than_4_lakhs() throws InterruptedException
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
	}
	
	@And("All the collected datas printed in Excel Sheet")
	public void All_the_collected_datas_printed_in_Excel_Sheet() throws IOException
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
	
	@When("Hover the icon called Upcoming_bikes")
	public void Hover_the_icon_called_Upcoming_bikes() throws InterruptedException
	{
		UpcomingBikes upbi=new UpcomingBikes(driver);
		logger.info("Click the icon called 'New Bikes' in ZigWheels site ☑️");	
		WebElement up=upbi.NewBikess();
		Actions act=new Actions(driver);
		act.moveToElement(up).perform();
		Thread.sleep(1300);
		logger.info("Click the icon called 'Upcoming Bikes' in ZigWheels site ☑️");	
		WebElement Upbike=upbi.clickUpcomingBikes();
		act.moveToElement(Upbike).perform();
		Upbike.click();
		Thread.sleep(1300);
		
	}
	
	@And("Selects the manufacturer called Honda")
	public void Selects_the_manufacturer_called_Honda() throws InterruptedException
	{
		UpcomingBikes upbi=new UpcomingBikes(driver);
		logger.info("Selects the manufacturer called 'Honda' ☑️");	
		WebElement manufacture=upbi.clickManufacturers();
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("arguments[0].click();", manufacture);
		Thread.sleep(2000);
		Select honda=new Select(manufacture);
        honda.selectByVisibleText("Honda");
        Thread.sleep(1000);
		logger.info("Click the ReadMore icon to view 'yet to launch vehicles' ☑️");	
        WebElement ReadMore=upbi.clickReadMore();
        ReadMore.click();		
        //Navigate().to("https://www.zigwheels.com/upcoming-bikes");
        driver.navigate().back();
	}
	
	@And("All the collected datas printed in Excel Sheet1")
	public void All_the_collected_datas_printed_in_Excel_Sheet1() throws IOException
	{
		logger.info("The Model,Expected price and Expected Launch Date has starts printing in Excel ☑️");	
		Select select =  new Select(driver.findElement(By.id("makeId")));

		select.selectByVisibleText("Honda");

		WebElement loadMore = driver.findElement(By.xpath("//span[normalize-space()='...Read More']"));

		loadMore.click();
 
			for(int i=1 ; i<=10;i++) {

			String bikeName = driver.findElement(By.xpath("//table//tr["+i+"]//td[1]")).getText();

			bikeNames.add(bikeName);

			String bikePrice = driver.findElement(By.xpath("//table//tr["+i+"]//td[2]")).getText();

			bikePrices.add(bikePrice);

			String bikeReleaseDate = driver.findElement(By.xpath("//table//tr["+i+"]//td[3]")).getText();

			bikeReleaseDates.add(bikeReleaseDate);

			String[] bikePrice_ =  bikePrice.split(" ");

			double bikePriceD = 0;

			int bikePriceI = 0;

			if(bikePrice_[1].contains(".")) {

				bikePriceD = Double.parseDouble(bikePrice_[1]);

			}

			else {

				String a = bikePrice_[1].replace(",", "");

				bikePriceI = Integer.parseInt(a);

			}

			if(bikePriceD<4.0) 
			{
				bikeUnder4Lac.put(bikeName, new String[] {bikePrice,bikeReleaseDate});
				if(bikeUnder4Lac.size()!=0)
				{
					brr[q]=bikeName;
					prr[q]=bikePrice;
					rrr[q]=bikeReleaseDate;
					
				}
				q++;
			}
			else 
			{
				bikeAbove4Lac.put(bikeName, new String[] {bikePrice,bikeReleaseDate});
				if(bikeAbove4Lac.size()!=0)
				{
					bbr[w]=bikeName;
					ppr[w]=bikePrice;
					rrrr[w]=bikeReleaseDate;
				}
				w++;
			}


		}
			
		for(Map.Entry<String, String[]> e :bikeUnder4Lac.entrySet()) {

			System.out.println(e.getKey()+"----->>"+e.getValue()[0]+"----->>"+e.getValue()[1]);

		}
		
		for(Map.Entry<String, String[]> e :bikeAbove4Lac.entrySet()) {

			System.out.println(e.getKey()+"----->>"+e.getValue()[0]+"----->>"+e.getValue()[1]);

		}
	    	    ed.writeDData("UnLaunched(<4Lakhs)Bikes",brr,0,0);
				ed.writeDData("UnLaunched(>4Lakhs)Bikes",bbr,0,0);
				ed.writeDData("UnLaunched(<4Lakhs)Bikes",prr,0,1);
				ed.writeDData("UnLaunched(>4Lakhs)Bikes",ppr,0,1);
				ed.writeDData("UnLaunched(<4Lakhs)Bikes",rrr,0,2);
				ed.writeDData("UnLaunched(>4Lakhs)Bikes",rrrr,0,2); 
	    logger.info("The Model,Expected price and Expected Launch Date has been printed in Excel ☑️");				
			
	}
	
	@When("Click the icon called Invalid Login")
	public void Click_the_icon_called_Invalid_Login() throws InterruptedException
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
		  String errorMsg=msg.getText();
		  System.out.println(errorMsg);
		logger.info("Invalid login capture message has been captured ☑️"); 
	}
	
	
	@When("Used Cars in Chennai")
	public void Used_Cars_in_Chennai() throws InterruptedException, IOException
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
		List<WebElement> done=car.maruti();
		Thread.sleep(2500);
		for(WebElement don:done)
		{
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
