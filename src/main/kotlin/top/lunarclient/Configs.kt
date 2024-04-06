package top.lunarclient

import cn.hutool.crypto.SecureUtil.md5
import io.ktor.server.auth.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import top.lunarclient.plugins.Alert
import java.io.File
import kotlin.io.path.Path

val JSON = Json { ignoreUnknownKeys = true; prettyPrint = true; encodeDefaults = true }

// 阻止除lunarclient.top和127.0.0.1的访问, 对API和文档不生效
const val BLOCK_UNOFFICIAL_HOST = true

val KNOWN_HOSTS = mutableListOf("lunarclient.top", "lunarcn.lol", "127.0.0.1")

val configDir = File(
    System.getProperty("user.home"),
    ".cubewhy/website"
).apply {
    mkdirs()
}

val artifactsDir = configDir.resolve("artifacts").apply {
    mkdirs()
}

val mirrorDir = configDir.resolve("mirror").apply {
    mkdirs()
}

val pluginsDir = configDir.resolve("plugins").apply {
    mkdirs()
}

val configFile: File = configDir.resolve("config.json")
val shortLinkFile: File = configDir.resolve("links.json")

val usersFile: File = Path(
    System.getProperty("user.home"),
    ".cubewhy",
    "website",
    "users.json"
).toFile().apply {
    this.parentFile.mkdirs()
}

val websiteConfig = try {
    JSON.decodeFromString<WebsiteConfig>(
        configFile.readText()
    )
} catch (e: Exception) {
    WebsiteConfig().apply {
        configFile.writeText(JSON.encodeToString(WebsiteConfig.serializer(), this)) // dump default
    }
}

val shortLinkConfig = try {
    JSON.decodeFromString<ShortLinkConfig>(
        shortLinkFile.readText()
    )
} catch (e: Exception) {
    ShortLinkConfig().apply {
        shortLinkFile.writeText(JSON.encodeToString(ShortLinkConfig.serializer(), this))
    }
}

val users = try {
    JSON.decodeFromString<Map<String, UserInfo>>(
        usersFile.readText()
    )
} catch (e: Exception) {
    emptyMap<String, UserInfo>().apply {
        usersFile.writeText(JSON.encodeToString(this)) // dump default
    }
}

fun findUser(name: String) = users[name]


@Serializable
data class ShortLinkConfig(
    val links: MutableList<ShortLink> = mutableListOf()
) {
    @Serializable
    data class ShortLink(val name: String, val target: String)

    fun add(name: String, target: String) = links.add(ShortLink(name, target))
    fun save() =
        shortLinkFile.writeText(JSON.encodeToString(serializer(), this))

    operator fun get(name: String) = links.find { it.name == name}
}


@Serializable
class WebsiteConfig {
    val title: String = "LunarCN Official"
    val description: String = "Make LunarClient Great Again"
    val url: String = "http://127.0.0.1:8080"
    val alert: Alert? = null
    val defaultVersion: String = "1.8.9"

    val defaultVMArgs: List<String> = mutableListOf(
        "--add-modules",
        "jdk.naming.dns",
        "--add-exports",
        "jdk.naming.dns/com.sun.jndi.dns\u003djava.naming",
        "-Djna.boot.library.path\u003dnatives",
        "-Dlog4j2.formatMsgNoLookups\u003dtrue",
        "--add-opens",
        "java.base/java.io\u003dALL-UNNAMED",
        "-XX:+UseStringDeduplication"
    )
}

@Serializable
data class UserInfo(
    val username: String,
    val password: String,
    val role: Role = Role.USER
) : Principal {
    enum class Role {
        USER,
        SUPPORTER,
        STAFF,
        ADMIN
    }

    fun assertPasswd(passwd: String) = md5(passwd) == password
}