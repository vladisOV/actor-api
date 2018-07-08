package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.ActContext
import `fun`.vladov.actorapi.domain.EmpInfo
import `fun`.vladov.actorapi.domain.InvoiceContext
import `fun`.vladov.actorapi.utils.DateUtils.Companion.formatDate
import `fun`.vladov.actorapi.utils.DateUtils.Companion.resolveFirstDayOfMonth
import `fun`.vladov.actorapi.utils.DateUtils.Companion.resolveLastDayOfMonth
import `fun`.vladov.actorapi.utils.StringUtils
import com.ibm.icu.text.RuleBasedNumberFormat
import com.ibm.icu.util.ULocale
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ResourceLoader
import org.springframework.stereotype.Service

/**
 * @author vladov
 * 05/07/2018
 */
@Service
class ActorServiceImpl
@Autowired
constructor(@Value("\${act.destination}") var actDestination: String,
            @Value("\${invoice.destination}") var invoiceDestination: String,
            var resourceLoader: ResourceLoader,
            var documentService: DocumentService) : ActorService {

    override fun generateXls(empInfo: EmpInfo): String {
        //TODO реализация
        val template = resourceLoader.getResource(invoiceDestination).file
        return documentService.stampAndXls(template, InvoiceContext(), "new")
    }


    override fun generateDoc(empInfo: EmpInfo): String {
        val resultSalary = calculateResultSalary(empInfo)
        val spellOutSalary = formatSalaryToSpellOutString(resultSalary)
        val context = ActContext(resolveLastDayOfMonth(empInfo.month),
                resolveFirstDayOfMonth(empInfo.month),
                resultSalary.toString(),
                empInfo.hours.toString(), empInfo.salary, spellOutSalary,
                empInfo.certificate.series, empInfo.certificate.number,
                empInfo.contract.number.toString(), formatDate(empInfo.contract.date),
                StringUtils.buildShortName(empInfo.fullName), empInfo.fullName)
        val template = resourceLoader.getResource(actDestination).inputStream
        return documentService.stampAndLoad(template, context, "${StringUtils.buildPrefix(empInfo)}_${empInfo.month}.docx")
    }

    private fun formatSalaryToSpellOutString(resultSalary: Int): String {
        val formatter = RuleBasedNumberFormat(ULocale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT)
        return formatter.format(resultSalary)
    }

    private fun calculateResultSalary(empInfo: EmpInfo): Int {
        return empInfo.hours * empInfo.salary
    }
}