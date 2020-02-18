package Utils;

//::Reference:: http://toolsqa.com/selenium-webdriver/factory-design-principle-in-frameworks/

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;

public class WebDriverFactory {

	private static WebDriver driver;

	public WebDriverFactory(WebDriver driver) {

		// Make class driver= method input driver that will be sent from test Case (that create object from this class)
		WebDriverFactory.driver = driver;
		//OR
//		this.driver = driver;
	}

	private static Map<String, WebDriver> drivers = new HashMap<String, WebDriver>();

	public static WebDriver Driver_Default(@Optional("Chrome") String browserName) {
		System.out.println("@Start Factory Driver");

		switch (browserName.toLowerCase()) {
		case "chrome":
			// get recently opened driver if it exist
			driver = drivers.get("chrome");

			// Create new driver
			if (driver == null) {
//				System.setProperty("webdriver.chrome.driver", "./WebDriver/ChromeDriver.exe");
				driver = new ChromeDriver();
				drivers.put("Chrome", driver);
			}
			break;

		case "firefox":
			// get recently opened driver if it exist
			driver = drivers.get("firefox");

			// Create new driver
			if (driver == null) {
				driver = new FirefoxDriver();
				drivers.put("Firefox", driver);
			}
			break;
			
		case "ie":
			// get recently opened driver if it exist
			driver = drivers.get("ie");

			// Create new driver
			if (driver == null) {
				System.setProperty("webdriver.ie.driver", "./WebDriver/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				drivers.put("IE", driver);
			}
			break;			
			}
			driver.manage().window().maximize();
			return driver;
	}

	
	public static WebDriver Driver_Path(@Optional("chrome") String browserName,@Optional("./WebDriver/") 
	String WebDriver_FolderDirectory) {
		System.out.println("@Start Factory Driver using specific path");
		System.out.println("Use chromedriver in project directory using 'System.setProperty'");
		try{
		switch (browserName.toLowerCase()) {
		case "chrome":
			// get recently opened driver if it exist
			driver = drivers.get("chrome");

			// Create new driver
			if (driver == null) {
			// String WebDriver_Path= "./WebDriver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", WebDriver_FolderDirectory + "chromedriver.exe");
			driver = new ChromeDriver();
			drivers.put("Chrome", driver);
		}
		break;

		case "firefox":
			// get recently opened driver if it exist
			driver = drivers.get("firefox");

			// Create new driver
			if (driver == null) {
			// String WebDriver_Path= "./WebDriver/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", WebDriver_FolderDirectory + "geckodriver.exe");
			driver = new FirefoxDriver();
			drivers.put("firefox", driver);
		}
		break;
		
		case "ie":
			// get recently opened driver if it exist
			driver = drivers.get("ie");

			// Create new driver
			if (driver == null) {
			// String WebDriver_Path= "./WebDriver/iexploredriver.exe";
			System.setProperty("webdriver.ie.driver", WebDriver_FolderDirectory + "IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			drivers.put("IE", driver);
		}
		break;
		
		case "edge":
			// get recently opened driver if it exist
			driver = drivers.get("edge");
			// Create new driver
			if (driver == null) {
			//set path to Edge.exe
			System.setProperty("webdriver.edge.driver", WebDriver_FolderDirectory + "msedgedriver.exe");
			driver = new EdgeDriver();
			}
			break;

		}
		driver.manage().window().maximize();
        }
		
		catch (Exception e){

			Log.error("Class Utils | Method OpenBrowser | Exception desc : "+e.getMessage());

		}

		return driver;
	
}

	public static WebDriver RemoteWebDriver(@Optional("localhost") String IP, @Optional("4444") String Port,@Optional("chrome") String browserName) throws Exception {
		System.out.println("@Start Factory RemoteWebDriver");
		System.out.println("Run Script throw Remote Node Connection");

		// 0- search for "selenium standalone url" ->http://localhost:4444/wd/hub
		// 1- Create instance from webDriver to remote host
		// 2- Start StandAlone server using cmd command (java -jar +StandAlone.jar directory)as in snapshot.
		// 3- Other machine have to include (Webdriver.exe, StandAlone.jar)
		// 4- run script ......................... all steps as Appium workflow

		// ::Note:: in case of run in other machine replace (localhost with machine IP Like 192.0.0.172)

		String URL = "http://" + IP + ':' + Port + "/wd/hub";
		// This print for testing only
		System.out.println("The Defined URL is: " + URL);

		switch (browserName.toLowerCase()) {
		case "chrome":
			// get recently opened driver if it exist
			driver = drivers.get("chrome");

			// Create new driver
			if (driver == null) {
			driver = new RemoteWebDriver(new URL(URL), DesiredCapabilities.chrome());
			drivers.put("Chrome", driver);
		}
			break;
			
		case "firefox":
			// get recently opened driver if it exist
			driver = drivers.get("firefox");

			// Create new driver
			if (driver == null) {
			driver = new RemoteWebDriver(new URL(URL), DesiredCapabilities.firefox());
			drivers.put("firefox", driver);
		}
			break;
			
		case "ie":
			// get recently opened driver if it exist
			driver = drivers.get("ie");

			// Create new driver
			if (driver == null) {
			driver = new RemoteWebDriver(new URL(URL), DesiredCapabilities.internetExplorer());
			drivers.put("IE", driver);
		}
			break;
		}
		driver.manage().window().maximize();
		return driver;
	}

	public static WebDriver HeadlessDriver (@Optional("Chrome") String browserName){
		System.out.println("@Start Headless Driver");

		switch (browserName.toLowerCase()) {
		case "chrome":
			driver = drivers.get("chrome");
			
			// Create new driver
			if (driver == null) {
				ChromeOptions Options = new ChromeOptions();
				Options.addArguments("--headless");
				 String WebDriver_Path= "./WebDriver/";
				System.setProperty("webdriver.chrome.driver", WebDriver_Path + "chromedriver.exe");
				//Have to set windows size because default size is small (cannot find the element without This arg value)
				Options.addArguments("--window-size=1920,1080");
			    driver = new ChromeDriver(Options);
			    drivers.put("Chrome", driver);
			}
			break;

		case "firefox":
			driver = drivers.get("firefox");
			
			// Create new driver
			if (driver == null) {
				FirefoxBinary firefoxBinary = new FirefoxBinary();
				 firefoxBinary.addCommandLineOptions("--headless");
				
				 FirefoxOptions firefoxOptions = new FirefoxOptions();
				 firefoxOptions.setBinary(firefoxBinary);
				 driver = new FirefoxDriver(firefoxOptions);
				 drivers.put("Chrome", driver);
			}
			break;
			
//		case "ie":
//			// get recently opened driver if it exist
//			driver = drivers.get("ie");
//
//			// Create new driver
//			if (driver == null) {
//				
////				>>>>>>Need to complete<<<<<<<<
//			drivers.put("ie", driver)
//			}
//			break;			
			}
			driver.manage().window().maximize();
			return driver;
	}
	
	
	public static void closeAllDriver() {
		for (String key : drivers.keySet()) {
//			drivers.get(key).close();
			drivers.get(key).quit();
		}
	}
}
// ===========================================
// Factory driver Guide:
// https://stackoverflow.com/questions/44000441/inject-webdrivers-in-testng-before-test-with-guice
// http://www.testautomationguru.com/selenium-webdriver-dependency-injection-using-guice/

// search Keyword:
// Cannot inject @Test annotated Method [Calc] with [class java.lang.String]
// testng
// Why do we need withInjection in testng
// selenium webdriver factory java

// ::Follow check error with::
// https://stackoverflow.com/questions/45523264/org-testng-testngexception-cannot-inject-test-annotated-method-with-class-jav

// ===========================================