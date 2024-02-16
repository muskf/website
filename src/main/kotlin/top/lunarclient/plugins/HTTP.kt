package top.lunarclient.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureHTTP() {
    install(StatusPages) {
        status(
            HttpStatusCode.BadRequest,
            HttpStatusCode.Unauthorized,
            HttpStatusCode.PaymentRequired,
            HttpStatusCode.Forbidden,
            HttpStatusCode.NotFound,
            HttpStatusCode.MethodNotAllowed,
            HttpStatusCode.NotAcceptable,
            HttpStatusCode.ProxyAuthenticationRequired,
            HttpStatusCode.RequestTimeout,
            HttpStatusCode.Conflict,
            HttpStatusCode.Gone,
            HttpStatusCode.LengthRequired,
            HttpStatusCode.PreconditionFailed,
            HttpStatusCode.PayloadTooLarge,
            HttpStatusCode.RequestURITooLong,
            HttpStatusCode.UnsupportedMediaType,
            HttpStatusCode.RequestedRangeNotSatisfiable,
            HttpStatusCode.ExpectationFailed,
            HttpStatusCode.UnprocessableEntity,
            HttpStatusCode.Locked,
            HttpStatusCode.FailedDependency,
            HttpStatusCode.TooEarly,
            HttpStatusCode.UpgradeRequired,
            HttpStatusCode.TooManyRequests,
            HttpStatusCode.RequestHeaderFieldTooLarge,
            HttpStatusCode.InternalServerError,
            HttpStatusCode.NotImplemented,
            HttpStatusCode.BadGateway,
            HttpStatusCode.ServiceUnavailable,
            HttpStatusCode.GatewayTimeout,
            HttpStatusCode.VersionNotSupported,
            HttpStatusCode.VariantAlsoNegotiates,
            HttpStatusCode.InsufficientStorage
        ) { call, status ->
            call.respondFTL(
                "error.ftl", mapOf(
                    "status" to status.value,
                    "status_desc" to status
                )
            )
        }
    }

    install(Compression) {
        gzip {
            priority = 1.0
        }
        deflate {
            priority = 10.0
            minimumSize(1024) // condition
        }
    }
}
