package com.example.demo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class EcommercePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public EcommercePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void limpiarSesion() {
        driver.manage().deleteAllCookies();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        js.executeScript("window.sessionStorage.clear();");
    }


    private By loginButton =
            By.xpath("//button[normalize-space()='Iniciar sesión']");

    private By cartCount =
            By.cssSelector("[data-testid='cart-count']");

    public void clickIniciarSesion() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton))
                .click();
    }

    public String obtenerCantidadCarrito() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartCount)
        ).getText();
    }


    private By productCards =
            By.cssSelector("[data-testid='product-card']");

    private By addToCartButtons =
            By.cssSelector("[data-testid='add-to-cart-btn']");

    public void agregarPrimerProductoAlCarrito() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(productCards));
        List<WebElement> botones = driver.findElements(addToCartButtons);
        if (botones.isEmpty()) {
            throw new NoSuchElementException("No se encontraron botones 'Agregar al carrito'");
        }
        wait.until(ExpectedConditions.elementToBeClickable(botones.get(0)))
                .click();
    }

    private By comprarAhoraButton =
            By.cssSelector("[data-testid='buy-now-btn']");

    private By irAPagarButton =
            By.cssSelector("[data-testid='go-pay-btn']");

    public void clickComprarAhora() {
        wait.until(ExpectedConditions.elementToBeClickable(comprarAhoraButton))
                .click();
    }

    public void clickIrAPagar() {
        wait.until(ExpectedConditions.elementToBeClickable(irAPagarButton))
                .click();
    }


    private By nombreInput = By.name("nombre");
    private By apellidoInput = By.name("apellido");
    private By telefonoInput = By.name("telefono");
    private By direccionInput = By.name("direccion");
    private By regionSelect = By.name("region");
    private By ciudadSelect = By.name("ciudad");
    private By codigoPostalInput = By.name("codigoPostal");

    private By continuarPagoButton =
            By.xpath("//button[normalize-space()='Continuar al Pago']");

    public void completarFormularioDespacho() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(nombreInput))
                .sendKeys("Juan");

        driver.findElement(apellidoInput).sendKeys("Pérez");
        driver.findElement(telefonoInput).sendKeys("912345678");
        driver.findElement(direccionInput)
                .sendKeys("Av. Providencia 1234, Depto 56");

        new Select(driver.findElement(regionSelect))
                .selectByVisibleText("Región Metropolitana");

        wait.until(ExpectedConditions.elementToBeClickable(ciudadSelect));
        new Select(driver.findElement(ciudadSelect))
                .selectByIndex(1);

        driver.findElement(codigoPostalInput).sendKeys("7500000");
    }

    public void continuarAlPago() {
        wait.until(ExpectedConditions.elementToBeClickable(continuarPagoButton))
                .click();
    }
}
