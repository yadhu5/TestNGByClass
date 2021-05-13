package testngclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class login {

	WebDriver driver;
	String brower = "null";
	String url = "null";
	
	@BeforeClass
	public void readConfig() {
		Properties prop = new Properties();
		//Inorder to read the file - InputStream//BufferReader//FileReader //Scanner
		
		try {
			
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			brower=prop.getProperty("brower");
			System.out.println("Used browser +++++");
			url = prop.getProperty("url");
			System.out.println("Used browser==="+ url);			
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}
	@BeforeMethod
	public void browser() {
		
		if(brower.equalsIgnoreCase("chrome")){
		
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		else if (brower.equalsIgnoreCase("firefox")) {
		System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		}
		
		
		driver.get("https://techfios.com/billing/?ng=admin/");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}
	//@Test (priority=1)
	public void loginpage() throws InterruptedException {
		
		Assert.assertEquals(driver.getTitle(),"Login - iBilling", "Wrong Page!");
		
		WebElement USERNAME_FILED= driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD =  driver.findElement(By.xpath("//input[@name='password']"));
		WebElement SIGN_IN_BUTTON = driver.findElement(By.xpath("//button[@type='submit']"));
		
		
		String LOGINID="demo@techfios.com";
		String PASSWORD="abc123";
		
		USERNAME_FILED.sendKeys(LOGINID);
		PASSWORD_FIELD.sendKeys(PASSWORD);
		SIGN_IN_BUTTON.click();
		
		WebElement DASHBOARD_TITLE = driver.findElement(By.xpath("h2[contains(Text(),'Dashboard')]"));
		Assert.assertEquals(DASHBOARD_TITLE,"Dashboard","Wrong Page!");
		
	}
	@Test  (priority=2)
	public void addCustomer() {
		Assert.assertEquals(driver.getTitle(),"Login - iBilling", "Wrong Page!");
		
		WebElement USERNAME_FILED= driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD =  driver.findElement(By.xpath("//input[@name='password']"));
		WebElement SIGN_IN_BUTTON = driver.findElement(By.xpath("//button[@type='submit']"));
		
		
		String LOGINID="demo@techfios.com";
		String PASSWORD="abc123";
		
		//Test data or mock data
		
		String fullName = " Test January";
		String CompanyName="Google";
		String email = "techfios@gmail.com";
		String phone	= "2121515454";
		
		
		USERNAME_FILED.sendKeys(LOGINID);
		PASSWORD_FIELD.sendKeys(PASSWORD);
		SIGN_IN_BUTTON.click();
		
		WebElement CUSTOMER_BUTTON_ELEMENT = driver.findElement(By.xpath("//span[contains(text(),'Customers')]"));
		CUSTOMER_BUTTON_ELEMENT.click();
		
		WebDriverWait wait1 = new WebDriverWait(driver,5);
		wait1.until(ExpectedConditions.visibilityOf(CUSTOMER_BUTTON_ELEMENT ));
		
		WebElement ADD_CUSTOMER_BUTTON = driver.findElement(By.xpath("//ul[@id='side-menu']/descendant::li[7]/child::ul/child::li/a"));
		ADD_CUSTOMER_BUTTON.click();
		
		

		
	
	}
}
