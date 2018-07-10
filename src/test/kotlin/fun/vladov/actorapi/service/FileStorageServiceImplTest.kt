package `fun`.vladov.actorapi.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.junit4.SpringRunner
import java.lang.Exception


/**
 * @author vladov
 * 08/07/2018
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class FileStorageServiceImplTest {

    @TestConfiguration
    internal class FileStorageServiceImplContextConfiguration {

        @Bean
        fun fileStorageService(): FileStorageServiceImpl {
            return FileStorageServiceImpl("uploadDir")
        }
    }

    @Autowired
    lateinit var fileStorageService: FileStorageService

    @Test
    fun createTempFile() {

    }

    @Test(expected = Exception::class)
    fun loadFileAsResource() {
        fileStorageService.loadFileAsResource("fileName")
    }

}