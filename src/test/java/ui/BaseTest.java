package ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseTest {
    protected static LoginPage loginPage;
    protected static WebDriver driver;
    protected static String url = "https://www.saucedemo.com/";
    @BeforeAll
    public static void setup() {
        String browser = System.getProperty("ui.test.browser", "chrome");
        System.out.println("Run setup with browser " + browser);

        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("browserName", "chrome");
            options.addArguments("--disable-dev-shm-usage"); // Required for Docker
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--headless");
            driver = new RemoteWebDriver(options);
        } else if (browser.equals("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("browserName", "firefox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--headless");
            driver = new RemoteWebDriver(options);
        } else {
            throw new IllegalArgumentException("browser " + browser + " is not supported in ui tests");
        }
        loginPage = new LoginPage(driver);
    }

    @AfterAll
    public static void quitBrowser() {
        driver.quit();
    }

    protected void login() {
        loginPage.login("standard_user", "secret_sauce");
    }
}
