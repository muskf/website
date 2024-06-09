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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import okhttp3.OkHttpClient
import okhttp3.Request
import top.lunarclient.*

var syncHash: String? = null
val httpClient = OkHttpClient()


fun Application.configureRouting() {
    install(FreeMarker) {
        templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
    }
    install(IgnoreTrailingSlash)


    routing {
        suspend fun checkOfficial(call: ApplicationCall, function: suspend (call: ApplicationCall) -> Unit) {
            if (!(BLOCK_UNOFFICIAL_HOST && KNOWN_HOSTS.contains(call.request.host()))) {
                call.respondFTL("blocked.ftl")
                return
            }
            function(call)
        }

        get("/") {
            checkOfficial(call) { call ->
                call.respondFTL("index.ftl")
            }
        }

        get("/lccn") {
            checkOfficial(call) { call ->
                call.respondFTL(
                    "celestial/index.ftl",
                    mapOf("title" to websiteConfig.title, "description" to websiteConfig.description)
                )
            }
        }

        get("/help") {
            call.respondFTL("help.ftl")
        }

        get("/weareback") {
            call.respondFTL("back.ftl")
        }

        get("/docs") {
            call.respondRedirect("/help")
        }

        get("/celestial") {
            call.respondRedirect("/lccn")
        }

        get("/celestial/gh") {
            // github
            call.respondRedirect("https://github.com/CubeWhyMC/celestial")
        }

        get("/lunarcn") {
            call.respondRedirect("/lccn")
        }

        get("/weareback") {
            call.respondRedirect("/weareback")
        }

        get("/donate") {
//            val ua = call.request.headers["User-Agent"]!!
//            if ("micromessenger" in ua) {
//                // WeChat
//                // TODO Redirect wechat
//            } else if ("AlipayClient" in ua) {
//                // Alipay
//                // TODO Redirect Alipay
//            } else call.respondFTL("donate.ftl")
            checkOfficial(call) { call ->
                call.respondFTL("donate.ftl")
            }
        }

        get("/api") {
            call.respondRedirect("/help")
        }

        get("/download") {
            call.respondRedirect("/help/#/celestial/download")
        }

        get("/mirror") {
            // 镜像源移到 mirror.mcip.link
            call.respondRedirect("/help/#/website/mirror")
        }
//
//        get("/mirror/textures/{path...}") {
//            val path = call.parameters.getAll("path")
//            val target = path!!.joinToString("/")
//            val targetFile = mirrorDir.resolve("textures").resolve(target)
//            if (targetFile.exists()) call.respond(targetFile.inputStream())
//            else {
//                // cache
//                val url = "https://textures.lunarclientcdn.com/$target"
//                val request = Request.Builder()
//                    .url(url)
//                    .get()
//                    .build()
//                with(httpClient.newCall(request).execute()) {
//                    this.body?.bytes()?.let { it ->
//                        targetFile.apply {
//                            targetFile.parentFile.mkdirs()
//                            createNewFile()
//                            writeBytes(it)
//                        }
//                    }
//                }
//                call.respond(targetFile.inputStream())
//            }
//        }

        post("/webhook/gh-release") {
            val file = configDir.resolve("celestial-cached.jar")
            val json = JSON.decodeFromString<ReleaseWebHook>(call.receiveText())
            if (json.action == null) {
                // just ping
                call.respond(204)
                return@post
            }
            val url = json.release.assets[0].downloadUrl
            val request = Request.Builder()
                .get()
                .url(url)
                .build()
            with(httpClient.newCall(request).execute()) {
                this.body?.bytes()?.let { it1 ->
                    file.writeBytes(it1)
                    syncHash = sha1(file)
                }
            }
            call.respond(204)
        }

        get("/dashboard/login") {
            call.respondFTL("dashboard/login.ftl")
        }

        get("/dashboard/register") {
            call.respondFTL("dashboard/register.ftl")
        }

        get("/link/{name}") {
            val name = call.parameters["name"]
            call.respondRedirect(
                when (name) {
                    "celestial" -> {
                        if (syncHash == null) {
                            "/help/#/celestial/download"
                        } else {
                            "/download/$syncHash"
                        }
                    }

                    else -> "/"
                }
            )
        }


        get("/api/download/{hash}") {
            // 兼容celestial
            val hash = call.parameters["hash"]
            call.respondRedirect("/download/${hash}")
        }

        get("/download/{hash}") {
            val hash = call.parameters["hash"]
            if (fileHashMap.containsKey(hash)) {
                call.respond(fileHashMap[hash]!!.inputStream())
            } else {
                call.respond(HttpStatusCode.NotFound, "Not found")
            }
        }

        get("/s/{name}") {
            // short link
            val name = call.parameters["name"]!!
            val target = shortLinkConfig[name]?.target
            if (target != null) {
                call.respondRedirect(target) // do redirect
            } else {
                call.respond(HttpStatusCode.NotFound, "Not found")
            }
        }

        authenticate("auth-form") {
            post("/dashboard/login") {
                call.sessions.set(call.principal<UserInfo>())
                call.respondRedirect("/dashboard")
            }

            post("/dashboard/register") {
                call.respondRedirect("/dashboard")
            }
        }

        authenticate("auth-session") {
            get("/dashboard") {
                val data = mapOf("user" to (call.authentication.principal<UserInfo>()?.username ?: "Unknown User"))
                call.respondFTL("dashboard/index.ftl", data)
            }

            get("/dashboard/{page}") {
                val pageName = call.parameters["page"]
                val data = when (pageName) {
                    "home" -> {
                        mapOf(
                            "username" to call.authentication.principal<UserInfo>()?.username,
                            "role" to call.authentication.principal<UserInfo>()?.role
                        )
                    }

                    else -> {
                        null
                    }
                }
                call.respondFTL("dashboard/pages/$pageName.ftl", data)
            }

            post("/dashboard/short-link/add") {
                val linkName = call.request.queryParameters["name"]
                val linkTarget = call.request.queryParameters["target"]
                // create a fast link
                if (linkTarget != null && linkName != null) {
                    shortLinkConfig.add(linkName, linkTarget)
                    shortLinkConfig.save() // save
                    call.respond("OK!")
                    return@post
                }
                call.respond("NULL!")
            }

            get("/dashboard/logout") {
                call.sessions.clear<UserInfo>()
                call.respondRedirect("/dashboard/login")
            }
        }

        staticResources("/", "static")
    }
}

@Serializable
data class ReleaseWebHook(
    val action: String? = null,
    val release: Release
) {
    @Serializable
    data class Release(
        val assets: List<Asset>
    )

    @Serializable
    data class Asset(
        val name: String,
        @SerialName("browser_download_url")
        val downloadUrl: String
    )
}

suspend fun ApplicationCall.respondFTL(template: String, model: Any? = null) {
    this.respond(FreeMarkerContent(template, model))
}
