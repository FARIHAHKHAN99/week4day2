package week4day2;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Salesforce {

	public static void main(String[] args) throws InterruptedException {
			
			ChromeOptions option=new ChromeOptions();
			option.addArguments("--disable-notifications");
			
			ChromeDriver driver = new ChromeDriver(option);
			driver.get("https://login.salesforce.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys("hari.radhakrishnan@qeagle.com");
			driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Testleaf$321");
			driver.findElement(By.xpath("//input[@id='Login']")).click();
			
			driver.findElement(By.xpath("//span[text()='Learn More']")).click();
			
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> list=new LinkedList<String>(windowHandles);
			driver.switchTo().window(list.get(1));
			
			driver.findElement(By.xpath("//button[text()='Confirm']")).click();
			String title = driver.getTitle();
			System.out.println(title);
			
			driver.close();
			driver.quit();
			
		}

	}

