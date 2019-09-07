package com.prototype.hibernate.service

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*
import com.nhaarman.mockito_kotlin.*
import com.prototype.hibernate.mock.repository.crud.MockBuilderAccountListRepository
import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission
import com.prototype.hibernate.mock.repository.crud.MockBuilderAccountRepository
import com.prototype.hibernate.mock.repository.crud.MockBuilderListRepository
import com.prototype.hibernate.mock.service.utility.MockBuilderPageRequestFactory
import org.springframework.data.domain.Sort

@RunWith(SpringJUnit4ClassRunner::class)
class TestListService {

    private val listService : ListService

    private val mockBuilderListRepository = MockBuilderListRepository()
    private val mockBuilderAccountRepository = MockBuilderAccountRepository()
    private val mockBuilderAccountListRepository = MockBuilderAccountListRepository()
    private val mockBuilderPageRequestFactory = MockBuilderPageRequestFactory()

    private val mockListRepository = mockBuilderListRepository.repository
    private val mockAccountRepository = mockBuilderAccountRepository.repository
    private val mockAccountListRepository = mockBuilderAccountListRepository.repository
    private val mockAccountListService = mock<AccountListService>()
    private val mockPageRequestFactory = mockBuilderPageRequestFactory.pageRequestFactory

    private val mockListEntity = mockBuilderListRepository.entity
    private val mockPageEntity = mockBuilderListRepository.pageEntity
    private val mockOptionalEntity = mockBuilderListRepository.optionalEntity
    private val mockListUuid = mockBuilderListRepository.uuid

    private val mockAccountEntity = mockBuilderAccountRepository.entity
    private val mockAccountUuid = mockBuilderAccountRepository.uuid

    private val mockPageRequest = mockBuilderPageRequestFactory.pageRequest

    private val mockListDTO = mock<ListDTO>()

    private val page = 0
    private val size = 20
    private val sortDirection = Sort.Direction.DESC
    private val sortField = arrayOf("some field")

    init {
        listService = ListService(
            mockListRepository,
            mockAccountRepository,
            mockAccountListRepository,
            mockAccountListService,
            mockPageRequestFactory
        )
    }

    @Test
    fun `ListService#create should instantiate an instance of ListEntity and pass it to the repository`() {
        whenever(mockListDTO.toEntity(any()))
            .thenReturn(mockListEntity)

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
        val result = listService.findAll(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockListRepository).findAll(mockPageRequest)
        assertEquals(mockPageEntity, result)
    }

    @Test
    fun `ListService#findByUuid should call ListRepository#findById`() {
        val result = listService.findByUuid(mockListUuid)

        verify(mockListRepository).findById(mockListUuid)
        assertEquals(mockOptionalEntity, result)
    }

    @Test
    fun `ListService#update should call ListRepository#save`() {
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

        verify(mockAccountListRepository).deleteByListUuid(mockListUuid)
        verify(mockListRepository).deleteById(mockListUuid)
    }

}
