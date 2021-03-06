package `fun`.vladov.actorapi.exception

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.io.FileNotFoundException

/**
 * handles exceptions and returns response error
 */
@ControllerAdvice
class ApiExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(FileNotFoundException::class)
    fun handleFileNotFoundException(
            ex: FileNotFoundException, request: WebRequest): ResponseEntity<Any> {
        val errorMsg = "You must specify real file name"
        val apiError = ApiError(HttpStatus.BAD_REQUEST, errorMsg , ex.localizedMessage)
        return ResponseEntity(
                apiError, HttpHeaders(), apiError.status)
    }
}