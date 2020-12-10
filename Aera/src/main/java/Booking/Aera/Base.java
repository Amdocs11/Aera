package Booking.Aera;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Base {

	public WebDriver driver;	
	
	public WebDriver initiateDriver() throws IOException
	
	{
		
		Properties pro = new Properties();
		FileInputStream fis = new FileInputStream("C:\\new Scripts\\Aera\\src\\main\\java\\data.properties");
		pro.load(fis);
		
		String browserName = pro.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "C:\\new Scripts\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "C:\\new Scripts\\geckodriver-v0.27.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		return driver;

			
	}
	
	
	
	
}
