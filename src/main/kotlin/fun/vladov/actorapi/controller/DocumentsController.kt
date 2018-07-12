package `fun`.vladov.actorapi.controller

import `fun`.vladov.actorapi.dto.AllDocsResponse
import `fun`.vladov.actorapi.dto.EmpInfo
import `fun`.vladov.actorapi.dto.SingleDocResponse
import `fun`.vladov.actorapi.service.ActorService
import `fun`.vladov.actorapi.service.FileStorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future


/**
 * @author vladov
 * 05/07/2018
 */

@RestController
class DocumentsController @Autowired constructor(private val actorService: ActorService,
                                                 private val fileStorageService: FileStorageService) {


    /**
     * doc file generation
     * @param empInfo - base employee info
     * @return document url & file name
     */
    @PostMapping("generate/doc")
    @ResponseStatus(HttpStatus.CREATED)
    fun generateAct(@RequestBody empInfo: EmpInfo): SingleDocResponse {
        val fileName = actorService.generateDoc(empInfo)
        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString()
        return SingleDocResponse(fileName, fileDownloadUri)
    }

    /**
     * xls file generation
     * @param empInfo - base employee info
     * @return xls url & file name
     */
    @PostMapping("generate/xls")
    @ResponseStatus(HttpStatus.CREATED)
    fun generateXls(@RequestBody empInfo: EmpInfo): SingleDocResponse {
        val fileName = actorService.generateXls(empInfo)
        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString()
        return SingleDocResponse(fileName, fileDownloadUri)
    }

    /**
     * generates both doc & xls files
     * @param empInfo - base employee info
     * @return xls url & doc url
     */
    @PostMapping("generate/all")
    @ResponseStatus(HttpStatus.CREATED)
    fun generateAll(@RequestBody empInfo: EmpInfo): AllDocsResponse {
        val xlsFileName = actorService.generateXls(empInfo)
        val docFileName = actorService.generateDoc(empInfo)
        var callables: List<Callable<String>> = mutableListOf()
        callables += Callable<String> { actorService.generateDoc(empInfo)}
        callables += Callable<String> { actorService.generateXls(empInfo)}
        val executorService: ExecutorService = Executors.newFixedThreadPool(callables.size)
        val futures:List<Future<String>> = executorService.invokeAll(callables)
        futures.parallelStream().forEach{print(it)}
        val xlsDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(xlsFileName)
                .toUriString()
        val docDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(docFileName)
                .toUriString()
        return AllDocsResponse(xlsFileName, xlsDownloadUri, docFileName, docDownloadUri)
    }

    /**
     * downloads file
     * @param fileName file to download
     * @return file as attachment
     */
    @GetMapping("/downloadFile/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String): ResponseEntity<Resource> {
        val resource = fileStorageService.loadFileAsResource(fileName)
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.filename + "\"")
                .body(resource)
    }
}