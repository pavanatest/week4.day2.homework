package week4.day2.homework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
	
		//	1. Launch https://www.snapdeal.com/
		
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Actions builder=new Actions (driver);
		
		//	2. Go to Mens Fashion
		WebElement Mens = driver.findElement(By.xpath("//div[@class='leftNavigationLeftContainer expandDiv']//span[@class='catText']"));
	
		builder.moveToElement(Mens).perform();
		
		//	3. Go to Sports Shoes
		driver.findElement(By.xpath("//div[@class='colDataInnerBlk']//span[@class='linkTest']")).click();
		
		//	4. Get the count of the sports shoes
		String countShoes = driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText();
		System.out.println("Count of Sports shoes : "+countShoes);
		
		//	5. Click Training shoes
		
		driver.findElement(By.xpath("(//div[@class='child-cat-name '])[2]")).click();
		Thread.sleep(6000);
		
		//	6. Sort by Low to High
		
		driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
		driver.findElement(By.xpath("//li[@class='search-li']")).click();
		
		
		//	7. Check if the items displayed are sorted correctly
		
		Thread.sleep(6000);
		
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		int rowCount=price.size();
		Double[] priceArray=new Double[rowCount];
		Double[] priceArraySort=new Double[rowCount];
		for(int i=0;i<rowCount;i++)
		{
			priceArray[i]=Double.parseDouble(price.get(i).getText().replace("Rs.", "").replace(",", ""));
			priceArraySort[i]=Double.parseDouble(price.get(i).getText().replace("Rs.", "").replace(",", ""));
		}
		Arrays.sort(priceArray);
		if(Arrays.equals(priceArray, priceArraySort))
			System.out.println("Values are sorted correctly");
		else
			System.out.println("Values are not sorted correctly");
		
				
		//	8.Select the price range (900-1200)
		
		Thread.sleep(4000);
			
		driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
		driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
		driver.findElement(By.xpath("//input[@name='toVal']")).clear();
		driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
		
	
		//	9.Filter with color Navy 
		
		driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
		Thread.sleep(4000);
		
		
		//	10 verify the all applied filters 
		String filter = driver.findElement(By.xpath("//div[@class='navFiltersPill']//a")).getText();
		System.out.println("Filters applied are :"+filter);
		
		//	11. Mouse Hover on first resulting Training shoes
		WebElement shoe1 = driver.findElement(By.xpath("(//img[@class='product-image wooble'])"));
		builder.moveToElement(shoe1).perform();
		
		//	12. click QuickView button
		driver.findElement(By.xpath("//div[@class='clearfix row-disc']/div")).click();
		
		//	13. Print the cost and the discount percentage
		Thread.sleep(5000);
		String priceShoe = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		
		System.out.println("Price of the filtered shoe :" +priceShoe);
		
		String discount = driver.findElement(By.xpath("(//span[@class='percent-desc '])")).getText();
		System.out.println("Discount percentage of the filtered shoe : "+discount);
		
		
		//	14. Take the snapshot of the shoes.
		Thread.sleep(5000);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("./snaps/snapDealScreenShot1.png");
		FileUtils.copyFile(source, destination);
		
		//	15. Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']")).click();
				
		
		


	}

}
