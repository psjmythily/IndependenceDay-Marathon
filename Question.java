package independenceday_marathon;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import week6.day1.ExcelData;

public class Question extends ProjectSpecificMethods{
	
	/*
	 * 01) Launch https://login.salesforce.com/ 
	 * 02) Login to Salesforce with your username and password 
	 * 03) Click on the App Launcher (dots) 
	 * 04) Click View All
	 * 05) Type Content on the Search box 
	 * 06) Click Content Link 
	 * 07) Click on Chatter Tab 
	 * 08) Verify Chatter title on the page 
	 * 09) Click Question tab 
	 * 10)Type Question with data (coming from excel) 
	 * 11) Type Details with data(coming from excel)
	 *  12) Click Ask 
	 *  13) Confirm the question appears 
	 *  14) Close the browser
	 */
@DataProvider(name ="question")
	
	public String[][] fetchData() throws IOException
	{
		String[][] data = ExcelData1.readData("Question");
		return data;
	}

	@BeforeTest
	public void setup()
	{
		browserName="chrome";
		excelFileName="";
	}
	
    @Test(dataProvider="question")
	public void question(String Question, String Details) throws InterruptedException {
		
		// Type Content on the Search box 
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("Content");
		
		//Click Content Link 
		driver.findElement(By.xpath("//mark[text()='Content']")).click();
		
		//Click on Chatter Tab
		WebElement chatter = driver.findElement(By.xpath("//span[text()='Chatter']"));
		driver.executeScript("arguments[0].click();", chatter);
		
		//Verify Chatter title on the page
		Thread.sleep(8000);
		String title = driver.getTitle();
		System.out.println(title);
		if(title.contains("Chatter Home"))
		{
			System.out.println("Chatter Home Salesforce page is displayed");
		}
		else
		{
			System.out.println("Wrong page is displayed");
		}
		
		//Click Question tab 
		driver.findElement(By.xpath("//span[text()='Question']")).click();
		
		//Type Question with data
		driver.findElement(By.xpath("//textarea[@class='cuf-questionTitleField textarea']")).sendKeys(Question);
		
		//Type Details with data
		driver.findElement(By.xpath("//div[@class='ql-editor ql-blank slds-rich-text-area__content slds-text-color_weak slds-grow']")).sendKeys(Details);
		
		//Click Ask 
		driver.findElement(By.xpath("//button[text()='Ask']")).click();
		
		//Confirm the question appears 
		String text = driver.findElement(By.xpath("//span[text()='"+Question+"']")).getText();
		if(text.contains(Question))
		{
			System.out.println("Asked question appears in the screen is confirmed");
		}
		else
		{
			System.out.println("Asked question is not appeared in the screen");
		}
		
		//Close the browser
		driver.close();
	}

}
