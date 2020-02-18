package TestCases;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import Pages.RegisterPage;
import Utils.JyperionListener;
import Utils.ReadCsv;
import Utils.Reporter;
import junit.framework.Assert;


//@Listeners(JyperionListener.class)
@Listeners(value=Reporter.class)
public class TestCases {
	 ExtentReports extent;
	 ExtentTest logger;
	 
	WebDriver driver;
	WebDriverWait wait;
	String CSVFileName= "TestCaseData.xlsx";
	String CSVPath= "./ReadFrom/TestCaseData.csv";

@DataProvider(name = "UserData")
public Object[] UserData() throws IOException {

	ReadCsv csvfile1= new ReadCsv();
	return csvfile1.dataProvider_ByCSVReader2(CSVPath);

}

//Verifying elements on Registration page
  //   @Test (priority=1)
     public void TestCase1()   {
	               System.out.println(":@TestCases1");
	               RegisterPage TC1 = new RegisterPage(driver);
	               TC1.verifyElemntsOnPageTest(driver);
	               }


//Registration with all valid data and invalid data
	@Test (priority=2,dataProvider = "UserData", enabled=true)
	public void TestCase2(String TestCaseID,String FirstName, String LastName,
			String Mobile, String Email,
			String Password, String ConfirmPassword) throws InterruptedException {
	//	System.out.println( TestCaseID+ FirstName+LastName
		//		+ Mobile+ Email+Password+ ConfirmPassword);

		System.out.println(":@TestCase2");
		RegisterPage TC2 = new RegisterPage(driver);
		TC2.signup(driver, FirstName, LastName, Mobile, Email, Password, ConfirmPassword);
		//TC2.asserttooltipnotappear(driver);
		TC2.assert_email_error_notappear(driver);
		TC2.assert_emailExists_error_notappear(driver);
		TC2.assert_pwd_policy_msg_notappear(driver);
		TC2.assertpwdmatch(driver);
		
		TC2.assert_signup_success(FirstName);
		
	}
	

	
	
	

	@BeforeMethod(alwaysRun = true)
	public void Setup() throws Exception {
		System.out.println(":@BeforeMethod");
	  //	DOMConfigurator.configure("./ReadFrom/log4j.xml");
	  	
		// Start printing the logs and printing the Test Case name
//		extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	  	
//		extent =new ExtentReports(System.getProperty("user.dir")+"\\ExtentReportResults.html");
//        extent.loadConfig(new File("./ReadFrom/extent-config.xml"));
//		logger = extent.startTest("start test");

//	  	Utils.Log.startTestCase(sTestCaseName);
		
		Utils.ReadingPropertiesFile data = new Utils.ReadingPropertiesFile("./ReadFrom/config.properties");

		// Read (Browser) from property file throw method in [utils] Package and add it in String variable [browser] using data.GetBrowser();
		//driver =Utils.WebDriverFactory.HeadlessDriver(data.GetBrowser());
		driver =Utils.WebDriverFactory.Driver_Path(data.GetBrowser(),"./WebDriver/");

		// Read URL from property file throw method in [utils] Package
		driver.get(data.GetURL());

	}

	
	@AfterMethod(alwaysRun = true)
	public void TearDown(ITestResult Result) throws Exception {
		System.out.println(":@AfterMethod");
	
		// Add time stamp to use it screenshot
		long TimeStamp = System.currentTimeMillis();
		// add screen catupre casesd
		if (Result.isSuccess())
			Utils.ScreenCapture.getFullScreenShot(driver, "./screenshot/myImage" + TimeStamp + ".png");
		else
			{Utils.ScreenCapture.getFullScreenShot(driver, "./screenshot/MyFailImage" + TimeStamp + ".png");
			//logger.log(LogStatus.FAIL,logger.addScreenCapture( "./screenshot/MyFailImage" + TimeStamp + ".png")+ "Test Failed");
			}

		driver.quit();

	}
	

	
}
