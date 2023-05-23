package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalContants;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressPageObject;
import pageObjects.nopCommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageObjects.nopCpmmerce.admin.AdminDashboardPageObject;
import pageObjects.nopCpmmerce.admin.AdminLoginPageObject;

public class Level_08_Switch_Role extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);

		userEmailAddress = "bennt1@gmail.com";
		userPassword = "123123123";

		adminEmailAddress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();
		
		//Login as User role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
		
		// Home Page -> Customer Info
		userCustomerInfoPage = userHomePage.openCustomerInfoPage();
		
		// Customer Info click Logout >> HomePage
		userHomePage = userCustomerInfoPage.clickToLogoutLinkLinkAtUserPage(driver);
		
		// User home page >> Open admin page >> login page
		userHomePage.openPageUrl(driver, GlobalContants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);

		// Login as Admin role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());
		
		// Dashboard  page >> Click logout >> Login page
		adminLoginPage =  adminDashboardPage.clickToLogoutLinkLinkAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin_To_User() {
		// Login {age (Admin) -> Open Portal Page
		adminLoginPage.openPageUrl(driver, GlobalContants.PORTAL_PAGE_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	private WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private String userEmailAddress, userPassword, adminEmailAddress, adminPassword;

}
