package week4day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Nyka {

		public static void main(String[] args) throws InterruptedException {
			
			ChromeDriver driver = new ChromeDriver();
			driver.get("https://www.nykaa.com");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

			// Mouseover the brands
			WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
			Actions nykaa = new Actions(driver);
			nykaa.moveToElement(brands).perform();

			// Type the brand name
			driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
			Thread.sleep(2000);

			// click on the brand name
			driver.findElement(By.xpath("(//div[@class='css-ov2o3v']/a)[1]")).click();

			// get the title
			driver.getTitle();
		   if (driver.getTitle().contains("L'Oreal Paris")) {
				System.out.println("L'Oreal Paris");
			} 
		   else {
				System.out.println("Try the correct one");
			}

			// Click sort By and select customer top rated
			WebElement sortBy = driver.findElement(By.xpath("//button[@class=' css-n0ptfk']"));
			sortBy.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[contains(@for,'radio_customer')]")).click();

			// Click Category and click Hair->Click haircare->Shampoo
			WebElement category = driver.findElement(By.xpath("//span[text()='Category']//parent:: div"));
			Thread.sleep(2000);
			category.click();
            driver.findElement(By.xpath("//span[text()='Hair']")).click();
			driver.findElement(By.xpath("(//span[text()='Hair Care'])[2]")).click();
			driver.findElement(By.xpath("//span[text()='Shampoo']//parent:: div")).click();
			Thread.sleep(2000);

			// Click->Concern->Color Protection
			WebElement concern = driver.findElement(By.xpath("//span[text()='Concern']//parent:: div"));
					// driver.executeScript("arguments[0].scrollIntoView()", concern);
			Thread.sleep(1500);
			concern.click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//span[contains(text(),'Color')])[2]//ancestor::label")).click();

			// check whether the Filter is applied with Shampoo
			WebElement checkFilter = driver.findElement(By.xpath("//span[text()='Shampoo']//parent:: div"));
			String filter = checkFilter.getText();
			if (filter.contains("Shampoo")) {
				System.out.println("Shampoo");
			} 
			else {
				System.out.println("Try the correct one");
			}
			Thread.sleep(2000);
			
			// Click on L'Oreal Paris Colour Protect Shampoo
			driver.findElement(By.xpath("//div[contains(text(),'Protect')]")).click();

			// GO to the new window and select size as 175ml
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> windows = new ArrayList<String>(windowHandles);
			driver.switchTo().window(windows.get(1));

			WebElement size = driver.findElement(By.xpath("//select"));
			Select sel = new Select(size);
			sel.selectByIndex(1);

			// Print the MRP of the product
			WebElement mrp = driver.findElement(By.className("css-1jczs19"));
			System.out.println(mrp.getText());

			// Click on ADD to BAG
			driver.findElement(By.xpath("(//span[text()='Add to Bag'])[1]/parent::button")).click();
			Thread.sleep(2000);
			
			// Go to Shopping Bag
			driver.findElement(By.xpath("//span[@class='cart-count']//ancestor::button")).click();
			Thread.sleep(2000);
			
			// Print the Grand Total amount
			WebElement iframe = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
			driver.switchTo().frame(iframe);
			
			WebElement grandTotal = driver.findElement(By.xpath("//span[contains(@class,'css-1um1mkq')]"));
			String grdtotal = grandTotal.getText();
			System.out.println(grdtotal);

			// Click Proceed
			driver.findElement(By.xpath("//span[text()='Proceed']//ancestor::button")).click();

			// Click on Continue as Guest
			driver.findElement(By.xpath("//button[contains(text(),'Continue as guest')]")).click();
			driver.switchTo().parentFrame();
			
			// Check if this grand total is the same
			driver.findElement(By.xpath("//img[contains(@src,'Clear.svg')]")).click();
			WebElement total = driver.findElement(By.xpath("//p[contains(@class,'css-cla92e')]"));
			String gTotal = total.getText();
			if (grdtotal.equals(gTotal)) {
				System.out.println("Grand Total is same"); 
			}
			else {
				System.out.println("Grand Total is Different");
			}

			driver.close();
			driver.quit();
		}

	}