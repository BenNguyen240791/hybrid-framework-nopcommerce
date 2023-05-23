package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.LoginPageUI;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page UI
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;

	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton;

	@FindBy(xpath = "//span[@id = 'Email-error']")
	private WebElement emailErrorMessage;

	@FindBy(xpath = "//div[contains(@class,'validation-summary-errors')]")
	private WebElement unseccessfullErrorMessage;
	// Page Object/Action

	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}


	public void inputToEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, emailTextbox);	
		sendKeyToElement(driver, emailTextbox, invalidEmail);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementClickable(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, unseccessfullErrorMessage);
		return getElementText(driver, unseccessfullErrorMessage);
	}

	
	
}
