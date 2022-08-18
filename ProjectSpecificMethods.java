package independenceday_marathon;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ProjectSpecificMethods {
	RemoteWebDriver driver;
	String excelFileName = "";
	String browserName = "";
	
	@BeforeMethod
	@Parameters({"url","username","password"})
	public void setup(String url, String username, String password)
	{
		if(browserName.equals("chrome"))
		{
			// To Download and set the path 
			WebDriverManager.chromedriver().setup();
			
			//To disable Notifications
			ChromeOptions notif = new ChromeOptions();
			notif.addArguments("--disable-notifications");

			// To Launch the chromebrowser
			 driver = new ChromeDriver(notif);
		}
		else
		{
			// To Download and set the path 
			WebDriverManager.edgedriver().setup();

			//To disable notifications
			EdgeOptions notif = new EdgeOptions();
			notif.addArguments("--disable-notifications");

			// To Launch the edgebrowser
		    driver = new EdgeDriver(notif);
		}
		
		//To Launch the URL
		driver.get(url);

		//To Maximise the window
		driver.manage().window().maximize();

		//To add implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		//To Enter the username as " psjmythily@gmail.com "
		driver.findElement(By.id("username")).sendKeys(username);

		//To  Enter the password as " Mythily@25 "
		driver.findElement(By.id("password")).sendKeys(password);

		//To click on the login button
		driver.findElement(By.id("Login")).click();

		//To Click on toggle menu button from the left corner
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();

		//To Click View All  
		driver.findElement(By.xpath("//button[text()='View All']")).click();
	}
			
	@AfterMethod
	public void postCondition()
	{
		driver.close();
	}

}
