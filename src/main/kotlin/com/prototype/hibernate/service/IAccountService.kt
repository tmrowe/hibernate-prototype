package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.AccountEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
interface IAccountService {

    fun create(accountDTO: AccountDTO): AccountEntity

    fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountEntity>

    fun findActive(
        page: Int,
        size: Int,
        sortDirection: Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountEntity>

    fun findInactive(
        page: Int,
        size: Int,
        sortDirection: Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountEntity>

    fun findByUuid(uuid : UUID) : Optional<AccountEntity>

    fun findByEmail(email : String) : Optional<AccountEntity>

    fun update(uuid : UUID, accountDTO : AccountDTO): AccountEntity

    fun deleteByUuid(uuid : UUID)

}
