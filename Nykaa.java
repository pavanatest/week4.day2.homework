package week4.day2.homework;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	
	
		public static void main(String[] args) throws InterruptedException {
			WebDriverManager.chromedriver().setup();
			ChromeDriver driver=new ChromeDriver();
			
			driver.get("https://www.nykaa.com/");
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			Actions builder=new Actions (driver);
			
			// 2) Mousehover on Brands and Search L'Oreal Paris
			WebElement brandEle = driver.findElement(By.xpath("//a[text()='brands']"));
			builder.moveToElement(brandEle).perform();
			
			driver.findElement(By.xpath("//input[@id='brandSearchBox']")).sendKeys("L'Oreal Paris");
			driver.findElement(By.xpath("//a[text()=\"L'Oreal Paris\"]")).sendKeys(Keys.ENTER);
			
			// 4) Check the title contains L'Oreal Paris(Hint-GetTitle)
			String title = driver.getTitle();
			System.out.println("Page title is = " + title);
			if(title.contains("L'Oreal Paris products"))
				System.out.println("Title is verified");
			else
				System.out.println("Title is not verified");
					
			Thread.sleep(6000);
			// 5) Click sort By and select customer top rated
			
			
			driver.findElement(By.xpath("//span[@class='sort-name']")).click();
			driver.findElement(By.xpath("//div[@class='control-value']/span[text()='customer top rated']")).click();
			

			//6) Click Category and click Hair->Click haircare->Shampoo
		
			driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();
		
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//span[normalize-space()='Hair']")).click();
			driver.findElement(By.xpath("//span[@class='filter-name ' and text()='Hair']")).click();
			driver.findElement(By.xpath("(//span[text()='Hair Care'])[2]")).click();
			driver.findElement(By.xpath("//span[@class='title' and text()='Shampoo']")).click();
			
			//7) Click->Concern->Color Protection
			driver.findElement(By.xpath("//span[@class='title ' and text()='Concern']")).click();
			driver.findElement(By.xpath("//span[@class='title' and text()='Color Protection']")).click();
			
			// 8)check whether the Filter is applied with Shampoo
			String option1 = driver.findElement(By.xpath("//div[@class='css-rtde4j']/div[@class='css-1emjbq5']/span")).getText();
			String option2 = driver.findElement(By.xpath("(//div[@class='css-rtde4j']/div[@class='css-1emjbq5']/span)[2]")).getText();
			if(option1.equals("Shampoo")) 
			{
				 if (option2.equals("Color Protection"))
				 {	 
					 System.out.println("Filters verified");
				 }
			else
				System.out.println("Filter verification failed");
				 
			//9) Click on L'Oreal Paris Colour Protect Shampoo
			Thread.sleep(7000);
			driver.findElement(By.xpath("//div[contains(text(),'Colour Protect Shampoo')]")).click();
			Thread.sleep(5000);
			//driver.findElement(By.xpath("//img[@class='css-11gn9r6']")).click();
			
			// 10) GO to the new window and select size as 175ml
			
			Set<String> windowHandles = driver.getWindowHandles();
			List<String>windows=new ArrayList<String>(windowHandles);
			driver.switchTo().window(windows.get(1));
			driver.manage().window().maximize();
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//span[contains(@class,'css-ieawrs') and text()='192.5ml']")).click();
			
			//11) Print the MRP of the product
			String mrp = driver.findElement(By.xpath("//span[@class='css-1jczs19']")).getText();
			System.out.println("MRP is :" +mrp.replaceAll("[^0-9]",""));
			
			
			//12) Click on ADD to BAG
			driver.findElement(By.xpath("//span[@class='btn-text' and text()='Add to Bag']")).click();
			
			//13) Go to Shopping Bag 
			driver.findElement(By.xpath("//span[@class='cart-count']")).click();
			
			//14) Print the Grand Total amount
			driver.switchTo().frame(0);
			
			Thread.sleep(6000);
			String grandTotal = driver.findElement(By.xpath("//div[@class='value medium-strong']")).getText();
			
		
			System.out.println("Grand Total  :" +grandTotal.replaceAll("[^0-9]",""));
			
			//15) Click Proceed
		
			driver.findElement(By.xpath("(//span[@class='vernacular-string'])[14]")).click();
		
			Thread.sleep(5000);
			
			//16) Click on Continue as Guest
			driver.findElement(By.xpath("//button[@class='btn full big']")).click();
			
			//17) Check if this grand total is the same in step 14
			String verifyTotal = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
			System.out.println("Verify total in checkout page : "+verifyTotal.replaceAll("[^0-9]",""));
			
			if(grandTotal.equals(verifyTotal))
				System.out.println("Grand Total is same");
			else
				System.out.println("Grand Total differs");
			
			//18) Close all windows
			Thread.sleep(4000);
			//driver.quit();
			
	
	}

}
}