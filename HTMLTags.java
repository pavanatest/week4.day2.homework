package week4.day2.homework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HTMLTags {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		
		//1. Launch https://html.com/tags/table/
		
		driver.get("https://html.com/tags/table/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//2. get te count  of rows
		List<WebElement> t = driver.findElements(By.tagName("tr"));
		int size=t.size();
		System.out.println("rows"+ size);
		//3. get count of number of columns
		
		List<WebElement> t1= driver.findElements(By.tagName("th"));
		int size1=t1.size();
		System.out.println("columns"+ size1);
		
		
		

	}

}
