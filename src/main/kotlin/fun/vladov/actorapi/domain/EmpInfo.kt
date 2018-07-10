package `fun`.vladov.actorapi.domain

import `fun`.vladov.actorapi.utils.EmpInfoDeserializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize

/**
 * @author vladov
 * 05/07/2018
 */
@JsonDeserialize(using = EmpInfoDeserializer::class)
data class EmpInfo(val salary: Int, val month: Int, val hours: Int, val fullName: String,
                   val certificate: Certificate, val contract: Contract, val bankInfo: BankInfo?
)