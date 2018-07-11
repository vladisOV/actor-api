package `fun`.vladov.actorapi.service

import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


/**
 * @author vladov
 * 08/07/2018
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class FileStorageServiceImplTest {

    lateinit var fileStorageService: FileStorageService

    @Before
    fun setUp() {
        fileStorageService = mock()
    }

    @Test
    fun loadFileAsResource() {
        fileStorageService.loadFileAsResource("fileName")
    }

}