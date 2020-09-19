package TestApplication;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.testng.annotations.Test;
import org.junit.runners.ParentRunner;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Container.Intialize;
import PageObjects.LaunchPage;
import PageObjects.environmentPage;
import PageObjects.sstPages;
import PageObjects.webPage;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class smallCase extends Intialize {
	WebDriverWait wait;
	TouchAction touch;
	LaunchPage launch;
	environmentPage environment;
	sstPages sst;
	webPage web;
	public static Properties prop;
	public String nativeApp;

	@Test(priority = 1)
	public void LaunchHome() throws IOException, InterruptedException {
		try {
			FileReader reader = new FileReader(
					System.getProperty("user.dir") + "\\src\\test\\resources\\base.properties");
			prop = new Properties();
			prop.load(reader);

			AndroidDriver<AndroidElement> driver = InitializeDriver();
			System.out.println("Successfully connected to Android device");
			touch = new TouchAction(driver);
			wait = new WebDriverWait(driver, 15);
			launch = new LaunchPage(driver);
			launch.enterIDText.click();
			launch.enterIDText.sendKeys(prop.getProperty("EnterID"));
			nativeApp = driver.getContext();
			touch.tap(tapOptions().withElement(element(launch.submit))).perform();

		} catch (Exception e) {
			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();

		}

	}

	@Test(priority = 2)
	public void setEnvGetid() throws InterruptedException {
		try {
			environment = new environmentPage(driver);

			String envSet = prop.getProperty("EnvSet");
			Map<String, Boolean> switchValue = new HashMap<String, Boolean>();
			switchValue.put("Custom Broker Config", Boolean.parseBoolean(prop.getProperty("CustomBrokerConfig")));
			switchValue.put("Leprechaun active", Boolean.parseBoolean(prop.getProperty("Leprechaunactive")));
			for (int i = 0; i < environment.radioButton.size(); i++) {
				if (environment.radioButton.get(i).getText().equalsIgnoreCase(envSet))
					touch.tap(tapOptions().withElement(element(environment.radioButton.get(i)))).perform();
			}

			int n = 0;
			for (Entry<String, Boolean> chk : switchValue.entrySet()) {
				System.out.println("=========");
				System.out.println(chk.getValue());
				if (environment.switchButton.get(n).getText().contains(chk.getKey()) && chk.getValue() == true
						&& environment.switchButton.get(n).isSelected() == false) {

					environment.switchButton.get(n).click();
				}
				n++;
			}
			touch.tap(tapOptions().withElement(element(environment.setUp))).perform();
			Thread.sleep(2000);
			String Tokenmsg = environment.authToken.getText();
			environment.authOK.click();

			System.out.println(Tokenmsg);

		} catch (Exception e) {
			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();
		}
	}

	@Test(priority = 3)
	public void sst() throws InterruptedException {
		try {
			sst = new sstPages(driver);
			String stock = prop.getProperty("stockName");
			touch.tap(tapOptions().withElement(element(sst.sstTab))).perform();

			touch.tap(tapOptions().withElement(element(sst.searchBar))).perform();
			Thread.sleep(2000);
			sst.EnterSearch.sendKeys(stock);
			((AndroidDriver<?>) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
			Thread.sleep(2000);
			for (int i = 0; i < sst.selectSearch.size(); i++) {
				System.out.println(sst.selectSearch.get(i).getText());
				if (sst.selectSearch.get(i).getText().equalsIgnoreCase(stock))
					touch.tap(tapOptions().withElement(element(sst.selectSearch.get(i)))).perform();
			}

			touch.tap(tapOptions().withElement(element(sst.Buy))).perform();
			sst.quantity.sendKeys("1");
			touch.tap(tapOptions().withElement(element(sst.add))).perform();
			touch.tap(tapOptions().withElement(element(sst.placeOrder))).perform();
			wait.until(ExpectedConditions.visibilityOf(sst.broker));

			touch.tap(tapOptions().withElement(element(sst.broker))).perform();

		} catch (Exception e) {
			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();
		}
	}

	@Test(priority = 4)
	public void webPage() throws InterruptedException {
		try {
			web = new webPage(driver);
			Thread.sleep(15000);
			Set<String> appType = driver.getContextHandles();

			for (String Web : appType) {
				if (!Web.equals(nativeApp))
					driver.context(Web);
			}

			web.tokenTextField().click();
			web.tokenTextField().sendKeys(prop.getProperty("Token"));

			web.submit().click();
			
			driver.context(nativeApp);

		} catch (Exception e) {
			System.out.println("Exception occured, stopping the server");
			e.printStackTrace();
			StopServer();
		}
	}
}
