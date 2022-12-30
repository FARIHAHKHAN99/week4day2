package week4day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import com.github.dockerjava.api.model.Driver;

public class Myntra {

		public static void main(String[] args) throws InterruptedException, IOException {

			
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--disable-notifications");
			
			ChromeDriver driver = new ChromeDriver(option);

			driver.get("https://www.myntra.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			// Mouse hover on MeN
			WebElement men = driver.findElement(By.xpath("//a[@href='/shop/men']"));
			Actions builder = new Actions(driver);
			builder.moveToElement(men).perform();

			// Click Jackets
			driver.findElement(By.xpath("//a[@href='/men-jackets']")).click();

			// Find the total count of item
			String titleCount = driver.findElement(By.className("title-count")).getText();
			System.out.println(titleCount);
			
			// Check jackets
			driver.findElement(By.xpath("//input[@value='Jackets']//parent::label")).click();

			// Click + More option under BRAND
			driver.findElement(By.className("brand-more")).click();

			// Type Duke and click checkbox
			driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@value='Duke']//parent::label")).click();
			Thread.sleep(2000);
			
			// Close the pop-up x
			driver.findElement(By.xpath("//span[contains(@class,'FilterDirectory-close')]")).click();

			// Confirm all the Coats are of brand Duke
			List<WebElement> brand = driver.findElements(By.className("product-brand"));
			for (int i = 0; i < brand.size(); i++) {
				String element = brand.get(i).getText();

				if (element.equals("Duke")) {
					System.out.println("Duke");
				}
			}
			
			// Sort by Better Discount
			driver.findElement(By.className("sort-sortBy")).click();
			driver.findElement(By.xpath("//input[@value='discount']//parent:: label")).click();
			
			// Find the price of first displayed item
			System.out.println(driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText());
			
			// Click on the first product
			driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])/ancestor:: a")).click();
			
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> ls=new ArrayList<String>(windowHandles);
			driver.switchTo().window(ls.get(1));
			
			// Take a screen shot
			Thread.sleep(2000);
			File srcFile=driver.getScreenshotAs(OutputType.FILE);
			File scrsht= new  File("./snaps/myntra.jpg");
			FileUtils.copyFile(srcFile, scrsht);
			
			// Click on WishList Now
			driver.findElement(By.xpath("//span[text()='WISHLIST']//parent:: div")).click();
			
			driver.close();
			driver.quit();
		}

	}

