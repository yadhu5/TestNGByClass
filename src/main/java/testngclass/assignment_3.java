package testngclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class assignment_3 {

	WebDriver driver;

	String browser;
//	String url;

	// Method to read config file

	@BeforeClass
	public void readConfig() {
		// object creation of properties file

		Properties prop = new Properties();

		//  read the file: inputstream

		try {
			InputStream input = new FileInputStream("\\src\\main\\java\\config\\config.properties");
			prop.load(input);
			browser = prop.getProperty("browser");
		//	url = prop.getProperty("url");
			
		}

		catch (IOException e) {

			e.printStackTrace();

		}
	}

	@BeforeMethod
	public void init() {

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
			driver = new ChromeDriver();
			
		} else if (browser.equalsIgnoreCase("fireFox")) {
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		driver.get("https://techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	

	}

	// @Test (priority=1)
	public void loginPage() {

		// String rightPage = driver.getTitle();
		// Assert.assertEquals( "Login - iBilling", "Good Page", driver.getTitle());

		WebElement USERNAME_FILED = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD = driver.findElement(By.xpath("//input[@name='password']"));
		WebElement SIGN_IN_BUTTON = driver.findElement(By.xpath("//button[@type='submit']"));

		USERNAME_FILED.sendKeys("demo@techfios.com");
		PASSWORD_FIELD.sendKeys("abc123");
		SIGN_IN_BUTTON.click();

		Assert.assertEquals("Dashboard- iBilling", driver.getTitle());
	}

	@Test(priority = 2)
	public void addCustomer() {

		// Element library

		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGN_IN_BUTTON = By.name("login");
		By CUSTOMER_BUTTON = By.xpath("//span[contains(text(),'Customers')]");
		By ADD_CUSTOMER_BUTTON = By.xpath("//a[contains(text(),'Add Customer')]");
		By FULL_NAME = By.xpath("//input[@name='account']");
		By COMPANY_NAME = By.xpath("//select[@id='cid']");
		By EMAIL_FIELD = By.xpath("//input[@id='email']");
		By PHONE_NUMBER = By.xpath("//input[@id='phone']");
		By ADDRESS = By.xpath("//input[@id='address']");
		By CITY_FIELD = By.xpath("//input[@id='city']");
		By STATE_FIELD = By.xpath("//input[@id='state']");
		By ZIP_CODE = By.xpath("//input[@id='zip']");

		// String

		String loginId = "demo@techfios.com";
		String password = "abc123";
		String name = "QA Company";
		String companyname = "Google";
		String emailaccount = "qa@gmail.com";
		String phoneno = "980-454-8555";
		String address = "249 W thronhill drive";
		String city = "Fort Worth";
		String state = "TX";
		String zipcode = "76464";

		// login page
		driver.findElement(USER_NAME_FIELD).sendKeys(loginId);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(SIGN_IN_BUTTON).click();

		driver.findElement(CUSTOMER_BUTTON).click();

		// explict wait
		waitforElement(driver, 5, ADD_CUSTOMER_BUTTON);
		driver.findElement(ADD_CUSTOMER_BUTTON).click();

		// explict wait
		waitforElement(driver, 7, FULL_NAME);
		
		driver.findElement(FULL_NAME).sendKeys(name);
		driver.findElement(PHONE_NUMBER).sendKeys(phoneno);

		Select sel = new Select(driver.findElement(COMPANY_NAME));
		sel.selectByVisibleText(companyname);

		Random ran = new Random();
		int ranEmail = ran.nextInt(100);

		driver.findElement(EMAIL_FIELD).sendKeys(ranEmail + emailaccount);
		driver.findElement(ADDRESS).sendKeys(address);
		driver.findElement(CITY_FIELD).sendKeys(city);
		driver.findElement(STATE_FIELD).sendKeys(state);
		driver.findElement(ZIP_CODE).sendKeys(zipcode);
		//

	}

	public void waitforElement(WebDriver driver, int timeInSecond, By locator) {
		WebDriverWait wait = new WebDriverWait(driver, timeInSecond);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

//	@AfterMethod
	public void tearDown() {

		driver.close();
		driver.quit();
	}
}
