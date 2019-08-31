package com.prototype.hibernate.service

import com.nhaarman.mockito_kotlin.*
import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.service.utility.PageRequestFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.*

// TODO: Test for the presence of a Transactional annotation.
@RunWith(SpringJUnit4ClassRunner::class)
class TestAccountService {

    private val accountService : AccountService

    private val mockAccountRepository = mock<AccountRepository>()
    private val mockPageRequestFactory = mock<PageRequestFactory>()

    private val mockAccountDTO = mock<AccountDTO>()
    private val mockAccountEntity = mock<AccountEntity>()
    private val mockOptionalAccountEntity = mock<Optional<AccountEntity>>()
    private val mockPageRequest = mock<PageRequest>()
    private val mockPage = mock<Page<AccountEntity>>()
    private val mockUuid = mock<UUID>()

    private val page = 0
    private val size = 20
    private val sortDirection = Sort.Direction.DESC
    private val sortField = arrayOf("some field")
    private val email = "some email"

    init {
        accountService = AccountService(mockAccountRepository, mockPageRequestFactory)

        whenever(mockAccountDTO.toEntity())
            .thenReturn(mockAccountEntity)

        whenever(mockPageRequestFactory.build(any(), any(), any(), any()))
            .thenReturn(mockPageRequest)

        whenever(mockOptionalAccountEntity.get())
            .thenReturn(mockAccountEntity)

        whenever(mockAccountEntity.copy())
            .thenReturn(mockAccountEntity)
    }

    @Test
    fun `AccountService#create should instantiate an instance of AccountEntity and pass it to the repository`() {
        whenever(mockAccountRepository.save(mockAccountEntity))
            .thenReturn(mockAccountEntity)

        val result = accountService.create(mockAccountDTO)

        verify(mockAccountDTO).toEntity()
        verify(mockAccountRepository).save(mockAccountEntity)
        assertEquals(mockAccountEntity, result)
    }

    @Test
    fun `AccountService#findAll should call AccountRepository#findAll`() {
        whenever(mockAccountRepository.findAll(any<PageRequest>()))
            .thenReturn(mockPage)

        val result = accountService.findAll(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountRepository).findAll(mockPageRequest)
        assertEquals(mockPage, result)
    }

    @Test
    fun `AccountService#findActive should call AccountRepository#findActive`() {
        whenever(mockAccountRepository.findByActiveTrue(any()))
            .thenReturn(mockPage)

        val result = accountService.findActive(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountRepository).findByActiveTrue(mockPageRequest)
        assertEquals(mockPage, result)
    }

    @Test
    fun `AccountService#findInactive should call AccountRepository#findInactive`() {
        whenever(mockAccountRepository.findByActiveFalse(any()))
            .thenReturn(mockPage)

        val result = accountService.findInactive(page, size, sortDirection, sortField)

        verify(mockPageRequestFactory).build(page, size, sortDirection, sortField)
        verify(mockAccountRepository).findByActiveFalse(mockPageRequest)
        assertEquals(mockPage, result)
    }

    @Test
    fun `AccountService#findByUuid should call AccountRepository#findById`() {
        whenever(mockAccountRepository.findById(any()))
            .thenReturn(mockOptionalAccountEntity)

        val result = accountService.findByUuid(mockUuid)

        verify(mockAccountRepository).findById(mockUuid)
        assertEquals(mockOptionalAccountEntity, result)
    }

    @Test
    fun `AccountService#findByEmail should call AccountRepository#findByEmail`() {
        whenever(mockAccountRepository.findByEmail(any()))
            .thenReturn(mockOptionalAccountEntity)

        val result = accountService.findByEmail(email)

        verify(mockAccountRepository).findByEmail(email)
        assertEquals(mockOptionalAccountEntity, result)
    }

    @Test
    fun `AccountService#update should call AccountRepository#save to update entity with given UUID`() {
        whenever(mockAccountRepository.findById(any()))
            .thenReturn(mockOptionalAccountEntity)

        whenever(mockAccountRepository.save(any<AccountEntity>()))
            .thenReturn(mockAccountEntity)

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
