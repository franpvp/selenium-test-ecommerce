package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class AddToCartTest extends BaseTest {


    private By nombreInput = By.name("nombre");
    private By apellidoInput = By.name("apellido");
    private By telefonoInput = By.name("telefono");
    private By direccionInput = By.name("direccion");
    private By regionSelect = By.name("region");
    private By ciudadSelect = By.name("ciudad");
    private By codigoPostalInput = By.name("codigoPostal");

    private By cardNumberInput = By.name("cardNumber");
    private By cardNameInput   = By.name("cardName");
    private By expiryInput     = By.name("expiry");
    private By cvcInput        = By.name("cvc");

    private By apiErrorMessage =
            By.cssSelector("[data-testid='api-error-message']");

    @BeforeEach
    public void init(){
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");
    }


    @Test
    void agregarProductoAlCarritoTest() {

        int segundos = 3;
        pausa(segundos);

        By addToCartButton = By.cssSelector("[data-testid='add-to-cart-btn']");
        driver.findElement(addToCartButton).click();

        WebElement badge =
                driver.findElement(By.cssSelector("[data-testid='cart-count']"));
        pausa(segundos);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement comprarAhora = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-testid='buy-now-btn']")
                )
        );


        comprarAhora.click();

        pausa(segundos);
        WebElement irAPagar = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-testid='go-pay-btn']")
                )
        );
        pausa(segundos);
        irAPagar.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(nombreInput))
                .sendKeys("Juan");

        driver.findElement(apellidoInput)
                .sendKeys("Pérez");

        driver.findElement(telefonoInput)
                .sendKeys("912345678");

        driver.findElement(direccionInput)
                .sendKeys("Av. Providencia 1234, Depto 56");


        Select region = new Select(
                driver.findElement(regionSelect)
        );
        region.selectByVisibleText("Región Metropolitana");

        wait.until(ExpectedConditions.elementToBeClickable(ciudadSelect));

        Select ciudad = new Select(
                driver.findElement(ciudadSelect)
        );
        ciudad.selectByIndex(1);

        driver.findElement(codigoPostalInput)
                .sendKeys("7500000");
        pausa(segundos);


        WebElement continuarPago = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-testid='continue-pay-btn']")
                )
        );
        pausa(segundos);
        continuarPago.click();

        pausa(segundos);
        wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberInput))
                .sendKeys("4242 4242 4242 4242");

        driver.findElement(cardNameInput)
                .sendKeys("Juan Perez");

        driver.findElement(expiryInput)
                .sendKeys("12/2030");

        driver.findElement(cvcInput)
                .sendKeys("123");

        pausa(segundos);
        WebElement aceptarPago = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("[data-testid='accept-pay-btn']")
                )
        );
        pausa(segundos);

        aceptarPago.click();

        pausa(50);




    }




    protected void pausa(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
