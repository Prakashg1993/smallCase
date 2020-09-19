package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class environmentPage {

	AppiumDriver<WebElement> driver; 

	public environmentPage(AppiumDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath  = "//android.widget.RadioButton")
	public List<WebElement> radioButton;
	@AndroidFindBy(xpath  = "//android.widget.Switch")
	public List<WebElement> switchButton;
	
	@AndroidFindBy(id = "in.smallcase.gateway.sample:id/setupBt")
	public WebElement setUp;
	
	
	@AndroidFindBy(id = "android:id/message")
	public WebElement authToken;
	
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	public WebElement authOK;
}
