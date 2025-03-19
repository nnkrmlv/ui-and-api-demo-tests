package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InventoryItem {
    private final WebElement item;
    public InventoryItem(WebElement item) {
        this.item = item;
    }

    private final By inventoryButton = By.className("btn_inventory");
    private final By title = By.cssSelector("[data-test='inventory-item-name']");
    private final By price = By.cssSelector("[data-test='inventory-item-price']");
    private final By description = By.cssSelector("[data-test='inventory-item-desc']");


    public void addToCart() {
        WebElement button = item.findElement(inventoryButton);
        if (button.getText().equalsIgnoreCase("Add to cart")) {
            button.click();
        }
    }

    public void removeFromCart() {
        WebElement button = item.findElement(inventoryButton);
        if (button.getText().equalsIgnoreCase("Remove")) {
            button.click();
        }
    }
    public String getTitle() {
        return item.findElement(title).getText();
    }
    public String getDescription() {
        return item.findElement(description).getText();
    }
    public Double getPrice() {
        String priceText = item.findElement(price).getText().replace("$", "").trim();
        return Double.parseDouble(priceText);
    }






}
