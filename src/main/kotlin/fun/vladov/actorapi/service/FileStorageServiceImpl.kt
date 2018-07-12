package `fun`.vladov.actorapi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileNotFoundException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths







/**
 * @author vladov
 * 07/07/2018
 */
@Service
class FileStorageServiceImpl
@Autowired
constructor(@Value("\${file.upload-dir}") var uploadDir: String) : FileStorageService {

    private final val fileStorageLocation: Path = Paths.get(uploadDir)
        .toAbsolutePath().normalize()

    init {
        Files.createDirectories(fileStorageLocation)
    }

    override fun createTempFile(fileName: String): File {
        val path = this.fileStorageLocation.resolve(fileName)
        return File(path.toUri())
    }

    override fun loadFileAsResource(fileName: String): Resource {
        try {
            val filePath = this.fileStorageLocation.resolve(fileName).normalize()
            val resource = UrlResource(filePath.toUri())
            return if (resource.exists()) {
                resource
            } else {
                throw FileNotFoundException("File not found $fileName")
            }
        } catch (ex: MalformedURLException) {
            throw FileNotFoundException("File not found $fileName")
        }
    }
}