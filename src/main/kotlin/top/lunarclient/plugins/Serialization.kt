package top.lunarclient.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.annotations.Contract
import top.lunarclient.JSON
import top.lunarclient.artifactsDir
import top.lunarclient.configDir
import top.lunarclient.websiteConfig
import java.io.File


@Serializable
data class LunarVersion(
    val id: String,
    @SerialName("default")
    val isDefault: Boolean,
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
data class LunarModuleConfig(
    @SerialName("default")
    val isDefault: Boolean = false,
    val artifacts: List<GameArtifactInfo.Artifact> = emptyList(),
    val textures: GameArtifactInfo.Textures? = null
)

@Serializable
data class LunarModule(
    val id: String,
    val default: Boolean,
) {


    companion object {
        fun isDefault(file: File): Boolean = JSON.decodeFromString<LunarModuleConfig>(file.readText()).isDefault
    }
}

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

private fun findVersions(): List<LunarVersion> {
    val list = mutableListOf<LunarVersion>()
    artifactsDir.listFiles()?.forEach { file ->
        if (file.isDirectory) {
            val versionID = file.name
            list.add(
                LunarVersion(
                    versionID,
                    websiteConfig.defaultVersion.let { it.substring(0, it.lastIndexOf('.')) } == versionID,
                    findSubVersions(versionID)))
        }
    }
    return list
}

private fun findSubVersions(id: String): List<LunarSubVersion> {
    val list = mutableListOf<LunarSubVersion>()
    artifactsDir.resolve(id).listFiles()?.forEach { file ->
        if (file.isDirectory) {
            val versionID = file.name
            list.add(LunarSubVersion(versionID, websiteConfig.defaultVersion == versionID, findModules(versionID)))
        }
    }
    return list
}

private fun findModules(id: String): List<LunarModule> {
    val list = mutableListOf<LunarModule>()
    val majorId = id.substring(0, id.lastIndexOf('.'))
    artifactsDir.resolve(majorId).resolve(id).listFiles()?.forEach { file ->
        if (file.isFile && file.name.endsWith(".json")) {
            val moduleID = file.name.substring(0, file.name.lastIndexOf('.'))
            list.add(LunarModule(moduleID, LunarModule.isDefault(file)))
        }
    }
    return list
}

private fun findBlogposts(): List<Blogpost> = configDir.resolve("blogposts.json").let { file ->
    if (file.exists()) JSON.decodeFromString<List<Blogpost>>(file.readText()) else emptyList()
}
