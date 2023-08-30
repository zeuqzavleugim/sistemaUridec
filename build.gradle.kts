import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm") version "1.9.0"
    id("org.jetbrains.compose") version "1.4.3"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}


dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    implementation(compose.materialIconsExtended)

    implementation("org.seleniumhq.selenium:selenium-java:4.11.0")
    implementation("junit:junit:4.13.2")
    implementation("org.apache.poi:poi:5.2.3")
    implementation("org.testng:testng:7.8.0")
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("javax.mail:mail:1.5.0-b01")
    implementation("org.apache.logging.log4j:log4j-api-kotlin:1.2.0")

}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "uridecSistema"
            packageVersion = "1.0.0"
        }
    }
}
