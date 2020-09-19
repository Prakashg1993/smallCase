package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LaunchPage {
	AppiumDriver<WebElement> driver;
	
	public LaunchPage(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	@AndroidFindBy(id = "in.smallcase.gateway.sample:id/main_id_name_tv")
	public WebElement enterIDText;
	
	
	@AndroidFindBy(id="in.smallcase.gateway.sample:id/main_id_submit_bt")
	public WebElement submit;	
}
