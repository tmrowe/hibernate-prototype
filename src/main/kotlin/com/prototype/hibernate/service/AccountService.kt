package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.Account
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.service.utility.PageRequestFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AccountService(
    private val accountRepository : AccountRepository,
    private val pageRequestFactory : PageRequestFactory
) : IAccountService {

    override fun create(accountDTO : AccountDTO) : Account {
        val account = accountDTO.toEntity()
        return accountRepository.save(account)
    }

    override fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<Account> {
        val pageRequest = pageRequestFactory.build(page, size, sortDirection, sortField)
        return accountRepository.findAll(pageRequest)
    }

    override fun findActive(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<Account> {
        val pageRequest = pageRequestFactory.build(page, size, sortDirection, sortField)
        return accountRepository.findByActiveTrue(pageRequest, Account::class.java)
    }

    override fun findInactive(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<Account> {
        val pageRequest = pageRequestFactory.build(page, size, sortDirection, sortField)
        return accountRepository.findByActiveFalse(pageRequest, Account::class.java)
    }

    override fun findByUuid(uuid : UUID) : Optional<Account> {
        return accountRepository.findById(uuid)
    }

    override fun findByEmail(email : String) : Optional<Account> {
        return accountRepository.findByEmail(email, Account::class.java)
    }

    @Transactional
    override fun update(uuid : UUID, accountDTO : AccountDTO) : Account {
        val account = accountRepository.findById(uuid).get()
        val updatedAccount = account.copy(
            active = accountDTO.active,
            email = accountDTO.email
        )
        return accountRepository.save(updatedAccount)
    }

}
