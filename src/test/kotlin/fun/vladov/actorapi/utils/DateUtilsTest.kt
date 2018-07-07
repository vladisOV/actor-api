package `fun`.vladov.actorapi.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author vladov
 * 06/07/2018
 */
class DateUtilsTest {

    @Test
    fun resolveLastDayOfMonthTest() {
        val result = DateUtils.resolveLastDayOfMonth(1)
        assertEquals("«31» января 2018", result)
    }

    @Test
    fun resolveFirstDayOfMonthTest() {
        val result = DateUtils.resolveFirstDayOfMonth(1)
        assertEquals("«01» января 2018", result)
    }
}
