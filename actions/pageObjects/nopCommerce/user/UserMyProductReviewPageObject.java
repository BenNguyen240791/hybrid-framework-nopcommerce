package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopCommerce.user.CustomerInfoPageUI;

public class UserMyProductReviewPageObject extends BasePage {
	WebDriver driver;
	
	public UserMyProductReviewPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isCustomerInfoPageDisplayed() {
		waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}

}
