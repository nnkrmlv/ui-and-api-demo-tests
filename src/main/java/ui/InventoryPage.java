package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    private WebDriver driver;
    private final String url = "https://www.saucedemo.com/inventory.html";

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private final By inventoryList = By.cssSelector("[data-test='inventory-list']");

    private final By burgerMenu = By.id("react-burger-menu-btn");

    private final By cartBadge = By.cssSelector("[data-test='shopping-cart-link']");
    private final By cartItemCount = By.cssSelector("[data-test='shopping-cart-badge']");

    private final By dropdown = By.cssSelector(("[data-test='product-sort-container']"));

    public List<InventoryItem> getAllItems() {
        List<InventoryItem> items = new ArrayList<>();
        for (WebElement webElement : driver.findElements(inventoryList)) {
            items.add(new InventoryItem(webElement));
        }
        return items;
    }

    public void openBurgerMenu() {
        driver.findElement(burgerMenu).click();
    }
    public boolean isCartBadgeVisible() {
        return driver.findElements(cartBadge).size() > 0;
    }

    public List<WebElement> getAllDropdownOptions() {
        return driver.findElements(dropdown);
    }

    public int getCartItemCount() {
        if (isCartBadgeVisible()) {
            return Integer.parseInt(driver.findElement(By.cssSelector("[data-test='shopping-cart-badge']")).getText());
        }
        return 0;
    }

}
