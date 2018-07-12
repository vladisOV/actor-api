package `fun`.vladov.actorapi.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

/**
 * @author vladov
 * 12/07/2018
 */
data class User(@Id val id: ObjectId, val username: String, var password: String)