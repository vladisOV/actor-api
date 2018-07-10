package `fun`.vladov.actorapi.utils

import `fun`.vladov.actorapi.domain.BankInfo
import `fun`.vladov.actorapi.domain.Certificate
import `fun`.vladov.actorapi.domain.Contract
import `fun`.vladov.actorapi.domain.EmpInfo
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import java.time.LocalDate
import java.time.format.DateTimeFormatter



/**
 * @author vladov
 * 07/07/2018
 */
class EmpInfoDeserializer : JsonDeserializer<EmpInfo>() {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): EmpInfo {
        val jNode = p.readValueAsTree<JsonNode>()
        val salary = getIntValue("salary", jNode)
        val month = getIntValue("month", jNode)
        val hours = getIntValue("hours", jNode)
        val fullName = getStringValue("fullName", jNode)
        val certificate = Certificate(getStringValue("series", jNode.get("certificate")),
                getStringValue("number", jNode.get("certificate")))
        val contract = Contract(getIntValue("number", jNode.get("contract")),
                parseDate("date", jNode.get("contract")))
        val bankInfoNode = jNode.get("bankInfo") ?: return EmpInfo(salary, month, hours, fullName, certificate, contract, null)
        val bankInfo = BankInfo(getStringValue("inn", bankInfoNode),
                getStringValue("bankName", bankInfoNode),
                getStringValue("bik", bankInfoNode),
                getStringValue("bankAccount", bankInfoNode),
                getStringValue("empAccount", bankInfoNode))
        return EmpInfo(salary, month, hours, fullName, certificate, contract, bankInfo)
    }

    private fun getIntValue(value: String, node: JsonNode): Int {
        return node.get(value).intValue()
    }

    private fun getStringValue(value: String, node: JsonNode): String {
        return node.get(value).textValue()
    }

    private fun parseDate(value: String, node: JsonNode): LocalDate {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return LocalDate.parse(node.get(value).textValue(), formatter)
    }
}