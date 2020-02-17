package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class RegisterPage {
	WebDriver driver;
	WebDriverWait wait;

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 60, 250);
	}


	// Locators
	By Text_FirstName_Loca = By.xpath("//*[@name='firstname']");
	By Text_LastName_Loca = By.xpath("//*[@name='lastname']");
	By Text_MobileNum_Loca = By.xpath("//*[@name='phone']");
	By Text_Email_Loca = By.xpath("//*[@name='email']");
	By Text_Pass_Loca = By.xpath("//*[@name='password']");
	By Text_ConfirmPass_Loca = By.xpath("//*[@name='confirmpassword']");
	By Btn_Signup_Loca = By.xpath("//button[@class='signupbtn btn_full btn btn-success btn-block btn-lg']");

	public void SetFirstName(WebDriver driver, String FirstName)
	{
		//Enter Fist Name
				driver.findElement(Text_FirstName_Loca).clear();
				driver.findElement(Text_FirstName_Loca).sendKeys(FirstName);
	}
	
	public void SetLastName(WebDriver driver, String LastName)
	{
		//Enter Last Name
				driver.findElement(Text_LastName_Loca).clear();
				driver.findElement(Text_LastName_Loca).sendKeys(LastName);
	}

	public void SetMobile(WebDriver driver, String Mobile)
	{
		//Enter Mobile 	
				driver.findElement(Text_MobileNum_Loca).clear();
				driver.findElement(Text_MobileNum_Loca).sendKeys(Mobile);
	}
	
	public void SetEmail(WebDriver driver, String Email)
	{
		//Enter Email 	
				driver.findElement(Text_Email_Loca).clear();
				driver.findElement(Text_Email_Loca).sendKeys(Email);
	}
	
	public void SetPassword(WebDriver driver, String Password)
	{
		//Enter Password
				driver.findElement(Text_Pass_Loca).clear();
				driver.findElement(Text_Pass_Loca).sendKeys(Password);
	}
	
	public void SetConfirmPassword(WebDriver driver, String ConfirmPassword)
	{
		//Enter Confirm Password
				driver.findElement(Text_ConfirmPass_Loca).clear();
				driver.findElement(Text_ConfirmPass_Loca).sendKeys(ConfirmPassword);
	}
	
	public void ClickonSignup(WebDriver driver)
	{
		//Wait for visibiability Signup button
				Utils.Wait.visibiability(driver, Btn_Signup_Loca);
		//Click on  Signup button
				driver.findElement(Btn_Signup_Loca).click();
	}
	
	public void signup(WebDriver driver, String FirstName, String LastName,
			String Mobile, String Email,
			String Password, String ConfirmPassword
			) throws InterruptedException {
//Enter Fist Name
		SetFirstName(driver,FirstName);
//Enter Last Name
		SetLastName(driver,LastName);
//Enter Mobile 	
		SetMobile(driver,Mobile);
//Enter Email 	
		SetEmail(driver,Email);
//Enter Password
		SetPassword(driver,Password);
//Enter Confirm Password
		SetConfirmPassword(driver,ConfirmPassword);

//Click on  Signup button
		ClickonSignup(driver);
		Thread.sleep(3000);
	}
	
	
	By text_Username_loca=By.xpath("//div[2]/div[1]/div[1]/div/div/div[1]/div/div[2]/h3");
	public void assert_signup_success(String username)
	{
		// assert after sign up success , check that user name is displayed
		Assert.assertTrue(driver.findElement(Btn_Signup_Loca).getText().contains(username));
	}
	 
	

	 
	 

}
