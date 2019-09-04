package com.prototype.hibernate.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*
import com.nhaarman.mockito_kotlin.*
import com.prototype.hibernate.mock.repository.crud.MockBuilderAccountListRepository
import com.prototype.hibernate.mock.repository.crud.MockBuilderAccountRepository
import com.prototype.hibernate.mock.repository.crud.MockBuilderListRepository
import com.prototype.hibernate.mock.service.utility.MockBuilderPageRequestFactory
import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import org.springframework.data.domain.Sort

@RunWith(SpringJUnit4ClassRunner::class)
class TestAccountListService {

    private val accountListService : AccountListService

    private val mockBuilderAccountListRepository = MockBuilderAccountListRepository()
    private val mockBuilderAccountRepository = MockBuilderAccountRepository()
    private val mockBuilderListRepository = MockBuilderListRepository()
    private val mockBuilderPageRequestFactory = MockBuilderPageRequestFactory()

    private val mockAccountListRepository = mockBuilderAccountListRepository.repository
    private val mockAccountRepository = mockBuilderAccountRepository.repository
    private val mockListRepository = mockBuilderListRepository.repository
    private val mockPageRequestFactory = mockBuilderPageRequestFactory.pageRequestFactory

    private val mockAccountListEntity = mockBuilderAccountListRepository.entity
    private val mockOptionalAccountListEntity = mockBuilderAccountListRepository.optionalEntity
    private val mockAccountListPage = mockBuilderAccountListRepository.pageEntity

    private val mockAccountEntity = mockBuilderAccountRepository.entity
    private val mockAccountUuid = mockBuilderAccountRepository.uuid

    private val mockListEntity = mockBuilderListRepository.entity
    private val mockListUuid = mockBuilderListRepository.uuid

    private val accountListUuid = AccountListId(mockAccountUuid, mockListUuid)
    private val mockAccountListDTO = mock<AccountListDTO>()

    private val mockPageRequest = mockBuilderPageRequestFactory.pageRequest

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
    }

    @Test
    fun `AccountListService#create should instantiate an instance of AccountListEntity and pass it to the repository`() {
        whenever(mockAccountListDTO.toEntity(any(), any(), any()))
            .thenReturn(mockAccountListEntity)

        val result = accountListService.create(mockAccountUuid, mockListUuid, mockAccountListDTO)

        verify(mockAccountListDTO).toEntity(accountListUuid, mockAccountEntity, mockListEntity)
        verify(mockAccountListRepository).save(mockAccountListEntity)
        assertEquals(mockAccountListEntity, result)
    }

    @Test
    fun `AccountListService#findAll should call AccountListRepository#findAll`() {
        val result = accountListService.findAll(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountListRepository).findAll(mockPageRequest)
        assertEquals(mockAccountListPage, result)
    }

    @Test
    fun `AccountListService#findByUuid should call AccountListRepository#findById`() {
        val result = accountListService.findByUuid(mockAccountUuid, mockListUuid)

        verify(mockAccountListRepository).findById(accountListUuid)
        assertEquals(mockOptionalAccountListEntity, result)
    }

    @Test
    fun `AccountListService#update should call AccountListRepository#save`() {
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
