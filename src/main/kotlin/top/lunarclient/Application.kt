package top.lunarclient

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import top.lunarclient.plugins.*

fun main() {
    embeddedServer(Netty, port = System.getProperty("port", "8080").toInt(), host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureSecurity()
    configureHTTP()
    configureSerialization()
    configureSockets()
    configureRouting()
}
