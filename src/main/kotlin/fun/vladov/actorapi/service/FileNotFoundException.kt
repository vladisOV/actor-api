package `fun`.vladov.actorapi.service

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

/**
 * @author vladov
 * 08/07/2018
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class FileNotFoundException: RuntimeException()