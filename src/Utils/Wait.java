package Utils;

//::Reference:: https://www.ontestautomation.com/using-wrapper-methods-for-better-error-handling-in-selenium/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wait {

//	WebDriver driver;
////	WebDriverWait wait;
//
//	public Wait(WebDriver driver) {
//		// super();
//		this.driver = driver;
////		wait = new WebDriverWait(driver, 60, 250);
//	}
	
	
	public static void Presence(WebDriver driver,By by) {
		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static void visibiability(WebDriver driver, By by) {
		(new WebDriverWait( driver, 60, 10)).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public static void clickable(WebDriver driver, By by) {
		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.elementToBeClickable(by));
		driver.findElement(by).click();
	}

	// ======= Waiting for attribute Contains =====

	public static void TextContains(WebDriver driver, By by, String partOfTextToAppear) {
		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.textToBe(by, partOfTextToAppear));
		// OR
		// (new WebDriverWait(driver, 60,10)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//h2[contains(.,'"+partOfTextToAppear+ "')]")));
	}

	public static void AttributeContains(WebDriver driver, By by, String partOfTextToAppear) {

		// ======= Using attribute "innerText" ====== get text without tags
		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.attributeContains(by, "innerText", partOfTextToAppear));

		// OR
		// =======Using attribute "textContent" ======
		// (new WebDriverWait(driver, 60,10)).until(ExpectedConditions.attributeContains(by,"textContent",partOfTextToAppear));

		// OR
		// =======Using attribute "value" ======
		// (new WebDriverWait(driver, 60,10)).until(ExpectedConditions.attributeContains(by,"value", partOfTextToAppear));
	}

	public static void attributeNotEmpty(WebDriver driver, By by, String AttributeName) {

		WebElement elementx = driver.findElement(by);
		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.attributeToBeNotEmpty(elementx, AttributeName));
	}

	public static void numberToBe(WebDriver driver, By by, int Number) {

		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.numberOfElementsToBe(by, Number));
	}

	public static void numberLessThat(WebDriver driver, By by, int Number) {
		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.numberOfElementsToBeLessThan(by, Number));

	}

	public static void numberMoreThat(WebDriver driver, By by, int Number) {
		(new WebDriverWait(driver, 60, 10)).until(ExpectedConditions.numberOfElementsToBeMoreThan(by, Number));
	}
}
