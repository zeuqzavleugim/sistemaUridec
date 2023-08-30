import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import pruebaDeTesting.archivoExcel
import pruebaDeTesting.correoSendGmail

@Composable
@Preview
fun App() {
    var user: String by remember { mutableStateOf(value = "") }
    var password: String by remember { mutableStateOf(value = "") }
    var passVisible: Boolean by remember { mutableStateOf(value = false) }
    var radio1: Boolean by remember { mutableStateOf(value = false) }
    var radio2: Boolean by remember { mutableStateOf(value = false) }
    val buttonEnabled: Boolean = user.isNotEmpty() && password.isNotEmpty() && (radio1 || radio2)

    val unitaria1 = pruebaDeTesting.Unitarias()
    val unitaria2 = pruebaDeTesting.Prueba2()
    val unitaria3 = pruebaDeTesting.Prueba3()
    val unitaria4 = pruebaDeTesting.Prueba4()
    val unitaria5 = pruebaDeTesting.Prueba5()
    val unitaria6 = pruebaDeTesting.Prueba6()
    val unitaria7 = pruebaDeTesting.Prueba7()
    val unitaria8 = pruebaDeTesting.Prueba8()
    val estres = pruebaDeTesting.Estres()
    val pfor = 9
    var pWen = 0
    MaterialTheme {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.paint(painter = painterResource("imag/fondo.png"), contentScale = ContentScale.Crop)
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource("imag/condusef2.png"),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Start).size(width = 150.dp, height = 75.dp)
                )
                Text(
                    text = "Bienvenido \n Este programa tiene la funion de poner a prueba el sistema llamado 'Digitalizador " +
                            "de expedientes' (URIDEC), para realizar la prueba es necesario su usuario y contraseña",
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.size(30.dp))
                Text(
                    text = "Nota: Al iniciar la prueba no se podrá hacer uso de la computadora hasta finalizar " +
                            "el proceso y vea que no aparece su usuario y contraseña en esta ventana, por lo que " +
                            "le pedimos este prevenido a sus actividades cotidianas" +
                            "\nNota: Favor de revisar que su usuario y contraseña sean correctos antes de iniciar la prueba",
                    textAlign = TextAlign.Justify,
                    color = Color.Red,
                    fontSize = 13.sp
                )
                Spacer(Modifier.size(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().height(200.dp), horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(Modifier.size(10.dp))
                    Column(modifier = Modifier.size(200.dp)) {
                        OutlinedTextField(
                            value = user,
                            onValueChange = { user = it },
                            label = { Text("User") },
                            colors = TextFieldDefaults.textFieldColors(Color.Black)
                        )
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(("Password")) },
                            visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconToggleButton(checked = passVisible, onCheckedChange = { passVisible = it }) {
                                    Icon(
                                        imageVector = if (passVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                        contentDescription = null
                                    )
                                }
                            },
                            colors = TextFieldDefaults.textFieldColors(Color.Black)
                        )
                    }
                    Column(modifier = Modifier.size(200.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        RadioButton(
                            selected = radio1,
                            onClick = {radio1 = true; radio2 = false}
                        )
                        Text(text = "Pruebas Unitarias")
                        Spacer(Modifier.size(10.dp))
                        RadioButton(
                            selected = radio2,
                            onClick = {radio2 = true; radio1 = false}
                        )
                        Text(text = "Prueba de Estres")
                    }
                    Column(modifier = Modifier.size(300.dp)) {
                        Spacer(Modifier.size(10.dp))
                        Text(
                            text = "Las pruebas pruebas unitarias son usadas para calificar el comportamiento " +
                                    "del sistema en sus diferentes acciones",
                            textAlign = TextAlign.Justify
                        )
                        Spacer(Modifier.size(30.dp))
                        Text(
                            text = "La prueba de estestres tiene la finalidad de ver el comportamiento del sistema " +
                                    "para calificar su rendimiento a eventos extremos",
                            textAlign = TextAlign.Justify
                        )
                    }
                }
                Button(onClick = {
                    if (radio1 && !radio2){
                        for (i:Int in 1.rangeTo(pfor)) {
                            when(pWen){
                                0->{
                                    try {
                                        unitaria1.prueba1(user, password)
                                        pWen += 1
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                                1->{
                                    try {
                                        unitaria2.prueba2(user, password)
                                        pWen += 1
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                                2->{
                                    try {
                                        unitaria3.prueba3(user, password)
                                        pWen += 1
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                                3->{
                                    try {
                                        unitaria4.prueba4(user, password)
                                        pWen += 1
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                                4->{
                                    try {
                                        unitaria5.prueba5(user, password)
                                        pWen += 1
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                                5->{
                                    try {
                                        unitaria6.prueba6(user, password)
                                        pWen += 1
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                                6->{
                                    try {
                                        unitaria7.prueba7(user, password)
                                        pWen += 1
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                                7->{
                                    try {
                                        unitaria8.prueba8(user, password)
                                        pWen += 1
                                        break
                                    }catch (e: Exception){
                                        correoSendGmail(pruebaNum = "Unitarias")
                                        break
                                    }
                                }
                            }
                        }
                        user = ""
                        password = ""
                        radio1 = false
                        radio2 = false
                    }
                    if (radio2 && !radio1){
                        try {
                            estres.test(user, password)
                            correoSendGmail(pruebaNum = "Estres")
                        }catch (e: Exception){
                            archivoExcel(valor = 1, prueba = "Estres")
                            correoSendGmail(pruebaNum = "Estres")
                            user = ""
                            password = ""
                            radio1 = false
                            radio2 = false
                        }
                    }
                    user = ""
                    password = ""
                    radio1 = false
                    radio2 = false
                }, enabled = buttonEnabled) {
                    Text(text = "Iniciar")
                }
                Spacer(Modifier.size(10.dp))
                Image(
                    painter = painterResource("imag/piepag.png"),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.CenterHorizontally).size(width = 250.dp, height = 125.dp)
                )
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Sistema de pruebas a URIDEC") {
        App()
    }
}
