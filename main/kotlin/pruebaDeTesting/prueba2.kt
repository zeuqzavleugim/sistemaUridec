package pruebaDeTesting

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

class Prueba2 {
    //<------------------------------------------------variables de las funciones
    private var driver: WebDriver? = null

    //-------------------------------------------------segunda ventana, opciones
    private var boton1:     By = By.id("crear")
    //-------------------------------------------------
    //-------------------------------------------------
    private var regresar:   By = By.id("regresar")

    var archivo:    By = By.id("archivo")
    var files:      By = By.className("swal2-file")
    //-----------------------------------------------Nombre de pag esperadas
    private var pag2 = "Busqueda"
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
    fun prueba2(username:String, password: String) {

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
                        //Inicio_Prueba_de_boton
                        driver!!.findElement(boton1).click()
                        Thread.sleep(1000)
                        Assert.assertEquals(pag3, driver!!.title)
                        Thread.sleep(1000)

                        pwen += 1
                    }catch (e: Exception){
                        archivoExcel(8, "Unitaria")
                        correoSendGmail("Prueba 2")
                        driver!!.quit()
                        break
                    }
                }
                2->{
                    try {
                        driver!!.findElement(regresar).click()
                        Assert.assertEquals(pag2, driver!!.title)
                        Thread.sleep(1000)
                        //Fin_Prueba_de_boton

                        tearDown()
                        break
                    }catch (e: Exception){
                        archivoExcel(9, "Unitaria")
                        correoSendGmail("Prueba 2")
                        driver!!.quit()
                        break
                    }
                }
            }
        }
    }
}