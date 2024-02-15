package top.lunarclient.plugins

import freemarker.cache.ClassTemplateLoader
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.*
import io.ktor.server.http.content.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*
import top.lunarclient.websiteConfig


fun Application.configureRouting() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(IgnoreTrailingSlash)


    routing {
        get("/") {
            call.respondFTL("index.ftl", mapOf("title" to websiteConfig.title, "description" to websiteConfig.description))
        }

        get("/help") {
            call.respondFTL("help.ftl")
        }

        get("/api") {
            call.respondRedirect("/help")
        }

        get("/dashboard/login") {
            call.respondFTL("dashboard/login.ftl")
        }

        get("/dashboard/register") {
            call.respondFTL("dashboard/register.ftl")
        }

        get("/download/{hash}") {
            val hash = call.parameters["hash"]
            if (fileHashMap.containsKey(hash)) {
                call.respond(fileHashMap[hash]!!.inputStream())
            } else {
                call.respond(HttpStatusCode.NotFound, "Not found")
            }
        }

        authenticate("auth-form") {
            post("/dashboard/login") {
                call.sessions.set(call.principal<UserSession>())
                call.respondRedirect("/dashboard")
            }

            post("/dashboard/register") {
                call.respondRedirect("/dashboard")
            }
        }

        authenticate("auth-session") {
            get("/dashboard") {
                val data = mapOf("user" to (call.authentication.principal<UserSession>()?.name ?: "Unknown User"))
                call.respondFTL("dashboard/index.ftl", data)
            }

            get("/dashboard/{page}") {
                val pageName = call.parameters["page"]
                val data = when (pageName) {
                    "home" -> {
                        mapOf("username" to call.authentication.principal<UserSession>()?.name)
                    }
                    else -> {null}
                }
                call.respondFTL("dashboard/pages/$pageName.ftl", data)
            }

            get("/dashboard/logout") {
                call.sessions.clear<UserSession>()
                call.respondRedirect("/dashboard/login")
            }
        }

        staticResources("/", "static")
    }
}

private suspend fun ApplicationCall.respondFTL(template: String, model: Any? = null) {
    this.respond(FreeMarkerContent(template, model))
}
