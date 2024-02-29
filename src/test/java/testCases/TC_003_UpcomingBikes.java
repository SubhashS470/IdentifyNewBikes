package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObjects.HondaPriceSelection;
import PageObjects.UpcomingBikes;
import UtilityFiles.ConvertToString;
import UtilityFiles.ExcelUtilsD;
import UtilityFiles.ExcelUtilss;
import UtilityFiles.Screenshots;
import testBases.BaseClass;

public class TC_003_UpcomingBikes extends BaseClass
{
	public static List<WebElement> BrandsInfo;
	public static List<String> Brand;
	public static List<WebElement> BrandsInfoBikes;
	public static List<String> BrandBikes;
	public static List<WebElement> BrandsInfoPrices;
	public static List<String> BrandPrice;
	public static List<WebElement> BrandsInfoLaunch;
	public static List<String> BrandLaunch;
	ExcelUtilss eu = new ExcelUtilss();
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
	public static String path;
	//static int p=1;

	@Test(priority=1)
	public void HondaManufacturer() throws InterruptedException, IOException
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
    	path=Screenshots.screenShots(driver, "Upcoming Bikes");
		Thread.sleep(1300);
		
	}
	@Test(priority=2)
	public void HondaEncode2() throws InterruptedException
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
   /* @Test(priority=3)
	public void HandlingTable() throws IOException
	{
		UpcomingBikes upbi=new UpcomingBikes(driver);
		List<WebElement> table=upbi.tablehead();
		BrandsInfo=table;
		Brand= ConvertToString.convertData(BrandsInfo, Brand);
		eu.writeData("UnLaunched Bikes",Brand,0,1);
	}*/
    
       @Test(priority = 3)
    	public void TC_03_extractHondaBikes() throws InterruptedException, IOException 
        {    	   
   		   logger.info("The Model,Expected price and Expected Launch Date has starts printing in Excel ☑️");	
    		Select select =  new Select(driver.findElement(By.id("makeId")));
    		select.selectByVisibleText("Honda");
    		WebElement loadMore = driver.findElement(By.xpath("//span[normalize-space()='...Read More']"));
    		loadMore.click();    
   			for(int i=1 ; i<=10;i++) 
   			{
    			String bikeName = driver.findElement(By.xpath("//table//tr["+i+"]//td[1]")).getText();
				bikeNames.add(bikeName);
    			String bikePrice = driver.findElement(By.xpath("//table//tr["+i+"]//td[2]")).getText();
    			bikePrices.add(bikePrice);
    			String bikeReleaseDate = driver.findElement(By.xpath("//table//tr["+i+"]//td[3]")).getText();
				bikeReleaseDates.add(bikeReleaseDate);
    			String[] bikePrice_ =  bikePrice.split(" ");
    			double bikePriceD = 0;
    			int bikePriceI = 0;
    			if(bikePrice_[1].contains(".")) 
    			{
    				bikePriceD = Double.parseDouble(bikePrice_[1]);
    			}
    			else 
    			{
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
            excel(brr,bbr,prr,ppr,rrr,rrrr);
    		for(Map.Entry<String, String[]> e :bikeUnder4Lac.entrySet()) 
    		{
    			System.out.println(e.getKey()+"----->>"+e.getValue()[0]+"----->>"+e.getValue()[1]);
    		}   		
    		for(Map.Entry<String, String[]> e :bikeAbove4Lac.entrySet()) 
    		{
    			System.out.println(e.getKey()+"----->>"+e.getValue()[0]+"----->>"+e.getValue()[1]);
    		}
    }
      // @Test
       public void excel(String[] brr,String[] bbr,String[] prr,String[] ppr,String[] rrr,String[] rrrr) throws IOException
       {
    	    ed.writeDData("UnLaunched(<4Lakhs)Bikes",brr,0,0);
			ed.writeDData("UnLaunched(>4Lakhs)Bikes",bbr,0,0);
			ed.writeDData("UnLaunched(<4Lakhs)Bikes",prr,0,1);
			ed.writeDData("UnLaunched(>4Lakhs)Bikes",ppr,0,1);
			ed.writeDData("UnLaunched(<4Lakhs)Bikes",rrr,0,2);
			ed.writeDData("UnLaunched(>4Lakhs)Bikes",rrrr,0,2); 
		    logger.info("The Model,Expected price and Expected Launch Date has been printed in Excel ☑️");
       }
	/*@Test(priority=4)
	public void HandlingPrice() throws IOException
	{
		UpcomingBikes upbi=new UpcomingBikes(driver);
		List<WebElement> SecondRows=upbi.tableRowsPRICE();
	 for(WebElement ele:SecondRows)
	 {
			String value =ele.getText();
			//j++;
			String tr[]=value.split(" ");
		for(int p=0;p<tr.length;p++)
		{
			try
			{
				if(tr[p].contains("Rs.") || tr[p].contains("Lakh"))
				{
					continue;
				}
				if(tr[p].contains("79,000"))
				{
					String kill=tr[p].replace(",","");
					arr[s]=(float)Integer.parseInt(kill);
					//arr[s]=79;
					continue;
				}
				arr[s]=(float)Integer.parseInt(tr[p]) ;
			}
			catch(Exception e)
			{
				arr[s]=Float.parseFloat(tr[p]) ;
			}
			s++;
		}	
	 }

		List c = new ArrayList<String>();
		List d = new ArrayList<String>(); 
		for(int i =0; i<SecondRows.size(); i++) {
			
			if(arr[i] < 4 || arr[i]==79000)
			{
				c.add(SecondRows.get(i).getText());				
			}
			else
			{
				str[u]=count;
				u++;
				d.add(SecondRows.get(i).getText());
			}
						
		}
	
        //BrandsInfoPrices=c;
        //BrandPrice=ConvertToString.convertData(BrandsInfoPrices, BrandPrice);
		eu.writeData("UnLaunched(<400000)Bikes",c,1,2);
		//BrandsInfoPrices=d;
        //BrandPrice=ConvertToString.convertData(BrandsInfoPrices, BrandPrice);
		eu.writeData("UnLaunched(>400000)Bikes",d,0,1);
	}
	@Test(priority=5)
	public void HandlingBike() throws IOException
	{
		UpcomingBikes upbi=new UpcomingBikes(driver);		
        List<WebElement> FirstRows=upbi.tableRowsBIKES();
        List e = new ArrayList<String>();
		List f = new ArrayList<String>(); 
		for(int i =0; i<FirstRows.size(); i++) 
		{
		  //for(int r=0;r<str.length;r++)
		 // {
			if(str[r]!=i)
			{
				e.add(FirstRows.get(i).getText());				
			}
			else
			{
				f.add(FirstRows.get(i).getText());
			}
			r++;
		 //}			
		}
        //BrandsInfoBikes=e;
        //BrandBikes=ConvertToString.convertData(BrandsInfoBikes, BrandBikes);
		eu.writeData("UnLaunched(<400000)Bikes",e,1,1);
		//BrandsInfoBikes=f;
        //BrandBikes=ConvertToString.convertData(BrandsInfoBikes, BrandBikes);
		eu.writeData("UnLaunched(>400000)Bikes",f,0,0);
	}
	
	@Test(priority=6)
	public void HandlingLaunch() throws IOException
	{
		UpcomingBikes upbi=new UpcomingBikes(driver);
		List<WebElement> ThirdRows=upbi.tableRowsLaunch();
		List g = new ArrayList<String>();
		List h = new ArrayList<String>(); 
		for(int i =0; i<ThirdRows.size(); i++) 
		{
		  //for(int r=0;r<str.length;r++)
		  //{
			if(str[r]!=i)
			{
				g.add(ThirdRows.get(i).getText());				
			}
			else
			{
				h.add(ThirdRows.get(i).getText());
			}
			r++;
		 //}			
		}
        //BrandsInfoLaunch=g;
        //BrandLaunch=ConvertToString.convertData(BrandsInfoLaunch, BrandLaunch);
		eu.writeData("UnLaunched(<400000)Bikes",g,1,3);
		//BrandsInfoLaunch=h;
        //BrandLaunch=ConvertToString.convertData(BrandsInfoLaunch, BrandLaunch);
		eu.writeData("UnLaunched(>400000)Bikes",h,0,2);
	}*/
       
	
}
