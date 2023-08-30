package pruebaDeTesting

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver

fun formatoFormulario(driver: WebDriver?){
    val area: By = By.name("area_de_procedencia")
    val tipos: By = By.name("tipos_de_macros")
    val macro: By = By.name("macros")
    val procesos: By = By.name("proceso")
    val series: By = By.name("serie")
    val fechas: By = By.name("fecha")
    val folio: By = By.name("folio")
    val formato: By = By.name("formato")

    //Llenado_del_formulario
    val areaProcedencia = driver!!.findElement(area)
    areaProcedencia.sendKeys("fintec")
    val tiposMacros = driver.findElement(tipos)
    tiposMacros.sendKeys("macros")
    val macros = driver.findElement(macro)
    macros.sendKeys("hola")
    val proceso = driver.findElement(procesos)
    proceso.sendKeys("proseso")
    val serie = driver.findElement(series)
    serie.sendKeys("321654")
    val fecha = driver.findElement(fechas)
    fecha.sendKeys("19062023")
    val folios = driver.findElement(folio)
    folios.sendKeys("9876543")
    val formatos = driver.findElement(formato)
    formatos.sendKeys("asdasd")
    //fin_del_formulario

}