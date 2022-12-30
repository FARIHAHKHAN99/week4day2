package week4day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Stkmarkt {

		public static void main(String[] args) throws InterruptedException {
			
			ChromeOptions option=new ChromeOptions();
			option.addArguments("--disable-notifications");
			
			ChromeDriver driver = new ChromeDriver(option);
			driver.get("https://www.chittorgarh.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
			
			driver.findElement(By.xpath("//a[@id='navbtn_stockmarket']")).click();
			Thread.sleep(2000);
			
			Actions acts=new Actions(driver);
			driver.findElement(By.xpath("(//a[text()='NSE Bulk Deals'])[1]")).click();
			acts.scrollByAmount(0, 500).perform();
			
			List<WebElement> count= driver.findElements(By.xpath("//table[@class='table table-bordered table-condensed table-striped']/tbody/tr/td[3]"));
			int size = count.size();
			System.out.println(size);
			List<String> secnames=new ArrayList<String>();
			Set<String> dup=new LinkedHashSet<String>(secnames);
			
			for(int i=1;i<=size;i++) {
					
				WebElement findElement = driver.findElement(By.xpath("//table[@class='table table-bordered table-condensed table-striped']/tbody/tr["+i+"]/td[3]"));
				String txt = findElement.getText();
				System.out.println(txt);
				secnames.add(txt);
				dup.add(txt);		
			}
			
			System.out.println("total no of security names: " +secnames.size());
			System.out.println(dup);
			System.out.println("without duplicates: " +dup.size());
			
			     if(secnames.size()==dup.size()) {
			    	 
			    	System.out.println("No Duplicate found"); 
			     }
			     else {
			    	 System.out.println("Duplicate found");
			    	 
			    	 driver.close();
			     }
		}

	}

