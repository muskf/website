package top.lunarclient.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Contract
import top.lunarclient.websiteConfig
import java.net.URL


@Serializable
data class LunarVersion(
    val id: String,
    val default: Boolean,
    val subversions: List<LunarSubVersion>
)

@Serializable
data class LunarSubVersion(
    val id: String,
    val default: Boolean,
    val modules: List<LunarModule>
)

@Serializable
data class GameArtifactInfo(
    val launchTypeData: LaunchTypeData,
    val textures: Textures,
    val jre: RuntimeInfo
) {
    @Serializable
    data class RuntimeInfo(
        val extraArguments: List<String>
    )

    @Serializable
    data class Artifact(
        val name: String,
        val sha1: String,
        val url: String,
        val type: ArtifactType
    ) {
        enum class ArtifactType {
            CLASS_PATH,
            EXTERNAL_FILE,
            NATIVES,
            JAVAAGENT // LCCN API, not support yet
        }
    }

    @Serializable
    data class LaunchTypeData(
        val artifacts: List<Artifact>,
        val mainClass: String,
        val ichor: Boolean = true
    )

    @Serializable
    data class Textures(
        val indexUrl: String,
        val indexSha1: String,
        val baseUrl: String
    )
}

@Serializable
data class LunarModule(
    val id: String,
    val default: Boolean,
)

@Serializable
data class Blogpost(
    val title: String,
    val excerpt: String? = null, // only available on LCCN API
    val image: String,
    val link: String,
    val author: String? = null, // moonsworth removed this in v3.0.0
    @SerialName("button_text")
    val buttonText: String? = null
)

@Serializable
data class Alert(
    val name: String? = "Failed to get title",
    val text: String? = null
)

@Serializable
data class LauncherMetadata(
    val versions: List<LunarVersion>,
    @SerialName("blogPosts")
    val blogposts: List<Blogpost> = emptyList(),
    val alert: Alert? = null
)

@Serializable
data class PluginInfo(
    val name: String,
    val sha1: String,
    val downloadLink: String,
    val category: Category,
    val meta: AddonMeta? = null
) {
    enum class Category {
        AGENT, CN, WEAVE;

        companion object {
            /**
             * Parse plugin type from a string
             */
            @Contract(pure = true)
            fun parse(category: String): Category? {
                return when (category) {
                    "cn" -> CN
                    "weave", "Mod" -> WEAVE
                    "Agent" -> AGENT
                    else -> null
                }
            }
        }
    }

    @Serializable
    data class AddonMeta(
        val name: String,
        val version: String,
        val description: String,
        val authors: List<String>,
        val website: String?,
        val repository: String?,
        val dependencies: List<String>?
    )
}

@Serializable
data class CrashReportResult(
    val id: String,
    val url: String,
    val message: String
)


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/api/launcher/metadata") {
            call.respond(LauncherMetadata(findVersions(), findBlogposts(), websiteConfig.alert))
        }
    }
}

private fun findVersions() : List<LunarVersion> {
    TODO("WIP")
}

private fun findBlogposts() : List<Blogpost> {
    TODO("WIP")
}
