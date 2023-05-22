package com.liveguru.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.BaseTestLiveGuru;
import pageObjects.liveGuru.HomePageObject;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.MyDashboardPageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObject;

public class Level_06_Page_Generator_Manager_III extends BaseTestLiveGuru {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getHomePage(driver);

		firstName = "Ben";
		lastName = "Nguyen";
		validEmail = "bennt" + getRandomNumber() + "@gmail.vn";
		password = "123123123";

	}

	@Test
	public void User_01_Register_To_System() {
	
		loginPage = homePage.clickToMyAccountLink();
		
		registerPage = loginPage.clickToCreateAnAccountButton();

		registerPage.inputToFisrtNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(validEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(),
				"Thank you for registering with Main Website Store.");
		
		

	}
	
	@Test
	public void User_02_Login_To_System() {
		
		//myDashboardPage.clickToAccountLink();
		//homePage= myDashboardPage.clickToLogoutButton();
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

	private WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private MyDashboardPageObject myDashboardPage;
	private String firstName, lastName, validEmail, password;

}
