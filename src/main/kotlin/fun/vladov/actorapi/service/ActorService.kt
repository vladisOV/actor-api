package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.domain.EmpInfo

/**
 * @author vladov
 * 05/07/2018
 */

interface ActorService {

    fun generateDoc(empInfo: EmpInfo): String
}