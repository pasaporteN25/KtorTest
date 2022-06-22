package com.example

import io.ktor.application.*
import com.example.plugins.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h3
import kotlinx.html.head
import kotlinx.html.title

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    //configureRouting()
    install(CallLogging)
    routing {
        static {
            resources("static")
        }
        get("/") {
            call.respondText("Hola Mundo! 2")
        }
        get("/welcome") {
            val name = call.request.queryParameters["name"]
            call.respondHtml {
                head {
                    title {
                        + "Custom Title"
                    }
                }
                body {
                    if (name.isNullOrEmpty()){
                        h3 { + "Welcome!" }
                    }else{
                        h3 { +"Welcome, $name!" }
                    }
                }
            }
        }
    }
}
