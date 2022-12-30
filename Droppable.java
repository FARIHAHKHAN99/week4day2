package week4day2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Droppable {

			public static void main(String[] args) {
				
				
			ChromeDriver driver = new ChromeDriver();

				driver.get("https://jqueryui.com/droppable/");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

				driver.switchTo().frame(0);
				WebElement element = driver.findElement(By.id("draggable"));
				Actions drag = new Actions(driver);
				drag.dragAndDropBy(element, 150, 20).perform();
				driver.close();
			}
	}


 
