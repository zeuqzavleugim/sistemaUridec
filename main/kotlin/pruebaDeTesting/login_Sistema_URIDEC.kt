package pruebaDeTesting

import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun loginSistemaURIDEC(username:String, password: String, driver: WebDriver?){
    //<------------------------------------------------variables de las funciones
    //-------------------------------------------------primer ventana
    val usernam: By = By.id("username")
    val passwor: By = By.id("password")
    val ingresar: By = By.id("3")
    //-------------------------------------------------
    val pag2 = "Busqueda"

    //Inicio_de_autentificacion
    val searchbox = driver!!.findElement(usernam)
    searchbox.clear()                                           //Limpiamos_el_elemento
    searchbox.sendKeys(username)                                //Escribimos_dentro_del_elemento
    Thread.sleep(500)
    val searchbox1 = driver.findElement(passwor)
    searchbox1.clear()                                          //Limpiamos_el_segundo_elemento
    searchbox1.sendKeys(password)                               //Escribimos_dentro_del_elemento
    Thread.sleep(500)
    driver.findElement(ingresar).click()                      //Damos_clik_en_el_elemento_para_evaluar
    Thread.sleep(1000)
    Assert.assertEquals(pag2, driver.title)
}