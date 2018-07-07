package `fun`.vladov.actorapi.service

import org.springframework.core.io.Resource
import java.io.File

/**
 * @author vladov
 * 07/07/2018
 */
interface FileStorageService {

    fun createTempFile(fileName: String): File
    fun loadFileAsResource(fileName: String): Resource
}