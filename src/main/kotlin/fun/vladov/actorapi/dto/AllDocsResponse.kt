package `fun`.vladov.actorapi.dto

/**
 * response with all documents links
 */
data class AllDocsResponse(val xlsFileName: String, val xlsDownloadUri: String,
                           val docFileName:String, val docDownloadUri: String)