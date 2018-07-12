package `fun`.vladov.actorapi.exception

import org.springframework.http.HttpStatus

/**
 * base error response
 */
data class ApiError(var status: HttpStatus, var message: String, var error: String)

