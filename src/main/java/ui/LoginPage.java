package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;


public class LoginPage {
    private final WebDriver driver;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    private final By usernameInput = By.cssSelector("[data-test='username']");
    private final By passwordInput = By.cssSelector("[data-test='password']");
    private final By loginButton = By.cssSelector("[data-test='login-button']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public void enterUsername(String username) {
        driver.findElement(usernameInput).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public void login(String username, String password) {
        var wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(4))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        driver.findElement(usernameInput).sendKeys(username);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
    }
}
