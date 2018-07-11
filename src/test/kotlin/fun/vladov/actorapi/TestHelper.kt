package `fun`.vladov.actorapi

import `fun`.vladov.actorapi.domain.BankInfo
import `fun`.vladov.actorapi.domain.Certificate
import `fun`.vladov.actorapi.domain.Contract
import `fun`.vladov.actorapi.dto.EmpInfo
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * @author vladov
 * 11/07/2018
 */
class TestHelper {

    companion object {
        @JvmStatic
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
}