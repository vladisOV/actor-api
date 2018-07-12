package `fun`.vladov.actorapi.exception

import org.springframework.http.HttpStatus

class ApiError {

    lateinit var status: HttpStatus
    lateinit var message: String
    lateinit var error: String

    constructor(status: HttpStatus, message: String, error: String) : super() {
        this.status = status
        this.message = message
        this.error = error
    }
}
