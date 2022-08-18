package independenceday_marathon;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import week6.day1.ExcelData;

public class Individuals extends ProjectSpecificMethods{

	/*
	 * 01) Launch https://login.salesforce.com/
	 *  02) Login to Salesforce with your username and password 
	 * 03) Click on the App Launcher (dots) 
	 * 04) Click View All
	 * 05) Type Individuals on the Search box 
	 * 06) Click Individuals Link 
	 * 07) Click New 
	 * 08) Select Salutation with data (coming from excel) 
	 * 09) Type Last Name
	 * 10) Click Save 
	 * 11) Click on the App Launcher (dots) 
	 * 12) Click View All 
	 * 13) Type Customers on the Search box
	 *  14) Click Customers Link 
	 *  15) Click New 
	 *  16) Type the same name provided in step 8 and confirm it appears
	 *   17) Close the browser
	 */

@DataProvider(name ="individuals")
	
	public String[][] fetchData() throws IOException
	{
		String[][] data = ExcelData1.readData("Individual");
		return data;
	}

	@BeforeTest
	public void setup()
	{
		browserName="edge";
		excelFileName="";
	}
	
    @Test(dataProvider = "individuals")
	public void individual(String Salutations, String LastName) throws InterruptedException {

		//Type Individuals on the Search box 
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Individuals");

		//Click Individuals Link 
		driver.findElement(By.xpath("//mark[text()='Individuals']")).click();

		//Click New 
		driver.findElement(By.xpath("//div[text()='New']")).click();

		//Select Salutation with data
		driver.findElement(By.xpath("(//a[@class='select'])[1]")).click();
		driver.findElement(By.xpath("//a[@title='"+Salutations+"']")).click();

		//Type Last Name
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(LastName);

		//Click Save 
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();

		//To Click on toggle menu button from the left corner
		try
		{
			 driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		}
		catch(Exception e) {
			System.out.println("ElementClickInterceptedException is handled");
			WebElement menu = driver.findElement(By.xpath("//div[@class='slds-icon-waffle']"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(menu)).click();
		}
		
		//To Click View All  
		driver.findElement(By.xpath("//button[text()='View All']")).click();

		//Type Customers on the Search box
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys("Customers");

		// Click Customers Link 
		Thread.sleep(6000);
		driver.findElement(By.xpath("//mark[text()='Customers']")).click();

		//Click New 
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@title='New'])[1]")).click();
		
		//Type the same name provided in step 8 and confirm it appears
		driver.findElement(By.xpath("//input[@title='Search Individuals']")).sendKeys(LastName);
		WebElement element = driver.findElement(By.xpath("//div[@title='"+LastName+"']"));
		String value = element.getAttribute("title");
		if(value.contains(LastName))
		{
			System.out.println("Same name appears");
		}
		else
		{
			System.out.println("Wrong name appears");
		}
	}

}
