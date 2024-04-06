package top.lunarclient.plugins

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.sessions.*
import top.lunarclient.UserInfo
import top.lunarclient.findUser

fun Application.configureSecurity() {
    install(Sessions) {
        cookie<UserInfo>("user_session")
    }
    install(Authentication) {
        form("auth-form") {
            userParamName = "username"
            passwordParamName = "password"
            validate { credentials ->
                val user = findUser(credentials.name)
                if (user != null && user.assertPasswd(credentials.password)) {
                    user
                } else {
                    null
                }
            }
            challenge {
                call.respondRedirect("/dashboard/login")
            }
        }

        session<UserInfo>("auth-session") {
            validate { session ->
                session
            }
            challenge {
                call.respondRedirect("/dashboard/login")
            }
        }
    }
}
