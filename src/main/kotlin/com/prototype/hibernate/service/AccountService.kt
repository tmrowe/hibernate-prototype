package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.mapper.toEntity
import com.prototype.hibernate.repository.crud.AccountRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AccountService(
    private val accountRepository : AccountRepository
) : IAccountService {

    override fun create(accountDTO : AccountDTO) : AccountEntity {
        val account = accountDTO.toEntity()
        return accountRepository.save(account)
    }

    override fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountEntity> {
        val pageRequest = PageRequest.of(page, size, sortDirection, *sortField)
        return accountRepository.findAll(pageRequest)
    }

    override fun findActive(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountEntity> {
        val pageRequest = PageRequest.of(page, size, sortDirection, *sortField)
        return accountRepository.findByActiveTrue(pageRequest)
    }

    override fun findInactive(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountEntity> {
        val pageRequest = PageRequest.of(page, size, sortDirection, *sortField)
        return accountRepository.findByActiveFalse(pageRequest)
    }

    override fun findByUuid(uuid : UUID) : Optional<AccountEntity> {
        return accountRepository.findById(uuid)
    }

    override fun findByEmail(email : String) : Optional<AccountEntity> {
        return accountRepository.findByEmail(email)
    }

    @Transactional
    override fun update(uuid : UUID, accountDTO : AccountDTO): AccountEntity {
        val account = accountRepository.findById(uuid).get()
        val updatedAccount = account.copy(
            active = accountDTO.active,
            email = accountDTO.email
        )
        return accountRepository.save(updatedAccount)
    }

    override fun deleteByUuid(uuid : UUID) {
        return accountRepository.deleteById(uuid)
    }

}
