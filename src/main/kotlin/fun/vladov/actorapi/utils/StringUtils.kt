package `fun`.vladov.actorapi.utils

import `fun`.vladov.actorapi.dto.EmpInfo

/**
 * @author vladov
 * 07/07/2018
 */
class StringUtils {

    companion object {

        @JvmStatic
        fun buildPrefix(empInfo: EmpInfo):String {
            val surname = empInfo.fullName.split(" ")[0]
            return "${empInfo.contract.number}_$surname"
        }

        @JvmStatic
        fun buildShortName(fullName: String):String {
            val splitted = fullName.split(" ")
            return "${splitted[0]} ${splitted[1].substring(0, 1)}. ${splitted[2].substring(0, 1)}."
        }
    }
}