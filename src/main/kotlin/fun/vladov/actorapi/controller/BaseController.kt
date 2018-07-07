package `fun`.vladov.actorapi.controller

import `fun`.vladov.actorapi.domain.EmpInfo
import `fun`.vladov.actorapi.domain.UploadFileResponse
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
import javax.servlet.http.HttpServletRequest


/**
 * @author vladov
 * 05/07/2018
 */

@RestController
class BaseController {

    @Autowired
    lateinit var actorService: ActorService
    @Autowired
    lateinit var fileStorageService: FileStorageService

    @RequestMapping("/info", method = [(RequestMethod.POST)])
    fun getInfo(): UploadFileResponse {
        return UploadFileResponse("1", "1")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun generateAct(@RequestBody empInfo: EmpInfo): UploadFileResponse {
        val fileName = actorService.generateDoc(empInfo)
        val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString()
        return UploadFileResponse(fileName, fileDownloadUri)
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