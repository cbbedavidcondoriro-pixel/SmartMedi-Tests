package com.swag.pe.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SmartMediLoginPage extends PageObject {

    @FindBy(css = "button.btn-green")
    WebElementFacade botonIngresarHome;

    @FindBy(name = "usuario")
    WebElementFacade campoUsuario;

    @FindBy(name = "password")
    WebElementFacade campoPassword;

    @FindBy(css = "button.btn-submit")
    WebElementFacade botonIngresar;

    @FindBy(css = "div.logout-box")
    WebElementFacade botonCerrarSesion;

    public void abrirHome() {
        openUrl("http://localhost:4200/");
        getDriver().manage().window().maximize();
    }

    public void abrirLogin() {
        openUrl("http://localhost:4200/login");
        getDriver().manage().window().maximize();
    }

    public void esperarSegundos(int segundos) {
        waitABit(segundos * 1000L);
    }

    public void clicIngresarDesdeHome() {
        botonIngresarHome.waitUntilClickable().click();
    }

    public void escribirUsuario(String usuario) {
        campoUsuario.waitUntilVisible().type(usuario);
    }

    public void escribirPassword(String password) {
        campoPassword.waitUntilVisible().type(password);
    }

    public void clicIngresar() {
        botonIngresar.waitUntilClickable().click();
    }

    public void aceptarAlertaSiExiste() {
        try {
            waitABit(800);
            Alert alerta = getDriver().switchTo().alert();
            alerta.accept();
            waitABit(800);
        } catch (NoAlertPresentException ignored) {
        }
    }

    public String obtenerUrlActual() {
        aceptarAlertaSiExiste();
        waitABit(1000);
        return getDriver().getCurrentUrl();
    }

    private void clicMenuSeguro(String ruta) {
        aceptarAlertaSiExiste();
        WebElement item = find(By.cssSelector("ul.menu li[routerLink='" + ruta + "']")).waitUntilPresent();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'}); arguments[0].click();", item);
    }

    private void deslizarPantallaSuave(int segundosTotales) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        long pausaPaso = (segundosTotales * 1000L) / 4;

        waitABit(pausaPaso);
        js.executeScript("window.scrollTo({ top: document.body.scrollHeight / 2, behavior: 'smooth' });");
        waitABit(pausaPaso);
        js.executeScript("window.scrollTo({ top: document.body.scrollHeight, behavior: 'smooth' });");
        waitABit(pausaPaso);
        js.executeScript("window.scrollTo({ top: 0, behavior: 'smooth' });");
        waitABit(pausaPaso);
    }

    public void recorrerModulosMedicoInteractivo() {
        // 1. Dashboard inicial
        deslizarPantallaSuave(10);

        // 2. Registrar Paciente
        clicMenuSeguro("/patients");
        deslizarPantallaSuave(10);

        // 3. Historial Clínico 3D
        clicMenuSeguro("/historial-clinico");
        waitABit(1500);
        try {
            WebElement selectEl = getDriver().findElement(By.cssSelector("select[name='paciente_id']"));
            Select selectPac = new Select(selectEl);
            if (selectPac.getOptions().size() > 1) {
                selectPac.selectByIndex(selectPac.getOptions().size() - 1);
            }
            waitABit(1500);
            List<WebElement> btnsCamara = getDriver().findElements(By.cssSelector("button.btn-camara"));
            if (!btnsCamara.isEmpty()) {
                btnsCamara.get(0).click();
            }
        } catch (Exception ignored) {}
        deslizarPantallaSuave(8);

        // 4. Tratamientos
        clicMenuSeguro("/treatments");
        deslizarPantallaSuave(10);

        // 5. Reportes
        clicMenuSeguro("/reportes");
        deslizarPantallaSuave(10);

        // 6. Consultas Clínicas
        clicMenuSeguro("/consultas");
        waitABit(1500);
        try {
            WebElement selectConsulta = getDriver().findElement(By.cssSelector(".selector-box select"));
            Select selectPacConsulta = new Select(selectConsulta);
            if (selectPacConsulta.getOptions().size() > 1) {
                selectPacConsulta.selectByIndex(selectPacConsulta.getOptions().size() - 1);
            }
            waitABit(800);
            getDriver().findElement(By.cssSelector(".selector-box button")).click();
            waitABit(2000);

            List<WebElement> tabs = getDriver().findElements(By.cssSelector(".tabs-navigation button"));
            for (WebElement tab : tabs) {
                tab.click();
                waitABit(2000);
            }
        } catch (Exception ignored) {}
        deslizarPantallaSuave(4);

        // 7. Ver Pacientes
        clicMenuSeguro("/patients-list");
        deslizarPantallaSuave(10);

        // 8. Alertas IoT
        clicMenuSeguro("/alertas-iot");
        deslizarPantallaSuave(10);

        // 9. Configuración
        clicMenuSeguro("/configuracion");
        deslizarPantallaSuave(6);
    }

    public void cerrarSesion() {
        aceptarAlertaSiExiste();
        WebElement btnSalir = botonCerrarSesion.waitUntilPresent();
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", btnSalir);
        aceptarAlertaSiExiste();
        waitABit(1000);
    }
}