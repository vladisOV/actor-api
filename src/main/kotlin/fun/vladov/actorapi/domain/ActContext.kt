package `fun`.vladov.actorapi.domain

/**
 * @author vladov
 * 06/07/2018
 */
data class ActContext(val endDate: String, val startDate: String,
                      val resultSalary: String, val hours: String,
                      val salary: Int, val spellOutSalary: String,
                      val series: String, val number: String,
                      val contractNumber: String, val contractDate: String,
                      val shortName: String, val fullName: String
)