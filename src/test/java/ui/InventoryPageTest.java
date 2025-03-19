package ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class InventoryPageTest extends BaseTest {
    private InventoryPage inventoryPage;

    @BeforeEach
    public void loadPage() {
        try {
            driver.get(url);
            login();
            inventoryPage = new InventoryPage(driver);
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.urlContains("inventory.html"));
            new WebDriverWait(driver, Duration.ofSeconds(4))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("inventory_container")));
        } catch (Exception e) {
            throw new RuntimeException("Failed to wait for inventory page to load.", e);
        }
    }

    //
    @Test
    public void AddItemToCartSuccessfully() {
        InventoryItem firstItem = inventoryPage.getAllItems().get(0);
        firstItem.addToCart();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-test='shopping-cart-badge']")));
        String title = firstItem.getTitle();
        int cartCount = inventoryPage.getCartItemCount();
        Assertions.assertTrue(inventoryPage.isCartBadgeVisible(), "Cart should show item count");
        Assertions.assertEquals( 1, cartCount);
        System.out.println("The item \"" + title + "\" has been added to the cart.");
    }
}
