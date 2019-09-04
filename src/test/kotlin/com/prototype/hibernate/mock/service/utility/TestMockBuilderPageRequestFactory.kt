package com.prototype.hibernate.mock.service.utility

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class TestMockBuilderPageRequestFactory {

    private val mockBuilderPageRequestFactory = MockBuilderPageRequestFactory()

    private val mockPageRequestFactory = mockBuilderPageRequestFactory.pageRequestFactory

    private val page = 0
    private val size = 20
    private val sortDirection = Sort.Direction.DESC
    private val sortField = arrayOf("some field")

    @Test
    fun `Should have a stubbed response for repository#findById`() {
        val result = mockPageRequestFactory.build(page, size, sortDirection, sortField)
        assertEquals(mockBuilderPageRequestFactory.pageRequest, result)
    }

    @Test
    fun `Should be able to override the return of a stubbed function`() {
        val pageRequest = mock<PageRequest>()

        whenever(mockPageRequestFactory.build(any(), any(), any(), any()))
            .thenReturn(pageRequest)

        val result = mockPageRequestFactory.build(page, size, sortDirection, sortField)
        assertEquals(pageRequest, result)
    }

}
