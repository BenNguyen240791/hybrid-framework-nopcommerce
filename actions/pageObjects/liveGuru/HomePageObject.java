package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.HomePageUI;
import pageUIs.liveGuru.LoginPageUI;


public class HomePageObject extends BasePage{
	
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;

	}

	public LoginPageObject clickToMyAccountLink() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getLoginPage(driver);

	}


}
