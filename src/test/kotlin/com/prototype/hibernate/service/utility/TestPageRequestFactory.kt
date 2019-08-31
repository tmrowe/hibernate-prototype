package com.prototype.hibernate.service.utility

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*

@RunWith(SpringJUnit4ClassRunner::class)
class TestPageRequestFactory {

    private val pageRequestFactory = PageRequestFactory()

    private val page = 0
    private val size = 20
    private val sortDirection = Sort.Direction.DESC
    private val sortField = arrayOf("some field", "some other field")

    @Test
    fun `PageRequestFactory#build should create a PageRequest with the given values`() {
        val result = pageRequestFactory.build(page, size, sortDirection, sortField)
        assertEquals(page, result.pageNumber)
        assertEquals(size, result.pageSize)
        assertSortDirection(result.sort)
        assertSortField(result.sort)
    }

    private fun assertSortDirection(sort : Sort) {
        assertTrue(sort.toString().contains(sortDirection.toString()))
    }

    private fun assertSortField(sort : Sort) {
        for (sortField in sortField) {
            assertTrue(sort.toString().contains(sortField))
        }
    }

}
