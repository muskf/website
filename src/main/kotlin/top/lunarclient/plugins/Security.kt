package top.lunarclient.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import top.lunarclient.UserInfo
import top.lunarclient.findUser

data class UserSession(
    val name: String,
    val password: String
) : Principal

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<UserSession>("user_session")
    }
    install(Authentication) {
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                val user = findUser(credentials.name)
                if (user != null && user.assertPasswd(credentials.password)) {
                    user.toSession()
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect("/dashboard/login")
            }
        }

        session<UserSession>("auth-session") {
            validate { session ->
                session
            }
            challenge {
                call.respondRedirect("/dashboard/login")
            }
        }
    }
}

private fun UserInfo.toSession(): Principal = UserSession(this.username, this.password)
