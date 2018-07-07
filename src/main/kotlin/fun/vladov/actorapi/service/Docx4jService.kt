package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.ActContext
import `fun`.vladov.actorapi.domain.EmpInfo
import java.io.InputStream

/**
 * @author vladov
 * 06/07/2018
 */
interface Docx4jService {

    fun stampAndLoad(template: InputStream, context: ActContext, fileName: String): String
}