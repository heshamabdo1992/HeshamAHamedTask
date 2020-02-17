package Utils;


import java.io.File;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

		public static WebDriver driver = null;

	public static String getTestCaseName(String sTestCase)throws Exception{

		String value = sTestCase;

		try{

			int posi = value.indexOf("@");

			value = value.substring(0, posi);

			posi = value.lastIndexOf(".");	

			value = value.substring(posi + 1);

			return value;

				}catch (Exception e){

			Log.error("Class Utils | Method getTestCaseName | Exception desc : "+e.getMessage());

			throw (e);

					}

			}



	}