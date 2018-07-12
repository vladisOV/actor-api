package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.dto.EmpInfo

/**
 * @author vladov
 * 05/07/2018
 */

interface ActorService {

    /**
     * base method for generating doc file
     * @param empInfo employee info
     * @return file name
     */
    fun generateDoc(empInfo: EmpInfo): String

    /**
     * base method for generating xls file
     * @param empInfo employee info
     * @return file name
     */
    fun generateXls(empInfo: EmpInfo): String
}