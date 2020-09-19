package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class sstPages {

	AppiumDriver<WebElement> driver;

	public sstPages(AppiumDriver driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='SST']")
	public WebElement sstTab;

	@AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='in.smallcase.gateway.sample:id/search_button']")
	public WebElement searchBar;

	@AndroidFindBy(id = "in.smallcase.gateway.sample:id/search_src_text")
	public WebElement EnterSearch;

	@AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='in.smallcase.gateway.sample:id/smallcase_securities_list_row_title_tv']")
	public List<WebElement> selectSearch;

	@AndroidFindBy(accessibility = "BUY")
	public WebElement Buy;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text='Quantity']")
	public WebElement quantity;

	@AndroidFindBy(id = "in.smallcase.gateway.sample:id/transaction_add_bt")
	public WebElement add;

	@AndroidFindBy(id = "in.smallcase.gateway.sample:id/transaction_order_bt")
	public WebElement placeOrder;

	@AndroidFindBy(id = "in.smallcase.gateway.sample:id/row_ll_broker_item_parent")
	public WebElement broker;
}
