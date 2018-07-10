package `fun`.vladov.actorapi.domain

/**
 * @author vladov
 * 07/07/2018
 */
data class XlsContext(val startDate: String, val endDate: String,
                      val resultSalary: String, val hours: String,
                      val salary: Int, val spellOutSalary: String,
                      val number: String, val fullName: String)