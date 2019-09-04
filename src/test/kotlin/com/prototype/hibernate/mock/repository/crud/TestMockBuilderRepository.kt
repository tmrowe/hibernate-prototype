package com.prototype.hibernate.mock.repository.crud

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.prototype.hibernate.model.entity.ListEntity
import com.prototype.hibernate.repository.crud.ListRepository
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.springframework.data.domain.Example
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
class TestMockBuilderRepository {

    private val mockBuilderRepository = MockBuilderRepository<ListRepository, ListEntity, UUID>(mock(), mock(), mock())

    private val mockRepository = mockBuilderRepository.repository

    private val mockUuid = mock<UUID>()
    private val mockEntity = mock<ListEntity>()
    private val mockSort = mock<Sort>()
    private val mockExample = mock<Example<ListEntity>>()
    private val mockPageRequest = mock<PageRequest>()
    private val mockUuidIterable = mock<List<UUID>>()
    private val mockEntityIterable = mock<List<ListEntity>>()

    @Test
    fun `Should build mock of the given repository`() {
        val mockRepository = mockBuilderRepository.repository
        assertThat(mockRepository, instanceOf(ListRepository::class.java))
    }

    @Test
    fun `Should have a stubbed response for repository#findById`() {
        val result = mockRepository.findById(mockUuid)
        assertEquals(mockBuilderRepository.optionalEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#save`() {
        val result = mockRepository.save(mockEntity)
        assertEquals(mockBuilderRepository.entity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findAll`() {
        val result = mockRepository.findAll()
        assertEquals(mockBuilderRepository.listEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findAll(Sort)`() {
        val result = mockRepository.findAll(mockSort)
        assertEquals(mockBuilderRepository.listEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findAll(Example)`() {
        val result = mockRepository.findAll(mockExample)
        assertEquals(mockBuilderRepository.listEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findAll(Example, Sort)`() {
        val result = mockRepository.findAll(mockExample, mockSort)
        assertEquals(mockBuilderRepository.listEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findAll(Example, Pageable)`() {
        val result = mockRepository.findAll(mockExample, mockPageRequest)
        assertEquals(mockBuilderRepository.pageEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findAll(Pageable)`() {
        val result = mockRepository.findAll(mockPageRequest)
        assertEquals(mockBuilderRepository.pageEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#getOne`() {
        val result = mockRepository.getOne(mockUuid)
        assertEquals(mockBuilderRepository.entity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findOne(Example)`() {
        val result = mockRepository.findOne(mockExample)
        assertEquals(mockBuilderRepository.optionalEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#findAllById(Iterable)`() {
        val result = mockRepository.findAllById(mockUuidIterable)
        assertEquals(mockBuilderRepository.listEntity, result)
    }

    @Test
    fun `Should have a stubbed response for repository#saveAll(Iterable)`() {
        val result = mockRepository.saveAll(mockEntityIterable)
        assertEquals(mockBuilderRepository.listEntity, result)
    }

    @Test
    fun `Should be able to override the return of a stubbed function`() {
        val mockOptionalEntity = mock<Optional<ListEntity>>()

        whenever(mockRepository.findById(ArgumentMatchers.any()))
            .thenReturn(mockOptionalEntity)

        val result = mockRepository.findById(mockUuid)
        assertEquals(mockOptionalEntity, result)
    }

}
