package Booking.Aera;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SearchResult {

	public WebDriver driver;
	
	PageFactory po = new PageFactory();
	
	public SearchResult(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	
	@FindBy(xpath = "//div[@class ='sr_header--title']//h1")
	WebElement properties;
	
	@FindBy(xpath = "//div[@id='filter_class']//div[@class = 'filteroptions']//a")
	List<WebElement> ratingsList;
	
	@FindBy(linkText = "Price (lowest first)")
	WebElement price;
	
	@FindBy(xpath = "//div[@class ='sr_header--title']//h1")
	WebElement filteredProperties;
	
	@FindBy(xpath = "(//div[@class = 'sr-hotel__title-wrap'])[1]")
	WebElement hotel;
	
	@FindBy(xpath = "(//div[contains(@class,'price-display__value')])[1]")
	WebElement hotelprice;	
	
	public void getProperties()
	{
		System.out.println("No. of properties found is: " + properties.getText());
	}
	
	public void ratingFilter(String inputStar)
	{
		Iterator<WebElement> startIterator = ratingsList.iterator();

		while (startIterator.hasNext()) {
			WebElement star = startIterator.next();
			if (star.getText().contains(inputStar)) {
				star.click();
				break;
			}
		}
	}
	
	public void priceFilter() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(price));

		price.click();

		Thread.sleep(4000);
	}
	
	public void afterFilterCount()
	{
		String numOfProperties = filteredProperties.getText();
		numOfProperties = numOfProperties.replaceAll("[^\\d]", " ");
		numOfProperties = numOfProperties.trim();
		System.out.println("No of properties after filter: " + numOfProperties);

		if (numOfProperties.equals("0")) {
			System.out.println("No single hotel is found after applying the given search criteria");
			Assert.assertEquals(false, true);
		}

	}
	
	public void hotelPriceDisplay()
	{
		String hotelName = hotel.getText();

		String hotelNameOnly[] = hotelName.split("Opens");

		String priceStr = hotelprice.getText();

		String hotelPriceOnly[] = priceStr.split(" ");

		System.out.println("Hotel name: " + hotelNameOnly[0] + " Price: " + hotelPriceOnly[1]);
	}
	

	}
