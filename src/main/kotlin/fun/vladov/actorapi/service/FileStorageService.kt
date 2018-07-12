package `fun`.vladov.actorapi.service

import org.springframework.core.io.Resource
import java.io.File

/**
 * @author vladov
 * 07/07/2018
 */
interface FileStorageService {

    /**
     * creates temp file on disk
     * @param fileName file name
     * @return created file
     */
    fun createTempFile(fileName: String): File/**
     * loads file from disk
     * @param fileName file name
     * @return file resource
     */
    fun loadFileAsResource(fileName: String): Resource
}