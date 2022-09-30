package week4.day2.homework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundActions {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		
		//Launch https://www.leafground.com/drag.xhtml"
		
		driver.get("https://www.leafground.com/drag.xhtml");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Actions builder = new Actions(driver);
		
		//drag me around
		
		WebElement e=driver.findElement(By.xpath("//div[@id='form:conpn1']"));
		Thread.sleep(5000);
		builder.dragAndDropBy(e, 450, 160).perform();
		
		//drag and drop
		
		WebElement Sele=driver.findElement(By.xpath("//div[@id='form:drag_content']"));
		WebElement Tele=driver.findElement(By.xpath("//div[@id='form:drop_content']"));
		Thread.sleep(4000);
		builder.dragAndDrop(Sele, Tele).perform();
		
	}
	

}
