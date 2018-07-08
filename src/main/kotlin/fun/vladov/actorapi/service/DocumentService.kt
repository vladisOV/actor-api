package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.ActContext
import `fun`.vladov.actorapi.domain.InvoiceContext
import java.io.File
import java.io.InputStream

/**
 * @author vladov
 * 06/07/2018
 */
interface DocumentService {

    fun stampAndLoad(template: InputStream, context: ActContext, fileName: String): String
    fun stampAndXls(template: File, context: InvoiceContext, fileName: String): String
}