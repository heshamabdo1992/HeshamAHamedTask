package Pages;

import javax.mail.Message;

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

	// Verify Elements on the page
	public void verifyElemntsOnPageTest(WebDriver driver)
	{
		try {
				driver.findElement(Text_FirstName_Loca).isDisplayed();
				System.out.println("FirstName textbox is displayed");
		}
		catch(Exception e)
		{
			System.out.println("FirstName textbox is not displayed");

		}
		try {
				driver.findElement(Text_LastName_Loca).isDisplayed();
				System.out.println("LastName textbox is  displayed");

		}		catch(Exception e)
		{
			System.out.println("LastName textbox is not displayed");

		}
		try {
				driver.findElement(Text_MobileNum_Loca).isDisplayed();
				System.out.println("Mobile textbox is  displayed");

		}		catch(Exception e)
		{
			System.out.println("Mobile textbox is not displayed");

		}
		try {
				driver.findElement(Text_Email_Loca).isDisplayed();
				System.out.println("Email textbox is  displayed");
}
		catch(Exception e)
		{
			System.out.println("Email textbox is not displayed");

		}
		try {
				driver.findElement(Text_Pass_Loca).isDisplayed();
				System.out.println("Password textbox is  displayed");
}
		catch(Exception e)
		{
			System.out.println("Password textbox is not displayed");

		}
		try {
				driver.findElement(Text_ConfirmPass_Loca).isDisplayed();
				System.out.println("Confirm Password textbox is  displayed");
}
		catch(Exception e)
		{
			System.out.println("Confirm Password textbox is not displayed");

		}
		try {
				driver.findElement(Btn_Signup_Loca).isDisplayed();
				System.out.println("Signup button is displayed");
}
		catch(Exception e)
		{
			System.out.println("Signup button is not displayed");

		}


	}
	
	
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
	
	// Assert tooltips appear when user enter empty 
	public void asserttooltipnotappear(WebDriver driver) {
		
		String toolTipText="";
        try {
    		Utils.Wait.visibiability(driver,By.xpath("//*[@value='Please fill out this field.']"));

    		toolTipText = driver.findElement(By.xpath("//*[.='Please fill out this field.']")).getAttribute("title");

        	Assert.assertFalse(toolTipText.contains("Please fill out this field"));
      //  	System.out.println(toolTipText+"\n toolTip not appear");

        }catch (Exception e) {
        	System.out.println(toolTipText+"\ntoolTip appear");
        }	
	}
	
	
	By Alertmessage= By.xpath("//*[@class='alert alert-danger']");
	// Assert email error message when user enter invalid email 
	public void assert_email_error_notappear(WebDriver driver) {
		String Msg="";
        try {
    		Utils.Wait.visibiability(driver, Alertmessage);
    		 Msg=driver.findElement(Alertmessage).getText();
        	Assert.assertFalse(Msg.contains("The Email field must contain a valid email address"));
        //	System.out.println(Msg+"\nThe Email field  contain a valid email address");

        }catch (Exception e) {
        	System.out.println(Msg+"\nThe Email field doesn't contain a valid email address");
        }	
        }
	
	
	
	// Assert email error message when user enter invalid email 
	public void assert_emailExists_error_notappear(WebDriver driver) {
		String Msg="";
        try {
    		Utils.Wait.visibiability(driver, Alertmessage);

    		 Msg=driver.findElement(Alertmessage).getText();
        	Assert.assertFalse(Msg.contains("Email Already Exists"));
        //	System.out.println(Msg+"\nEmail is not Exists");

        }catch (Exception e) {
        	System.out.println(Msg+"\nEmail Already Exists");
        }	
        }

	
	// Assert password policy  message when user enter passward not match the policy  
	public void assert_pwd_policy_msg_notappear(WebDriver driver) {
		String Msg="";
        try {
    		Utils.Wait.visibiability(driver, Alertmessage);

    		 Msg=driver.findElement(Alertmessage).getText();

        	Assert.assertFalse(Msg.contains("The Password field must be at least 6 characters in length"));
       // 	System.out.println(Msg+"\nThe Passward  match the policy");

        }catch (Exception e) {
        	System.out.println(Msg+"\nThe Passward not match the policy");
        }
	}
	
	// Assert password policy  message when user enter confirm passward not match the  password  
	public void assertpwdmatch(WebDriver driver) {
		String Msg="";

        try {
    		Utils.Wait.visibiability(driver, Alertmessage);

    		 Msg=driver.findElement(Alertmessage).getText();
        	Assert.assertFalse(Msg.contains("Password not matching with confirm password"));
      //  	System.out.println(Msg+"\nPassword  matching with confirm password");

        }catch (Exception e) {
        	System.out.println(Msg+"\nPassword not matching with confirm password");
        }
	}
	
	
	By text_Username_loca=By.xpath("//div[2]/div[1]/div[1]/div/div/div[1]/div/div[2]/h3");
	public void assert_signup_success(String username)
	{
		Utils.Wait.visibiability(driver, text_Username_loca);

		// assert after sign up success , check that user name is displayed
		Assert.assertTrue(driver.findElement(text_Username_loca).getText().contains(username));
	}
	 
	

	 
	 

}
