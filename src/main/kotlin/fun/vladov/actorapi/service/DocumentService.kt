package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.dto.DocumentContext
import java.io.File
import java.io.InputStream

/**
 * @author vladov
 * 06/07/2018
 */
interface DocumentService {

    /**
     * replaces data in doc template to employee info
     * @param template input stream template (baseTemplate)
     * @param context data to change
     * @param fileName file name
     * @return file name
     */
    fun stampAndLoadDoc(template: InputStream, context: DocumentContext, fileName: String): String

    /**
     * replaces data in xls template to employee info
     * @param template file template (baseTemplate)
     * @param context data to change
     * @param fileName file name
     * @return file name
     */
    fun stampAndLoadXls(template: File, context: DocumentContext, fileName: String): String
}