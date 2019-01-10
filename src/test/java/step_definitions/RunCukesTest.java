package step_definitions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(
					features = "classpath:features", 
					tags = { "@Test" }, 
					glue = { "step_definitions" }, 
					monochrome = false, 
					plugin = { "pretty", 
							"html:target/cucumber-reports/cucumber-pretty",
							"json:target/cucumber-reports/CucumberTestReport.json", 
							"rerun:target/cucumber-reports/rerun.txt" }
				)

public class RunCukesTest 
{
	private TestNGCucumberRunner testNGCucumberRunner;

	@Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) 
	{
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() 
	{
		return testNGCucumberRunner.provideFeatures();
	}

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Throwable
	{
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Throwable
	{
		testNGCucumberRunner.finish();
	}
}