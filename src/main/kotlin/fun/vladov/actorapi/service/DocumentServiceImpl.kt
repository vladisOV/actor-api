package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.ActContext
import `fun`.vladov.actorapi.domain.InvoiceContext
import org.docx4j.openpackaging.packages.WordprocessingMLPackage.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.wickedsource.docxstamper.DocxStamper
import org.wickedsource.docxstamper.DocxStamperConfiguration
import java.io.*
import org.docx4j.openpackaging.packages.SpreadsheetMLPackage
import org.docx4j.openpackaging.parts.PartName
import org.docx4j.openpackaging.parts.SpreadsheetML.JaxbSmlPart






/**
 * @author vladov
 * 06/07/2018
 */
@Service
class DocumentServiceImpl
@Autowired
constructor(var fileStorageService: FileStorageService) : DocumentService {

    override fun stampAndXls(template: File, context: InvoiceContext, fileName: String): String {
        val newFile = fileStorageService.createTempFile(fileName)
        val out = FileOutputStream(newFile)
        val opcPackagepkg = SpreadsheetMLPackage.load(template)
        val smlPart = opcPackagepkg.parts.get(PartName("/xl/sharedStrings.xml")) as JaxbSmlPart<*>
//
//        val document = load(newFile)
//        document.save(newFile)

        logger.info(newFile.canonicalPath)
        return newFile.name
    }

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
                LoggerFactory.getLogger(DocumentServiceImpl::class.java)
    }
}