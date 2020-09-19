package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class webPage {


public WebDriver driver;

	public webPage(WebDriver driver) {
	this.driver=driver;
	}

	By tokenTextField=By.xpath("//input[@placeholder='Enter Token Here']");
	By submit=By.xpath("//button[@type='submit']");
	
	public  WebElement tokenTextField() {
		return driver.findElement(tokenTextField);
	}
	public  WebElement submit() {
		return driver.findElement(submit);
	}
}
