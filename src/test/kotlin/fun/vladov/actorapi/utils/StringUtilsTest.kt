package `fun`.vladov.actorapi.utils

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * @author vladov
 * 07/07/2018
 */
class StringUtilsTest {

    @Test
    fun buildPrefixTest() {
        //TODO  fillTest
    }

    @Test
    fun buildShortNameTest() {
        val fullName = "Овчаренко Владислав Александрович (ИП Овчаренко В.А.)"
        assertEquals(StringUtils.buildShortName(fullName), "Овчаренко В. А.")
    }
}