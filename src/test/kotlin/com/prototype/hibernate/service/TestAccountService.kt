package com.prototype.hibernate.service

import com.nhaarman.mockito_kotlin.*
import com.prototype.hibernate.mock.repository.crud.MockBuilderAccountRepository
import com.prototype.hibernate.mock.service.utility.MockBuilderPageRequestFactory
import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.view.EmailView
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort

@RunWith(SpringJUnit4ClassRunner::class)
class TestAccountService {

    private val accountService : AccountService

    private val mockBuilderAccountRepository = MockBuilderAccountRepository()
    private val mockBuilderPageRequestFactory = MockBuilderPageRequestFactory()

    private val mockAccountRepository = mockBuilderAccountRepository.repository
    private val mockPageRequestFactory = mockBuilderPageRequestFactory.pageRequestFactory

    private val mockAccountEntity = mockBuilderAccountRepository.entity
    private val mockOptionalAccountEntity = mockBuilderAccountRepository.optionalEntity
    private val mockPage = mockBuilderAccountRepository.pageEntity
    private val mockUuid = mockBuilderAccountRepository.uuid

    private val mockPageRequest = mockBuilderPageRequestFactory.pageRequest

    private val mockAccountDTO = mock<AccountDTO>()

    private val page = 0
    private val size = 20
    private val sortDirection = Sort.Direction.DESC
    private val sortField = arrayOf("some field")
    private val email = "some email"

    init {
        accountService = AccountService(mockAccountRepository, mockPageRequestFactory)
    }

    @Test
    fun `AccountService#create should instantiate an instance of AccountEntity and pass it to the repository`() {
        whenever(mockAccountDTO.toEntity())
            .thenReturn(mockAccountEntity)

        val result = accountService.create(mockAccountDTO)

        verify(mockAccountDTO).toEntity()
        verify(mockAccountRepository).save(mockAccountEntity)
        assertEquals(mockAccountEntity, result)
    }

    @Test
    fun `AccountService#findAll should call AccountRepository#findAll`() {
        val result = accountService.findAll(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountRepository).findAll(mockPageRequest)
        assertEquals(mockPage, result)
    }

    @Test
    fun `AccountService#findActive should call AccountRepository#findActive`() {
        val result = accountService.findActive(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountRepository).findByActiveTrue(mockPageRequest, AccountEntity::class.java)
        assertEquals(mockPage, result)
    }

    @Test
    fun `AccountService#findInactive should call AccountRepository#findInactive`() {
        val result = accountService.findInactive(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountRepository).findByActiveFalse(mockPageRequest, AccountEntity::class.java)
        assertEquals(mockPage, result)
    }

    @Test
    fun `AccountService#findByUuid should call AccountRepository#findById`() {
        val result = accountService.findByUuid(mockUuid)

        verify(mockAccountRepository).findById(mockUuid)
        assertEquals(mockOptionalAccountEntity, result)
    }

    @Test
    fun `AccountService#findByEmail should call AccountRepository#findByEmail`() {
        val result = accountService.findByEmail(email)

        verify(mockAccountRepository).findByEmail(email, AccountEntity::class.java)
        assertEquals(mockOptionalAccountEntity, result)
    }

    @Test
    fun `AccountService#update should call AccountRepository#save to update entity with given UUID`() {
        val result = accountService.update(mockUuid, mockAccountDTO)

        verify(mockAccountEntity).copy(
            active = mockAccountDTO.active,
            email = mockAccountDTO.email
        )
        verify(mockAccountRepository).save(mockAccountEntity)
        assertEquals(mockAccountEntity, result)
    }

    @Test
    fun `AccountService#deleteByUuid should call AccountRepository#deleteById`() {
        accountService.deleteByUuid(mockUuid)

        verify(mockAccountRepository).deleteById(mockUuid)
    }

}
