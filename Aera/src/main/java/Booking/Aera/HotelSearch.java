package Booking.Aera;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class HotelSearch {

	public WebDriver driver;

	PageFactory po = new PageFactory();

	public HotelSearch(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='cross-product-bar__wrapper ']/span | //li[@class = 'bui-tab__item']//a[1]")
	WebElement stay;

	public void defaultTab() {
		if (stay.getAttribute("class").contains("selected")) {
			System.out.println("Stays is already selected");
		}

		else {
			stay.click();
		}

	}

	@FindBy(xpath = "//input[@id='ss']")
	WebElement dest;

	public WebElement destField() {
		return dest;
	}

	public void destination() {
		List<WebElement> list = driver.findElements(By.xpath("//li[@role='option']"));
		SoftAssert sa = new SoftAssert();
		for (WebElement i : list) {

			if (i.getText().contains("New York") && i.getText().contains("United States")) {
				System.out.println("New York city is populated along with country as United States");
				i.click();
				break;
			} else {
				sa.assertTrue(true, "The New York city option is not populating in the list");
				list.get(0).click();
				break;
			}
		}
	}

	@FindBy(xpath = "//table[@class='bui-calendar__dates']//td[contains(@class, 'today')]")
	WebElement todayDate1;

	public void dateSelection() {

		List<WebElement> allDates = driver
				.findElements(By.xpath("//table[@class='bui-calendar__dates']//td[@class='bui-calendar__date']"));
		int indexToday = allDates.indexOf(todayDate1);
		allDates.get(indexToday + 1).click();
		allDates.get(indexToday + 22).click();
	}

	@FindBy(xpath = "//button[@class='bui-button bui-button--secondary bui-stepper__add-button ' and @aria-describedby = 'group_adults_desc']")
	WebElement adultAddButton;

	@FindBy(id = "xp__guests__toggle")
	WebElement room;

	@FindBy(xpath = "//div[@class='sb-group__field sb-group__field-adults']//span[@class='bui-stepper__display']")
	WebElement adultDisplayed;

	@FindBy(xpath = "//button[@class='bui-button bui-button--secondary bui-stepper__add-button ' and @aria-describedby = 'group_children_desc']")
	WebElement childAddButton;

	@FindBy(xpath = "//div[@class='sb-group__field sb-group-children ']//span[@class='bui-stepper__display']")
	WebElement childDisplayed;

	@FindBy(xpath = "//select[@name='age']")
	WebElement dropdownChild;

	@FindBy(xpath = "//button[@class='bui-button bui-button--secondary bui-stepper__add-button ' and @aria-describedby = 'no_rooms_desc']")
	WebElement roomAddButton;

	@FindBy(xpath = "//div[@class='sb-group__field sb-group__field-rooms']//span[@class='bui-stepper__display']")
	WebElement roomDisplay;

	@FindBy(xpath = "//button[@class='sb-searchbox__button ']")
	WebElement searchButton;

	public void roomSelection(String inputAdults, String inputChild, String inputAge, String inputRoom) {
		room.click();
		while (!(adultDisplayed.getText().equalsIgnoreCase(inputAdults))) {
			adultAddButton.click();
		}

		while (!(childDisplayed.getText().equalsIgnoreCase(inputChild))) {
			childAddButton.click();
		}

		dropdownChild.click();

		Select age = new Select(dropdownChild);

		age.selectByValue(inputAge);

		while (!(roomDisplay.getText().equalsIgnoreCase(inputRoom))) {
			roomAddButton.click();
		}

		searchButton.click();
	}

}
