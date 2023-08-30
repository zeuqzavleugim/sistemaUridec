package pruebaDeTesting

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass

class Unitarias {
    //<------------------------------------------------variables de las funciones
    var driver: WebDriver? = null
    //-------------------------------------------------primer ventana
    //-------------------------------------------------
    private var salir:      By = By.id("salir")
    //-------------------------------------------------formulario
    var archivo:    By = By.id("archivo")
    var files:      By = By.className("swal2-file")
    //-----------------------------------------------Nombre de pag esperadas
    private var pag1 = "Login Prueba"

    private var pfor = 2
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
    fun prueba1(username:String, password: String) {

        setUp()

        for (i in 1..pfor){
            when(pwen){
                0->{
                    try {
                        loginSistemaURIDEC(username, password, driver)
                        pwen += 1

                    }catch (e: Exception){
                        archivoExcel(6, "Unitaria")
                        driver!!.quit()
                        break
                    }
                }
                1->{
                    try {
                        driver!!.findElement(salir).click()
                        Thread.sleep(1000)
                        Assert.assertEquals(pag1, driver!!.title)
                        //Fin_de_autentificacion
                        tearDown()
                        pwen += 1
                        break
                    }catch (e: Exception){
                        archivoExcel(7, "Unitaria")
                        correoSendGmail("prueba 1")
                        driver!!.quit()
                        break
                    }
                }
            }
        }
    }
}