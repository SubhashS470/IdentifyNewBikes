package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {".//FeatureFiles//Identify_New_Bikes.feature"},
		glue="StepDefinitions" ,
		plugin= {"pretty","html:CucumberReports//myreport.html",
				"json:CucumberReports/myreport.json","rerun:target/rerun.txt",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		},
		dryRun=false,
		monochrome=true,
		tags="@Smoke"	
		)
public class TestRunner 
{
	

}
