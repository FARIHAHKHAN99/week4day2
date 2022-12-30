package week4day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Snapdeal {

		public static void main(String[] args) throws InterruptedException, IOException {
			
			ChromeDriver driver = new ChromeDriver();

			// Launch https://www.snapdeal.com/
			driver.get("https://www.snapdeal.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.findElement(By.xpath("//button[@id='pushDenied']")).click();

			// Go to Mens Fashion
			WebElement men = driver.findElement(By.xpath("(//span[@class='catText'])[1]"));

			Actions builder = new Actions(driver);
			builder.moveToElement(men).perform();

			// Go to Sports Shoes
			driver.findElement(By.xpath("//span[text()='Sports Shoes']/parent::a")).click();
			builder.scrollByAmount(0, 200).perform();

			// Get the count of the sports shoes
			List<WebElement> sportShoes = driver.findElements(By.xpath("//picture"));
			int sSize = sportShoes.size();
			System.out.println(sSize);

			// Click Training shoes
			driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();

			// Sort by Low to High
			driver.findElement(By.className("sort-label")).click();
			driver.findElement(By.xpath("//div[contains(@class,'sorting-sec')]/ul/li[2]")).click();

			// Select the price range (900-1200)
			WebElement fromVal = driver.findElement(By.name("fromVal"));
			fromVal.clear();
			fromVal.sendKeys("900");
			WebElement toVal = driver.findElement(By.name("toVal"));
			toVal.clear();
			toVal.sendKeys("1200");

			// Filter with color Navy
			WebElement navy = driver.findElement(By.xpath("//div[contains(@class,'sdCheckbox')][5]"));
			builder.scrollByAmount(0, 400).perform();
			Thread.sleep(2000);
			navy.click();
			Thread.sleep(2000);
			
			// Mouse Hover on first resulting Training shoes
			builder.scrollByAmount(0, 400).perform();
			
			WebElement firstElement = driver.findElement(By.xpath("//img[contains(@title,'Navy Training Shoes')]"));
			Thread.sleep(2000);
			
			Actions move = new Actions(driver);
			move.moveToElement(firstElement).perform();
			
			// click QuickView button
			driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
			
			// Print the cost and the discount percentage
			WebElement cost = driver.findElement(By.className("payBlkBig"));
			System.out.println(cost.getText());
			
			WebElement percentage = driver.findElement(By.className("percent-desc"));
			System.out.println(percentage.getText());
			
			// Take the snapshot of the shoes.
			File srcFile=driver.getScreenshotAs(OutputType.FILE);
			File snap= new  File("./snaps/snapdeal.jpg");
			FileUtils.copyFile(srcFile, snap);
			
			driver.close();
			driver.quit();

		}

	}
