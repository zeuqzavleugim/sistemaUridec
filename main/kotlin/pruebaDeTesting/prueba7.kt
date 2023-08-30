package pruebaDeTesting

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.io.File

class Prueba7{
    //<------------------------------------------------variables de las funciones
    private var driver: WebDriver? = null
    //-------------------------------------------------segunda ventana, opciones
    private var boton1:     By = By.id("crear")

    private var botonCrear: By = By.id("crear")

    var archivo:    By = By.id("archivo")
    private var subir:      By = By.id("subir")

    private var x:          By = By.className("swal2-close")
    private var files:      By = By.className("swal2-file")
    private var acept:      By = By.className("swal2-confirm")
    //-----------------------------------------------Nombre de pag esperadas
   //var pag1 = "Login Prueba"
   // var pag2 = "Busqueda"
    private var pag3 = "Formulario"
    private var pag4 = "Archivos"

    private var pfor = 5
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
    fun prueba7(username:String, password: String) {

        setUp()

        for (i:Int in 1..pfor){
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
                        //Fin_Prueba_de_boton

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

                        Thread.sleep(500)
                        driver!!.findElement(botonCrear).click()
                        Thread.sleep(500)
                        val alert = driver!!.switchTo().alert()
                        alert.accept()
                        Assert.assertEquals(pag4, driver!!.title)
                        Thread.sleep(1000)

                        pwen += 1
                    }catch (e: Exception){
                        driver!!.quit()
                        break
                    }
                }
                3->{
                    try {
                        //Inicio_subir_archivo
                        driver!!.findElement(archivo).click()
                        val file = File("src\\main\\resources\\raw\\MANUAL_DE_IDENTIDAD_GRAFICA_2018-2024 (1).pdf")
                        val path = file.absolutePath
                        Thread.sleep(1000)
                        driver!!.findElement(files).sendKeys(path)
                        Thread.sleep(1000)
                        driver!!.findElement(acept).click()
                        Thread.sleep(1000)

                        pwen += 1
                    }catch (e: Exception){
                        archivoExcel(14, "Unitarias")
                        correoSendGmail("Prueba 7")
                        driver!!.quit()
                        break
                    }
                }
                4->{
                    try {
                        val alert = driver!!.switchTo().alert()
                        alert.accept()
                        Thread.sleep(1000)
                        driver!!.findElement(x).click()
                        Thread.sleep(1000)
                        driver!!.findElement(subir).click()
                        Thread.sleep(500)
                        //Fin_de_prueba
                        Assert.assertEquals(pag4, driver!!.title)
                        Thread.sleep(1000)

                        pwen += 1
                        tearDown()
                        break
                    }catch (e: Exception){
                        archivoExcel(15, "Unitarias")
                        correoSendGmail("Prueba 7")
                        driver!!.quit()
                        break
                    }
                }
            }
        }
    }
}