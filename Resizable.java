package week4day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Resizable {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver = new ChromeDriver();

		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		Actions drag = new Actions(driver);
		drag.clickAndHold(element).moveByOffset(100, 80).release().perform();
		
	}

}

