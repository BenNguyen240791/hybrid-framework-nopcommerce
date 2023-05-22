package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage {
	private WebDriver driver;

	public MyDashboardPageObject(WebDriver driver){
		this.driver = driver;
	}

	public void clickToAccountLink() {
		waitForElementClickable(driver, MyDashboardPageUI.ACCOUNT_LINK);
		clickToElement(driver, MyDashboardPageUI.ACCOUNT_LINK);
	}

	public HomePageObject clickToLogoutButton() {
		waitForElementClickable(driver, MyDashboardPageUI.LOGOUT_BUTTON);
		clickToElement(driver, MyDashboardPageUI.LOGOUT_BUTTON);
		return new PageGeneratorManager().getHomePage(driver);
	}

}
