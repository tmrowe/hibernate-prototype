package com.prototype.hibernate.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*
import com.nhaarman.mockito_kotlin.*
import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.entity.AccountListEntity
import com.prototype.hibernate.model.entity.ListEntity
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import com.prototype.hibernate.repository.crud.AccountListRepository
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.repository.crud.ListRepository
import com.prototype.hibernate.service.utility.PageRequestFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.*

@RunWith(SpringJUnit4ClassRunner::class)
class TestAccountListService {

    private val accountListService : AccountListService

    private val mockAccountListRepository = mock<AccountListRepository>()
    private val mockAccountRepository = mock<AccountRepository>()
    private val mockListRepository = mock<ListRepository>()
    private val mockPageRequestFactory = mock<PageRequestFactory>()

    private val mockAccountListEntity = mock<AccountListEntity>()
    private val mockOptionalAccountListEntity = mock<Optional<AccountListEntity>>()
    private val mockAccountListDTO = mock<AccountListDTO>()

    private val mockAccountEntity = mock<AccountEntity>()
    private val mockOptionalAccountEntity = mock<Optional<AccountEntity>>()

    private val mockListEntity = mock<ListEntity>()
    private val mockOptionalListEntity = mock<Optional<ListEntity>>()

    private val mockAccountUuid = mock<UUID>()
    private val mockListUuid = mock<UUID>()
    private val accountListUuid = AccountListId(mockAccountUuid, mockListUuid)

    private val mockPageRequest = mock<PageRequest>()
    private val mockPage = mock<Page<AccountListEntity>>()

    private val page = 0
    private val size = 20
    private val sortDirection = Sort.Direction.DESC
    private val sortField = arrayOf("some field")

    init {
        accountListService = AccountListService(
            mockAccountListRepository,
            mockAccountRepository,
            mockListRepository,
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

        whenever(mockAccountListRepository.findById(any()))
            .thenReturn(mockOptionalAccountListEntity)

        whenever(mockOptionalAccountListEntity.get())
            .thenReturn(mockAccountListEntity)

        whenever(mockAccountListDTO.toEntity(any(), any(), any()))
            .thenReturn(mockAccountListEntity)
    }

    @Test
    fun `AccountListService#create should instantiate an instance of AccountListEntity and pass it to the repository`() {
        whenever(mockAccountListRepository.save(any<AccountListEntity>()))
            .thenReturn(mockAccountListEntity)

        val result = accountListService.create(mockAccountUuid, mockListUuid, mockAccountListDTO)

        verify(mockAccountListDTO).toEntity(accountListUuid, mockAccountEntity, mockListEntity)
        verify(mockAccountListRepository).save(mockAccountListEntity)
        assertEquals(mockAccountListEntity, result)
    }

    @Test
    fun `AccountListService#findAll should call AccountListRepository#findAll`() {
        whenever(mockAccountListRepository.findAll(any<PageRequest>()))
            .thenReturn(mockPage)

        whenever(mockPageRequestFactory.build(any(), any(), any(), any()))
            .thenReturn(mockPageRequest)

        val result = accountListService.findAll(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountListRepository).findAll(mockPageRequest)
        assertEquals(mockPage, result)
    }

    @Test
    fun `AccountListService#findByUuid should call AccountListRepository#findById`() {
        whenever(mockAccountListRepository.findById(any()))
            .thenReturn(mockOptionalAccountListEntity)

        val result = accountListService.findByUuid(mockAccountUuid, mockListUuid)

        verify(mockAccountListRepository).findById(accountListUuid)
        assertEquals(mockOptionalAccountListEntity, result)
    }

    @Test
    fun `AccountListService#update should call AccountListRepository#save`() {
        whenever(mockAccountListRepository.save(any<AccountListEntity>()))
            .thenReturn(mockAccountListEntity)

        whenever(mockAccountListEntity.copy())
            .thenReturn(mockAccountListEntity)

        val result = accountListService.update(mockAccountUuid, mockListUuid, mockAccountListDTO)

        verify(mockAccountListEntity).copy(
            permission = mockAccountListDTO.permission
        )
        verify(mockAccountListRepository).save(mockAccountListEntity)
        assertEquals(mockAccountListEntity, result)
    }

    @Test
    fun `AccountListService#deleteByUuid should call AccountListRepository#deleteById`() {
        accountListService.deleteByUuid(mockAccountUuid, mockListUuid)

        verify(mockAccountListRepository).deleteById(accountListUuid)
    }

}
