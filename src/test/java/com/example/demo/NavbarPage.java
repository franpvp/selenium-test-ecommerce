package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavbarPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public NavbarPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By loginButton =
            By.xpath("//button[normalize-space()='Iniciar sesión']");

    public void clickIniciarSesion() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }
}
