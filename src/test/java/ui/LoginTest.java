package ui;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;


public class LoginTest extends BaseTest {

    @BeforeEach
    public void loadPage() {
        driver.get(url);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginSuccessfulWithValidUser() {
        login();
        var wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class);
        boolean loginSuccess = wait.until(ExpectedConditions.urlContains("inventory.html"));
        Assertions.assertTrue(loginSuccess, "User should be redirected to inventory page after login");
    }

    @Test
    public void loginFailedForValidUsernameAndWrongPassword() {
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLoginButton();
        String message = loginPage.getErrorMessage();
        Assertions.assertEquals(message, "Epic sadface: Username and password do not match any user in this service");
    }
}
