package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.entity.AccountListEntity
import com.prototype.hibernate.model.entity.AccountListPermissionEntity
import com.prototype.hibernate.repository.crud.AccountListRepository
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.repository.crud.ListRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AccountListService(
    private val accountListRepository : AccountListRepository,
    private val listRepository : ListRepository,
    private val accountRepository : AccountRepository
) : IAccountListService {

    @Transactional
    override fun create(accountUuid : UUID, listUuid : UUID, accountListDTO : AccountListDTO) : AccountListEntity {
        val account = accountRepository.findById(accountUuid).get()
        val list = listRepository.findById(listUuid).get()
        val accountList = accountListDTO.toEntity(account, list)
        return accountListRepository.save(accountList)
    }

    override fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField : Array<String>
    ): Page<AccountListEntity> {
        val pageRequest = PageRequest.of(page, size, sortDirection, *sortField)
        return accountListRepository.findAll(pageRequest)
    }

    override fun findByUuid(uuid : UUID) : Optional<AccountListEntity> {
        return accountListRepository.findById(uuid)
    }

    // TODO: This is currently creating a new relationship each time.
    override fun update(uuid : UUID, accountListDTO: AccountListDTO): AccountListEntity {
        val accountList = accountListRepository.findById(uuid).get()
        val updatedAccountList = accountList.copy(
            permission = AccountListPermissionEntity(
                canViewList = accountListDTO.permission.canViewList,
                canEditList = accountListDTO.permission.canEditList,
                canDeleteList = accountListDTO.permission.canDeleteList
            )
        )
        return accountListRepository.save(updatedAccountList)
    }

    override fun deleteByUuid(uuid : UUID) {
        return accountListRepository.deleteById(uuid)
    }

}
