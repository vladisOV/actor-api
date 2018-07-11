package `fun`.vladov.actorapi

import `fun`.vladov.actorapi.domain.BankInfo
import `fun`.vladov.actorapi.domain.Certificate
import `fun`.vladov.actorapi.domain.Contract
import `fun`.vladov.actorapi.dto.EmpInfo
import com.fasterxml.jackson.core.JsonFactory
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author vladov
 * 05/07/2018
 */
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BaseControllerIntegrationTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun testGenerateDoc() {
        val result = testRestTemplate.postForEntity("/generate/doc", buildEmpInfo(),String::class.java)
        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals("1_Test_1.docx", getFileName(result.body, "fileName").textValue())
    }

    @Test
    fun testGenerateXls() {
        val result = testRestTemplate.postForEntity("/generate/xls", buildEmpInfo(), String::class.java)
        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals("1_Test_1.xlsx", getFileName(result.body, "fileName").textValue())
    }

    @Test
    fun testGenerateAll() {
        val result = testRestTemplate.postForEntity("/generate/all",buildEmpInfo(), String::class.java)
        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result.statusCode)
        assertEquals("1_Test_1.xlsx", getFileName(result.body, "xlsFileName").textValue())
        assertEquals("1_Test_1.docx", getFileName(result.body, "docFileName").textValue())
    }

    private fun getFileName(body: String?, toParse: String): JsonNode {
        return ObjectMapper(JsonFactory()).reader().readTree(body)[toParse]
    }

    fun buildEmpInfo(): EmpInfo {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val contractDate = LocalDate.parse("2001-01-01", formatter)
        return EmpInfo(1, 1, 100, "Test test testovich",
                Certificate("series", "number"),
                Contract(1, contractDate),
                BankInfo("inn", "name", "bik",
                        "bankAcc", "empAcc"))
    }
}