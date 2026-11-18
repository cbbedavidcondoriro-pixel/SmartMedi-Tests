package com.swag.pe.definitions;

import com.swag.pe.pages.SmartMediLoginPage;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import org.junit.Assert;

public class LoginDefinitions {

    SmartMediLoginPage loginPage;

    @Dado("que el usuario abre el navegador en la página principal de SmartMedi")
    public void abrirPaginaHome() {
        loginPage.abrirHome();
    }

    @Dado("que el usuario abre el navegador en la página de inicio de sesión de SmartMedi")
    public void abrirPaginaLogin() {
        loginPage.abrirLogin();
    }

    @Cuando("espera {int} segundos y hace clic en Ingresar al Sistema desde el inicio")
    public void esperarYClicEnHome(int segundos) {
        loginPage.esperarSegundos(segundos);
        loginPage.clicIngresarDesdeHome();
    }

    @Cuando("ingresa el usuario {string} y la contraseña {string}")
    public void ingresarCredenciales(String usuario, String password) {
        loginPage.escribirUsuario(usuario);
        loginPage.escribirPassword(password);
    }

    @Cuando("hace clic en el botón Ingresar al Sistema")
    public void hacerClicIngresar() {
        loginPage.clicIngresar();
    }

    @Entonces("el sistema debe redirigirlo al panel principal del médico")
    public void verificarPanelMedico() {
        String urlActual = loginPage.obtenerUrlActual();
        Assert.assertTrue("Se esperaba entrar a /dashboard pero la URL es: " + urlActual,
                urlActual.contains("/dashboard") && !urlActual.contains("farmaceutico"));
    }

    @Cuando("recorre los módulos médicos interactuando con vistas y deslizando 10 segundos por pantalla")
    public void recorrerModulosMedico() {
        loginPage.recorrerModulosMedicoInteractivo();
    }

    @Entonces("cierra la sesión del médico correctamente")
    public void cerrarSesionMedico() {
        loginPage.cerrarSesion();
        String urlActual = loginPage.obtenerUrlActual();
        Assert.assertTrue("Se esperaba volver al inicio o login al cerrar sesión: " + urlActual,
                urlActual.endsWith("/") || urlActual.contains("/login"));
    }

    @Entonces("el sistema debe redirigirlo al panel principal del farmacéutico")
    public void verificarPanelFarmaceutico() {
        String urlActual = loginPage.obtenerUrlActual();
        Assert.assertTrue("Se esperaba entrar a /dashboard-farmaceutico pero la URL es: " + urlActual,
                urlActual.contains("/dashboard-farmaceutico"));
    }

    @Entonces("el sistema debe mantener al usuario en la pantalla de inicio de sesión")
    public void verificarPermanenciaEnLogin() {
        String urlActual = loginPage.obtenerUrlActual();
        Assert.assertTrue("El usuario no debería haber salido de /login: " + urlActual,
                urlActual.contains("/login"));
    }
}