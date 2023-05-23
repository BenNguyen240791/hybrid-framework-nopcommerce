package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_07_Switch_Page extends BaseTest {
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		firstName = "Ben";
		lastName = "Nguyen";
		emailAddress = "bennt" + getRandomNumber() + "@gmail.vn";
		validPassword = "123123123";
	}

	@Test
	public void User_01_Empty_Register() {
		registerPage = homePage.openRegisterPage();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickToContinueButton();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.openLoginPage();
	
		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(validPassword);
		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}

	@Test
	public void User_03_Customer_Infor() {
		customerInfoPage = homePage.openCustomerInfoPage();
		//Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplayed());
	}

	@Test
	public void User_04_Switch_Page() {
		// Customer Info >> Address
		addressesPage = customerInfoPage.openAddressPage(driver);
			
		// Address >> My Product Review
		myProductReviewPage =  addressesPage.openMyProductReviewPage(driver);
		
		// My Product Review >> Reward Point
		rewardPointPage =  myProductReviewPage.openRewardPointPage(driver);
		
		// Reward Point >> Address 
		addressesPage =  rewardPointPage.openAddressPage(driver);
		
		// Address >> Reward Point
		rewardPointPage =  addressesPage.openRewardPointPage(driver);
		
		// Reward Point >> My Product Review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		//My Product Review  >> Address
		addressesPage =  myProductReviewPage.openAddressPage(driver);
		
		// Address >> CustomerInfo
		customerInfoPage =  addressesPage.openCustomerInfoPage(driver);

	}

	@Test
	public void User_05_Switch_Role() {
		// Role User -> Role Admin
		
		// Role Admin -> Role User
		

	}

	@AfterClass // Custom close browser/driver
	public void afterClass() {
		//driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressesPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;
	
	private String firstName, lastName,emailAddress, validPassword;

}
