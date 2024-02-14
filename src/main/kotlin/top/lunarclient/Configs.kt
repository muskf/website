package top.lunarclient

import cn.hutool.crypto.SecureUtil.md5
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import top.lunarclient.plugins.Alert
import java.io.File
import kotlin.io.path.Path

val JSON = Json { ignoreUnknownKeys = true; prettyPrint = true; encodeDefaults = true }

val configFile: File = Path(
    System.getProperty("user.home"),
    ".cubewhy",
    "website",
    "config.json"
).toFile().apply {
    this.parentFile.mkdirs()
}

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
class WebsiteConfig {
    val title: String = "LunarCN Official"
    val description: String = "Make LunarClient Great Again"
    val url: String = "http://127.0.0.1:8080"
    val alert: Alert? = null
}

@Serializable
data class UserInfo(
    val username: String,
    val password: String
) {
    fun assertPasswd(passwd: String) = md5(passwd) == password
}