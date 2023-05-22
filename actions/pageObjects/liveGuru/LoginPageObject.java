package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.LoginPageUI;

public class LoginPageObject extends BasePage{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;	}

	public RegisterPageObject clickToCreateAnAccountButton() {
		waitForElementVisible(driver, LoginPageUI.CREATE_AN_ACCOUNT);
		clickToElement(driver, LoginPageUI.CREATE_AN_ACCOUNT);
		return PageGeneratorManager.getRegisterPageObject(driver);
	}

}
