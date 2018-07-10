package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.ActContext
import `fun`.vladov.actorapi.domain.XlsContext
import java.io.File
import java.io.InputStream

/**
 * @author vladov
 * 06/07/2018
 */
interface DocumentService {

    fun stampAndLoadDoc(template: InputStream, context: ActContext, fileName: String): String
    fun stampAndLoadXls(template: File, context: XlsContext, fileName: String): String
}