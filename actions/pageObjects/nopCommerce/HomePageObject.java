package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;

import com.nopcommerce.user.MyAccountPageObject;

import commons.BasePage;
import pageUIs.nopCommerce.HomePageUI;
import pageUIs.nopCommerce.LoginPageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;

	}

	public RegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		// Cach 2
		// return new RegisterPageObject(driver);
		
		// Cach 3
		return PageGeneratorManager.getRegisterPage(driver);

	}

	public LoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return new PageGeneratorManager().getLoginPage(driver);

	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public MyAccountPageObject clickToMyAccountLink() {
		// TODO Auto-generated method stub
		return null;
	}

}
