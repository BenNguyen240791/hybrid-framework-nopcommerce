package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageFactory {
	private long longTimeout = 30;

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	protected void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);

	}

	protected String getTitle(WebDriver driver) {
		return driver.getTitle();

	}

	protected String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();

	}

	protected String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();

	}

	protected void backToPage(WebDriver driver) {

		driver.navigate().back();

	}

	protected void forwardToPage(WebDriver driver) {

		driver.navigate().forward();

	}

	protected void refreshCurrentPage(WebDriver driver) {

		driver.navigate().refresh();

	}

	protected Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());

	}

	protected void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();

	}

	protected void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();

	}

	protected String getTextAlert(WebDriver driver) {

		return waitForAlertPresence(driver).getText();

	}

	protected void sendKeyToAlert(WebDriver driver, String textValue) {

		waitForAlertPresence(driver).sendKeys(textValue);

	}

	protected void switchToWindowByID(WebDriver driver, String otherID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (!id.equals(otherID)) {
				driver.switchTo().window(id);
			}

		}
	}

	protected void switchToWindowByTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {

			driver.switchTo().window(id);

			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}

		}

	}

	protected void closeAllWindowWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}

		}

		driver.switchTo().window(parentID);

	}

	protected void waitForElementVisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}

	protected void waitForAllElementVisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	protected void waitForElementInvisible(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOf(element));
	}

	protected void waitForAllElementInvisible(WebDriver driver, List<WebElement> elements) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	protected void waitForElementClickable(WebDriver driver, WebElement element) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}

	protected void clickToElement(WebDriver driver, WebElement element) {
		// Lời gọi hàm : call a function
		element.click();

	}

	protected void sendKeyToElement(WebDriver driver, WebElement element, String textValue) {
		element.clear();
		element.sendKeys(textValue);

	}
	protected String getElementText(WebDriver driver, WebElement element) {
		return element.getText();
	}
	protected boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}
}
