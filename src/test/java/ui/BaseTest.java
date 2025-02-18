package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

// chrome doesn't work. Probably because of m1
// docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" --platform=linux/amd64 selenium/standalone-chrome:latest
//
// firefox works. See supported browsers on https://github.com/SeleniumHQ/docker-selenium?tab=readme-ov-file#browser-images-in-multi-arch
// docker run -d -p 4444:4444 -p 7900:7900 --shm-size="2g" selenium/standalone-firefox:4.28.1-20250202
public class BaseTest {
    protected static LoginPage loginPage;
    protected static WebDriver driver;

    @BeforeAll
    public static void setup() throws MalformedURLException {
//        WebDriverManager.chromedriver().driverVersion("132.0.6834.159").clearDriverCache().setup();

        FirefoxOptions options = new FirefoxOptions();
//        ChromeOptions options = new ChromeOptions();
//        options.setCapability("browserName", "chrome");
        options.setCapability("browserName", "firefox");
        options.addArguments("--disable-dev-shm-usage"); // Required for Docker
        options.addArguments("--no-sandbox"); // Helps prevent crashes
        options.addArguments("--disable-gpu");
        options.addArguments("--headless"); // Run Chrome in headless mode (optional)

//        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANY.name());
//        chromeOptions.setCapability(CapabilityType.BROWSER_NAME, Browser.CHROME.browserName());
//        chromeOptions.setCapability(CapabilityType.BROWSER_VERSION, "stable");

//        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);
        driver = new RemoteWebDriver(options);
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
