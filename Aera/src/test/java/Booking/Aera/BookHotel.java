package Booking.Aera;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import Booking.Aera.Base;

public class BookHotel extends Base{

	
	@BeforeTest
	public void openBrowser() throws IOException
	{
		driver = initiateDriver();
		
		driver.get("https://www.booking.com/");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.navigate().refresh();
		
	}
	
	@Test
	public void selectHotel() throws InterruptedException {
		
		HotelSearch hs = new HotelSearch(driver);
		hs.defaultTab();
		hs.destField().sendKeys("New");
		hs.destination();
		
		hs.dateSelection();
		
		String inputAdults = "2";
		String inputChild = "1";
		String inputAge = "2";
		String inputRoom = "1";
		hs.roomSelection(inputAdults,inputChild,inputAge,inputRoom);
		
		SearchResult sr = new SearchResult(driver);
		
		sr.getProperties();
		
		String inputStar = "3";
		sr.ratingFilter(inputStar);
		
		sr.priceFilter();
		
		sr.afterFilterCount();
		
		sr.hotelPriceDisplay();
		
	}

}
