package com.prototype.hibernate.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*
import com.nhaarman.mockito_kotlin.*
import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.entity.ListEntity
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.repository.crud.ListRepository
import com.prototype.hibernate.service.utility.PageRequestFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
class TestListService {

    private val listService : ListService

    private val mockListRepository = mock<ListRepository>()
    private val mockAccountRepository = mock<AccountRepository>()
    private val mockAccountListService = mock<AccountListService>()
    private val mockPageRequestFactory = mock<PageRequestFactory>()

    private val mockListEntity = mock<ListEntity>()
    private val mockOptionalListEntity = mock<Optional<ListEntity>>()
    private val mockListDTO = mock<ListDTO>()

    private val mockAccountEntity = mock<AccountEntity>()
    private val mockOptionalAccountEntity = mock<Optional<AccountEntity>>()

    private val mockAccountUuid = mock<UUID>()
    private val mockListUuid = mock<UUID>()

    private val mockPageRequest = mock<PageRequest>()
    private val mockPage = mock<Page<ListEntity>>()

    private val page = 0
    private val size = 20
    private val sortDirection = Sort.Direction.DESC
    private val sortField = arrayOf("some field")

    init {
        listService = ListService(
            mockListRepository,
            mockAccountRepository,
            mockAccountListService,
            mockPageRequestFactory
        )

        whenever(mockAccountRepository.findById(any()))
            .thenReturn(mockOptionalAccountEntity)

        whenever(mockOptionalAccountEntity.get())
            .thenReturn(mockAccountEntity)

        whenever(mockListRepository.findById(any()))
            .thenReturn(mockOptionalListEntity)

        whenever(mockOptionalListEntity.get())
            .thenReturn(mockListEntity)

        whenever(mockPageRequestFactory.build(any(), any(), any(), any()))
            .thenReturn(mockPageRequest)
    }

    @Test
    fun `ListService#create should instantiate an instance of ListEntity and pass it to the repository`() {
        whenever(mockListRepository.save(any<ListEntity>()))
            .thenReturn(mockListEntity)

        whenever(mockListDTO.toEntity(any()))
            .thenReturn(mockListEntity)

        whenever(mockListEntity.uuid)
            .thenReturn(mockListUuid)

        val result = listService.create(mockAccountUuid, mockListDTO)

        verify(mockListDTO).toEntity(mockAccountEntity)
        verify(mockListRepository).save(mockListEntity)
        verify(mockAccountListService).create(mockAccountUuid, mockListUuid, AccountListDTO(
            permission = AccountListPermission(
                canViewList = true,
                canEditList = true,
                canDeleteList = true
            )
        ))
        assertEquals(mockListEntity, result)
    }

    @Test
    fun `ListService#findAll should call ListRepository#findAll`() {
        whenever(mockListRepository.findAll(any<PageRequest>()))
            .thenReturn(mockPage)

        val result = listService.findAll(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockListRepository).findAll(mockPageRequest)
        assertEquals(mockPage, result)
    }

    @Test
    fun `ListService#findByUuid should call ListRepository#findById`() {
        whenever(mockListRepository.findById(any()))
            .thenReturn(mockOptionalListEntity)

        val result = listService.findByUuid(mockListUuid)

        verify(mockListRepository).findById(mockListUuid)
        assertEquals(mockOptionalListEntity, result)
    }

    @Test
    fun `ListService#update should call ListRepository#save`() {
        whenever(mockListRepository.save(any<ListEntity>()))
            .thenReturn(mockListEntity)

        whenever(mockListEntity.copy())
            .thenReturn(mockListEntity)

        val result = listService.update(mockListUuid, mockListDTO)

        verify(mockListEntity).copy(
            name = mockListDTO.name,
            description = mockListDTO.description
        )
        verify(mockListRepository).save(mockListEntity)
        assertEquals(mockListEntity, result)
    }

    @Test
    fun `ListService#deleteByUuid should call ListRepository#deleteById`() {
        listService.deleteByUuid(mockListUuid)

        verify(mockListRepository).deleteById(mockListUuid)
    }

}
