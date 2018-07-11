package `fun`.vladov.actorapi.dto

data class AllDocsResponse(val xlsFileName: String, val xlsDownloadUri: String,
                           val docFileName:String, val docDownloadUri: String)