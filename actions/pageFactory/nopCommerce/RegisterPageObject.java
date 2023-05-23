package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	// Page UI
		@FindBy(id = "FirstName")
		private WebElement firstNameTextbox;

		@FindBy(id = "LastName")
		private WebElement lastNameTextbox;

		@FindBy(id = "Email")
		private WebElement emailTextbox;
		
		@FindBy(id = "Password")
		private WebElement passwordTextbox;
		
		@FindBy(id = "ConfirmPassword")
		private WebElement confirmPasswordTextbox;

		@FindBy(id = "register-button")
		private WebElement registerButton;
		
		@FindBy(id = "FirstName-error")
		private WebElement firstNameErrorMessage;
		
		@FindBy(id = "LastName-error")
		private WebElement lastNameErrorMessage;
		
		@FindBy(id = "Email-error")
		private WebElement emailErrorMessage;
		
		@FindBy(id = "Password-error")
		private WebElement passwordErrorMessage;
		
		@FindBy(id = "ConfirmPassword-error")
		private WebElement confirmPasswordErrorMessage;

		@FindBy(xpath = "//div[@class='result']")
		private WebElement registerSuccessMessage;
		
		@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
		private WebElement existingEmailErrorMessage;
		
		@FindBy(xpath = "//a[contains(@class,'register-continue-button')]")
		private WebElement continueButton;
	
		public void clickToRegisterButton() {
			waitForElementClickable(driver, registerButton);
			clickToElement(driver, registerButton);

		}

		public String getErrorMessageAtFirstNameTextbox() {
			waitForElementVisible(driver, firstNameErrorMessage);
			return getElementText(driver, firstNameErrorMessage);
		}

		public String getErrorMessageAtLastNameTextbox() {
			waitForElementVisible(driver, lastNameErrorMessage);
			return getElementText(driver, lastNameErrorMessage);
		}

		public String getErrorMessageAtEmailTextbox() {
			waitForElementVisible(driver, emailErrorMessage);
			return getElementText(driver, emailErrorMessage);
		}

		public String getErrorMessageAtPasswordTextbox() {
			waitForElementVisible(driver, passwordErrorMessage);
			return getElementText(driver, passwordErrorMessage);
		}

		public String getErrorMessageAtConfirmTextbox() {
			waitForElementVisible(driver, confirmPasswordErrorMessage);
			return getElementText(driver, confirmPasswordErrorMessage);
		}

		public void inputToFirstNameTextbox(String firstName) {
			waitForElementVisible(driver, firstNameTextbox);
			sendKeyToElement(driver, firstNameTextbox, firstName);

		}

		public void inputToLastNameTextbox(String lastName) {
			waitForElementVisible(driver, lastNameTextbox);
			sendKeyToElement(driver, lastNameTextbox, lastName);

		}

		public void inputToEmailTextbox(String emailAddress) {
			waitForElementVisible(driver, emailTextbox);
			sendKeyToElement(driver, emailTextbox, emailAddress);

		}

		public void inputToPasswordTextbox(String password) {
			waitForElementVisible(driver, passwordTextbox);
			sendKeyToElement(driver, passwordTextbox, password);

		}

		public void inputToConfirmPasswordTextbox(String confirmPassword) {
			waitForElementVisible(driver, confirmPasswordTextbox);
			sendKeyToElement(driver, confirmPasswordTextbox, confirmPassword);

		}

		public String getRegisterSuccessMessage() {
			waitForElementVisible(driver, registerSuccessMessage);
			return getElementText(driver, registerSuccessMessage);
		}

		public String getErrorExistingEmailMessage() {
			waitForElementVisible(driver, existingEmailErrorMessage);
			return getElementText(driver, existingEmailErrorMessage);
		}

		public void clickToContinueButton() {
			waitForElementClickable(driver, continueButton);
			clickToElement(driver, continueButton);
			
		}

}
