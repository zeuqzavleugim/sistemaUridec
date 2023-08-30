package pruebaDeTesting
import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.testng.annotations.AfterClass
import org.testng.annotations.BeforeClass
import java.io.File

class Estres {

    //<------------------------------------------------variables de las funciones
    var driver: WebDriver? = null

    //-------------------------------------------------segunda ventana, opciones
    private var boton1:     By = By.id("crear")
    //-------------------------------------------------
    //-------------------------------------------------ventana Formulario
    private var botonCrear: By = By.id("crear")
    //-------------------------------------------------
    //-------------------------------------------------ultima ventana/ventana de pruebaDeTesting.estres
    var archivo:    By = By.id("archivo")
    private var files:      By = By.className("swal2-file")
    private var acept:      By = By.className("swal2-confirm")
    private var x:          By = By.className("swal2-close")
    private var subir:      By = By.id("subir")
    //-------------------------------------------------
    private var nDeEstres = 2  //<------------------------------variable de pruebaDeTesting.estres
    //-----------------------------------------------Nombre de pag esperadas
    //var pag1 = "Login Prueba"
    //var pag2 = "Busqueda"
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
    fun test(username:String, password: String) {

        setUp()

        for (i:Int in 1..pfor){
            when(pwen){
                0 ->{
                    try {
                        loginSistemaURIDEC(username, password, driver)
                        pwen += 1

                    }catch (e: Exception){
                        driver!!.quit()
                        archivoExcel(1, "Estres")
                        break
                    }
                }
                1 ->{
                    try {
                        Thread.sleep(500)
                        driver!!.findElement(boton1).click()
                        Thread.sleep(1000)
                        Assert.assertEquals(pag3, driver!!.title)
                        pwen += 1
                    }catch (e: Exception){
                        driver!!.quit()
                        archivoExcel(2, "Estres")
                        break
                    }
                }
                2 ->{
                    try {
                        Thread.sleep(1000)

                        //Llenado_del_formulario
                        formatoFormulario(driver)
                        //fin_del_formulario

                        Thread.sleep(500)
                        driver!!.findElement(botonCrear).click()
                        Thread.sleep(500)

                        pwen += 1
                    }catch (e: Exception){
                        driver!!.quit()
                        archivoExcel(3, "Estres")
                        break
                    }
                }
                3->{
                    try {
                        Thread.sleep(500)
                        val alert = driver!!.switchTo().alert()
                        alert.accept()
                        Thread.sleep(500)
                        Assert.assertEquals(pag4, driver!!.title)
                        Thread.sleep(1000)

                        pwen += 1
                    }catch (e: Exception){
                        driver!!.quit()
                        archivoExcel(4, "Estres")
                        break
                    }
                }
                4->{
                    try {
                        //Inicio_del_for
                        for (j:Int in 1..nDeEstres) {
                            //Inicio_subir_archivo
                            driver!!.findElement(archivo).click()
                            val file = File("src\\main\\resources\\raw\\MANUAL_DE_IDENTIDAD_GRAFICA_2018-2024 (1).pdf")
                            val path = file.absolutePath
                            Thread.sleep(1000)
                            driver!!.findElement(files).sendKeys(path)
                            Thread.sleep(1000)
                            driver!!.findElement(acept).click()
                            Thread.sleep(1000)
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
                            //Final_del_for
                        }
                        tearDown()
                        archivoExcel(0, "Estres")
                        break
                    }catch (e: Exception){
                        driver!!.quit()
                        archivoExcel(5, "Estres")
                        break
                    }
                }
            }
        }
    }
}
