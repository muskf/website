package top.lunarclient.plugins

import cn.hutool.crypto.SecureUtil
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import org.jetbrains.annotations.Contract
import top.lunarclient.*
import java.io.File
import java.util.jar.JarFile


val fileHashMap = HashMap<String, File>()

@Serializable
data class LunarVersion(
    val id: String, @SerialName("default") val isDefault: Boolean, val subversions: List<LunarSubVersion>
)

@Serializable
data class LunarSubVersion(
    val id: String, val default: Boolean, val modules: List<LunarModule>
)

@Serializable
data class GameArtifactInfo(
    val launchTypeData: LaunchTypeData, val textures: Textures?, val jre: RuntimeInfo
) {
    @Serializable
    data class RuntimeInfo(
        val extraArguments: List<String>
    )

    @Serializable
    data class Artifact(
        val name: String, val sha1: String, val url: String, val type: ArtifactType
    ) {
        enum class ArtifactType {
            CLASS_PATH, EXTERNAL_FILE, NATIVES, JAVAAGENT // LCCN API, not support yet
        }
    }

    @Serializable
    data class LaunchTypeData(
        val artifacts: List<Artifact>, val mainClass: String, val ichor: Boolean = true
    )

    @Serializable
    data class Textures(
        val indexUrl: String, val indexSha1: String, val baseUrl: String
    )
}

@Serializable
data class LunarModuleConfig(
    @SerialName("default") val isDefault: Boolean = false,
    val artifacts: List<GameArtifactInfo.Artifact> = emptyList(),
    val textures: GameArtifactInfo.Textures? = null,
    val mainClass: String = "com.moonsworth.lunar.genesis.Genesis"
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
data class LauncherBlogpost(
    val title: String, val excerpt: String? = null, // only available on LCCN API
    val image: String, val link: String, val author: String? = null, // moonsworth removed this in v3.0.0
    @SerialName("button_text") val buttonText: String? = null,
    val type: ButtonType? = ButtonType.OPEN_LINK
) {
    enum class ButtonType {
        OPEN_LINK,
        CHANGE_API
    }
}

@Serializable
data class GameBlogpost(
    val title: String
)

@Serializable
data class Alert(
    val name: String? = "Failed to get title", val text: String? = null
)

@Serializable
data class LauncherMetadata(
    val versions: List<LunarVersion>,
    @SerialName("blogPosts") val blogposts: List<LauncherBlogpost> = emptyList(),
    val alert: Alert? = null
)

@Serializable
data class PluginInfo(
    val name: String, val sha1: String, val downloadLink: String, val category: Category, val meta: AddonMeta? = null
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
    val id: String, val url: String, val message: String
)

@Serializable
data class GameMetadata(
    @SerialName("blogPosts") val blogposts: List<GameBlogpost> = emptyList()
)


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }

    routing {
        get("/api/launcher/metadata") {
            call.respond(LauncherMetadata(findVersions(), findBlogposts(), websiteConfig.alert))
        }

        post("/api/launcher/launch") {
            val info: GameVersionInfo = JSON.decodeFromString(call.receiveText())
            val natives = info.findNatives()
            val json = JSON.decodeFromString<LunarModuleConfig>(
                info.version.resolveFolder().resolveModule(info.module).readText()
            )
            call.respond(
                GameArtifactInfo(
                    GameArtifactInfo.LaunchTypeData(
                        info.findArtifacts(json, natives), json.mainClass
                    ), json.textures, GameArtifactInfo.RuntimeInfo(websiteConfig.defaultVMArgs)
                )
            )
        }


        get("/api/plugins/info") {
            val pluginList = mutableListOf<PluginInfo>()
            // find plugins
            pluginsDir.listFiles()?.forEach { file ->
                if (file.isDirectory) {
                    val category = when (file.name) {
                        "agents" -> PluginInfo.Category.AGENT
                        "lunarcn" -> PluginInfo.Category.CN
                        "weave" -> PluginInfo.Category.WEAVE
                        else -> null // unknown type
                    }
                    if (category != null) {
                        file.listFiles()?.forEach { addon ->
                            val hash = sha1(addon)
                            pluginList.add(
                                PluginInfo(
                                    addon.name, hash, "/download/${hash}", category,
                                    addon.readMeta()
                                )
                            )
                        }
                    }
                }
            }
            call.respond(pluginList)
        }

        get("/api/game/metadata") {
            call.respond(GameMetadata())
        }
    }
}

private fun File.readMeta(): PluginInfo.AddonMeta? {
    val jar = JarFile(this)
    val meta = try {
        val bytes = jar.getInputStream(jar.getEntry("addon.meta.json")).readAllBytes()
        JSON.decodeFromString<PluginInfo.AddonMeta>(String(bytes))
    } catch (e: Exception) {
        null
    }
    return meta
}

@Serializable
data class GameVersionInfo(
    // for getting artifacts
    val version: String, val module: String, val branch: String,
    // for getting natives
    val os: String, val arch: String
)

@Serializable
data class Natives(
    val name: String, val sha1: String, val url: String
) {
    fun toArtifact() =
        GameArtifactInfo.Artifact(name, sha1, url, GameArtifactInfo.Artifact.ArtifactType.NATIVES)
}

private fun GameVersionInfo.findArtifacts(json: LunarModuleConfig, natives: Natives?): List<GameArtifactInfo.Artifact> {
    // get artifacts from fs
    val list = if (natives != null) mutableListOf(natives.toArtifact()) else mutableListOf()
    this.version.resolveFolder().resolve("artifacts").resolve(module).listFiles()?.forEach { file ->
        if (file.isFile && file.name.endsWith(".jar")) {
            // default to classpath
            val sha1 = sha1(file)
            list.add(
                GameArtifactInfo.Artifact(
                    file.name,
                    sha1,
                    "${websiteConfig.url}/download/$sha1",
                    GameArtifactInfo.Artifact.ArtifactType.CLASS_PATH
                )
            )
        } else if (file.isDirectory && file.name == "EXTERNAL_FILES") {
            file.listFiles()?.forEach { externalFile ->
                val sha1 = sha1(externalFile)
                list.add(
                    GameArtifactInfo.Artifact(
                        externalFile.name,
                        sha1,
                        "${websiteConfig.url}/download/$sha1",
                        GameArtifactInfo.Artifact.ArtifactType.EXTERNAL_FILE
                    )
                )
            }
        } else if (file.isDirectory && file.name == "JAVAAGENTS") {
            file.listFiles()?.forEach { javaagent ->
                val sha1 = sha1(javaagent)
                list.add(
                    GameArtifactInfo.Artifact(
                        javaagent.name,
                        sha1,
                        "${websiteConfig.url}/download/$sha1",
                        GameArtifactInfo.Artifact.ArtifactType.EXTERNAL_FILE
                    )
                )
            }
        }
    }
    list.addAll(json.artifacts)
    return list
}

fun sha1(file: File): String = SecureUtil.sha1(file).apply {
    if (!fileHashMap.containsKey(this)) fileHashMap[this] = file
}

private fun File.resolveModule(module: String) = this.resolve("$module.json")

private fun GameVersionInfo.findNatives(): Natives? {
    val nativesFile = configDir.resolve("natives.json")
    if (!nativesFile.exists()) return null
    val json = JSON.decodeFromString<JsonObject>(nativesFile.readText())
    val nativesJson = json[this.os]!!.jsonObject[this.arch]!!
    return JSON.decodeFromJsonElement(nativesJson)
}

private fun findVersions(): List<LunarVersion> {
    val list = mutableListOf<LunarVersion>()
    artifactsDir.listFiles()?.forEach { file ->
        if (file.isDirectory) {
            val versionID = file.name
            list.add(LunarVersion(versionID,
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

private fun String.resolveFolder(): File {
    val majorId = this.substring(0, this.lastIndexOf('.'))
    return artifactsDir.resolve(majorId).resolve(this)
}

private fun findModules(id: String): List<LunarModule> {
    val list = mutableListOf<LunarModule>()
    id.resolveFolder().listFiles()?.forEach { file ->
        if (file.isFile && file.name.endsWith(".json")) {
            val moduleID = file.name.substring(0, file.name.lastIndexOf('.'))
            list.add(LunarModule(moduleID, LunarModule.isDefault(file)))
        }
    }
    return list
}

private fun findBlogposts(): List<LauncherBlogpost> = configDir.resolve("blogposts.json").let { file ->
    if (file.exists()) JSON.decodeFromString<List<LauncherBlogpost>>(file.readText()) else emptyList()
}
