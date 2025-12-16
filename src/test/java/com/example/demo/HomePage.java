package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    private By addToCartButton =
            By.cssSelector("[data-testid='add-to-cart-btn']");

    public void agregarProducto() {

    }
}
