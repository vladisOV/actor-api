package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.DocumentContext
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
import java.util.HashMap








/**
 * @author vladov
 * 06/07/2018
 */
@Service
class DocumentServiceImpl
@Autowired
constructor(var fileStorageService: FileStorageService) : DocumentService {

    override fun stampAndLoadXls(template: File, context: DocumentContext, fileName: String): String {
        val newFile = fileStorageService.createTempFile(fileName)
        val opcPackagepkg = SpreadsheetMLPackage.load(template)
        val smlPart = opcPackagepkg.parts.get(PartName("/xl/sharedStrings.xml")) as JaxbSmlPart<*>
        smlPart.variableReplace(buildMappings(context))
        opcPackagepkg.save(newFile)
        return newFile.name
    }

    override fun stampAndLoadDoc(template: InputStream, context: DocumentContext, fileName: String): String {
        val newFile = fileStorageService.createTempFile(fileName)
        val out = FileOutputStream(newFile)

        val stamper: DocxStamper<DocumentContext> = DocxStamper(DocxStamperConfiguration())
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

    private fun buildMappings(context: DocumentContext): HashMap<String, String>? {
        val mappings = HashMap<String, String>()
        mappings["endDate"] = context.endDate
        mappings["resultSalary"] = context.resultSalary
        mappings["spellOutSalary"] = context.spellOutSalary
        mappings["hours"] = context.hours
        mappings["fullName"] = context.fullName
        mappings["number"] = context.contractNumber
        mappings["salary"] = context.salary.toString()
        mappings["inn"] = context.inn
        mappings["bik"] = context.bik
        mappings["billNumber"] = context.billNumber
        mappings["bankAccount"] = context.bankAccount
        mappings["bankName"] = context.bankName
        mappings["empAccount"] = context.empAccount
        return mappings
    }
}