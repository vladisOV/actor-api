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
import sun.reflect.generics.tree.ReturnType
import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.servlet.http.HttpServletRequest


/**
 * @author vladov
 * 05/07/2018
 */

@RestController
class BaseController @Autowired constructor(private val actorService: ActorService,
                                            private val fileStorageService: FileStorageService) {

    @RequestMapping("/info", method = [(RequestMethod.POST)])
    fun getInfo(): SingleDocResponse {
        return SingleDocResponse("1", "1")
    }

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

    @PostMapping("generate/all")
    @ResponseStatus(HttpStatus.CREATED)
    fun generateAll(@RequestBody empInfo: EmpInfo): AllDocsResponse {
        var callables: List<Callable<String>> = mutableListOf()
        callables += Callable<String> { actorService.generateDoc(empInfo)}
        callables += Callable<String> { actorService.generateXls(empInfo)}
        val executorService: ExecutorService = Executors.newFixedThreadPool(callables.size)
        val futures:List<Future<String>> = executorService.invokeAll(callables)
        executorService.shutdown()

        //TODO null check + exception
        val docFileName = futures[0].get()
        val xlsFileName = futures[1].get()

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

    @GetMapping("/downloadFile/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String, request: HttpServletRequest): ResponseEntity<Resource> {
        val resource = fileStorageService.loadFileAsResource(fileName)
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource)
    }
}