package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_03_Page_Object_02_Login {
	private WebDriver driver;
	private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, validPassword, incorrectPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new UserHomePageObject(driver);

		firstName = "Ben";
		lastName = "Nguyen";
		invalidEmail = "bennt@gmail.com@gmail";
		existingEmail = "bennt" + getRandomNumber() + "@gmail.vn";
		notFoundEmail = "bennt" + getRandomNumber() + "@gmail.com";
		validPassword = "123123123";
		incorrectPassword = "123456";

		System.out.println("Pre-Condition -Step 01: Click to Register link");
		homePage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Pre-Condition -Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);

		System.out.println("Pre-Condition- Step 03 : Click to Register button");
		registerPage.clickToRegisterButton();

		// CLick Register link >> nhảy qua trang Register >> thì phải khởi tạo
		registerPage = new UserRegisterPageObject(driver);

		System.out.println("Pre-Condition - Step 04 : Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register_03 -Step 04: Click to Continue button");
		registerPage.clickToContinueButton();

		// Click Continue thì business nó sẽ quay về trang HomePage
		homePage = new UserHomePageObject(driver);

	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01 -Step 01: Click to Login link");
		homePage.openLoginPage();

		// Từ trang HOme - click Login link >> Qua trang login
		loginPage = new UserLoginPageObject(driver);

		System.out.println("Login_01 -Step 02: Click to Login button");
		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");

	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(invalidEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(notFoundEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(existingEmail);

		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Incorrect_Password() {
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(existingEmail);

		loginPage.inputToPasswordTextbox(incorrectPassword);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password() {
		System.out.println("Login_01 -Step 01: Click to Login link");
		homePage.openLoginPage();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(existingEmail);

		loginPage.inputToPasswordTextbox(validPassword);

		loginPage.clickToLoginButton();

		homePage = new UserHomePageObject(driver);

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

	}

	@AfterClass // Custom close browser/driver
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {

		return new Random().nextInt(99999);

	}

}
