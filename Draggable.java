package week4day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Draggable {

	public static void main(String[] args) {

		ChromeDriver driver = new ChromeDriver();

		driver.get("https://jqueryui.com/draggable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.switchTo().frame(0);
		WebElement element = driver.findElement(By.id("draggable"));
		Actions drag = new Actions(driver);
		drag.dragAndDropBy(element, 60, 100).perform();
		
	}

}
