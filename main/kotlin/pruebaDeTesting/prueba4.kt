package pruebaDeTesting

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

class Prueba4 {
    //<------------------------------------------------variables de las funciones
    private var driver: WebDriver? = null

    //-------------------------------------------------segunda ventana, opciones
    private var boton1:     By = By.id("crear")

    //-------------------------------------------------formulario
    private var area:       By = By.name("area_de_procedencia")

    var archivo:    By = By.id("archivo")
    private var cancelar:   By = By.name("cancelar")
    var files:      By = By.className("swal2-file")
    //-----------------------------------------------Nombre de pag esperadas
    private var pag3 = "Formulario"

    private var pfor = 3
    private var pwen = 0

    @BeforeClass
    fun setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/main/resources/raw/chromedriver.exe")
        driver = ChromeDriver()
        (driver as ChromeDriver).manage().window().maximize()
        (driver as ChromeDriver).get("http://10.33.7.75:8080/proyect_URIDEC/Index.html")
    }

    @AfterClass
    fun tearDown() {
        driver!!.quit()
    }

    @org.testng.annotations.Test
    fun prueba4(username:String, password: String) {

        setUp()

        for (i in 1..pfor){
            when(pwen){
                0->{
                    try {
                        loginSistemaURIDEC(username, password, driver)
                        pwen += 1
                    }catch (e: Exception){
                        driver!!.quit()
                        break
                    }
                }
                1->{
                    try {
                        //Fin_de_autentificacion
                        //Inicio_Prueba_de_boton
                        driver!!.findElement(boton1).click()
                        Thread.sleep(1000)
                        Assert.assertEquals(pag3, driver!!.title)
                        Thread.sleep(1000)

                        pwen += 1
                    }catch (e: Exception){
                        driver!!.quit()
                        break
                    }
                }
                2->{
                    try {
                        //Llenado_del_formulario
                        formatoFormulario(driver)
                        //fin_del_formulario

                        Thread.sleep(1000)
                        driver!!.findElement(cancelar).click()
                        Thread.sleep(2000)
                        driver!!.findElement(area).isDisplayed
                        //Fin_Prueba_de_boton

                        tearDown()
                        pwen += 1
                        break
                    }catch (e: Exception){
                        archivoExcel(11, "Unitarias")
                        correoSendGmail("Prueba 4")
                        driver!!.quit()
                        break
                    }

                }
            }
        }
    }
}