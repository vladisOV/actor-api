package `fun`.vladov.actorapi.service

import `fun`.vladov.actorapi.TestHelper.Companion.buildEmpInfo
import `fun`.vladov.actorapi.dto.EmpInfo
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author vladov
 * 06/07/2018
 */
@RunWith(SpringRunner::class)
@SpringBootTest
class ActorServiceImplTest {

    lateinit var actorService: ActorService
    lateinit var documentService: DocumentService
    lateinit var empInfo: EmpInfo

    @Before
    fun setUp() {
        documentService = mock()
        actorService = mock()
        empInfo = buildEmpInfo()
    }

    @Test
    fun generateDocTest() {
        whenever(documentService.stampAndLoadDoc(any(), any(), any()))
                .thenReturn("newFileName")
        actorService.generateDoc(empInfo)
        verify(actorService).generateDoc(empInfo)
    }

    @Test
    fun generateXlsTest() {
        whenever(documentService.stampAndLoadXls(any(), any(), any()))
                .thenReturn("newFileName")
        actorService.generateXls(empInfo)
        verify(actorService).generateXls(empInfo)
    }
}