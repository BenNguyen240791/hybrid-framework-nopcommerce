package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjects.nopCommerce.HomePageObject;
import pageObjects.nopCommerce.RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;

	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
	
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);

		firstName = "Ben";
		lastName = "Nguyen";
		password = "123123123";
		emailAddress = "bennt" + getRandomNumber() + "@gmail.vn"; 
	}

	@Test
	public void Register_01_Empty_Data() {

		System.out.println("Register_01 -Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_01 - Step 02 : Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_01 - Step 03 : Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {

		System.out.println("Register_02 -Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		// CLick Register link >> nhảy qua trang Register >> thì phải khởi tạo
		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_02 -Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("bennt..");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_02 - Step 03 : Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 04 : Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 -Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		// CLick Register link >> nhảy qua trang Register >> thì phải khởi tạo
		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_03 -Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_03 - Step 03 : Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 04 : Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register_03 -Step 04: Click to Continue button");
		registerPage.clickToContinueButton();

	}

	@Test
	public void Register_04_Existing_Email() {

		System.out.println("Register_04 -Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		// CLick Register link >> nhảy qua trang Register >> thì phải khởi tạo
		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_04 -Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register_04 - Step 03 : Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04 - Step 04 : Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {

		System.out.println("Register_05 -Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		// CLick Register link >> nhảy qua trang Register >> thì phải khởi tạo
		registerPage = new RegisterPageObject(driver);

		System.out.println("Register_05 -Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfirmPasswordTextbox("123");

		System.out.println("Register_05 - Step 03 : Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_05 - Step 04 : Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(),
				"Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {

		System.out.println("Register_06 -Step 01: Click to Register link");
		homePage.clickToRegisterLink();

		System.out.println("Register_06 -Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(emailAddress);

		System.out.println("Register_06 - Step 03 : Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_06 - Step 04 : Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(),
				"The password and confirmation password do not match.");

	}

	@AfterClass // Custom close browser/driver
	public void afterClass() {
		driver.quit();
	}

	public int getRandomNumber() {

		return new Random().nextInt(99999);

	}

}
