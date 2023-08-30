package pruebaDeTesting

import java.io.File
import java.util.*
import javax.activation.DataHandler
import javax.activation.FileDataSource
import javax.mail.BodyPart
import javax.mail.Message
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart


fun correoSendGmail(pruebaNum: String){

    val correo = "construyendofuturo1@condusef.gob.mx"
    val password = "Miguel#Angel#Vazquez"
    val correoDestino = "miguelvazquez870@gmail.com"
    val file = File(/* pathname = */ "src\\main\\resources\\raw\\Reporte_URIDEC.xlsx")

    val p = Properties()
    val s: Session = Session.getDefaultInstance(p)

    val texto = MimeBodyPart()
    val adjunto: BodyPart = MimeBodyPart()
    val mensaje = MimeMessage(s)
    val inter = InternetAddress(correoDestino)
    val m = MimeMultipart()
    val t: Transport = s.getTransport(/* protocol = */ "smtp")

    try {
        //587   25
        p["mail.smtp.host"] = "10.33.1.200"
        p["mail.smtp.port"] = "465"
        p["mail.smtp.auth"] = "true"
        p["mail.smtp.starttls.enable"] = "false" //TLS
        p["mail.smtp.ssl.trust"] = "10.33.1.200"
        p["mail.smtp.connectiontimeout"] = "10000"

        texto.setText("Archivo adjunto de la prueba $pruebaNum")

        adjunto.dataHandler = DataHandler(FileDataSource(file))
        adjunto.fileName = "Reporte_URIDEC.xlsx"

        m.addBodyPart(texto)
        m.addBodyPart(adjunto)

        mensaje.setFrom(InternetAddress(correo))
        mensaje.addRecipients(Message.RecipientType.TO, arrayOf(inter))
        mensaje.subject = "Reporte del Sistema URIDEC"
        mensaje.setContent(m)

        t.connect(correo, password)
        t.sendMessage(mensaje, mensaje.allRecipients)
        t.close()

    }catch (e: Exception){
        // para el correo: sistemauridec@gmail.com
        // su contraseña es: bqyzmwrpcqukcihw

        val correo1 = "sistemauridec@gmail.com"
        val password1 = "bqyzmwrpcqukcihw"


        p["mail.smtp.host"] = "smtp.gmail.com"
        p["mail.smtp.ssl.trust"] = "smtp.gmail.com"
        p.setProperty("mail.smtp.starttls.enable","true")
        p.setProperty("mail.smtp.port","587")
        p.setProperty("mail.smtp.user",correo1)
        p.setProperty("mail.smtp.ssl.protocols", "TLSv1.2")
        p.setProperty("mail.smtp.auth","true")

        texto.setText("Archivo adjunto de la prueba $pruebaNum")

        adjunto.dataHandler = DataHandler(FileDataSource(file))
        adjunto.fileName = "Reporte_URIDEC.xlsx"

        m.addBodyPart(texto)
        m.addBodyPart(adjunto)

        mensaje.setFrom(InternetAddress(correo1))
        mensaje.addRecipients(Message.RecipientType.TO, arrayOf(inter))
        mensaje.subject = "Reporte del Sistema URIDEC"
        mensaje.setContent(m)

        t.connect(correo1, password1)
        t.sendMessage(mensaje, mensaje.allRecipients)
        t.close()
    }
}