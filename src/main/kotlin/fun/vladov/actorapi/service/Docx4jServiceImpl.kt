package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.ActContext
import `fun`.vladov.actorapi.domain.EmpInfo
import org.docx4j.openpackaging.packages.WordprocessingMLPackage
import org.docx4j.openpackaging.packages.WordprocessingMLPackage.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.wickedsource.docxstamper.DocxStamper
import org.wickedsource.docxstamper.DocxStamperConfiguration
import java.io.*


/**
 * @author vladov
 * 06/07/2018
 */
@Service
class Docx4jServiceImpl
@Autowired
constructor(var fileStorageService: FileStorageService) : Docx4jService {

    override fun stampAndLoad(template: InputStream, context: ActContext, fileName: String): String {
        val newFile = fileStorageService.createTempFile(fileName)
        val out = FileOutputStream(newFile)

        val stamper: DocxStamper<ActContext> = DocxStamper(DocxStamperConfiguration())
        stamper.stamp(template, context, out)

        val document = load(newFile)
        document.save(newFile)

        logger.info(newFile.canonicalPath)
        return newFile.name
    }

    companion object {
        val logger: Logger =
                LoggerFactory.getLogger(Docx4jServiceImpl::class.java)
    }
}