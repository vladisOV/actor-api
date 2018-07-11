package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.DocumentContext
import java.io.File
import java.io.InputStream

/**
 * @author vladov
 * 06/07/2018
 */
interface DocumentService {

    fun stampAndLoadDoc(template: InputStream, context: DocumentContext, fileName: String): String
    fun stampAndLoadXls(template: File, context: DocumentContext, fileName: String): String
}