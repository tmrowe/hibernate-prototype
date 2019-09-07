package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.Account
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
interface IAccountService {

    fun create(accountDTO: AccountDTO): Account

    fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<Account>

    fun findActive(
        page: Int,
        size: Int,
        sortDirection: Sort.Direction,
        sortField: Array<String>
    ) : Page<Account>

    fun findInactive(
        page: Int,
        size: Int,
        sortDirection: Sort.Direction,
        sortField: Array<String>
    ) : Page<Account>

    fun findByUuid(uuid : UUID) : Optional<Account>

    fun findByEmail(email : String) : Optional<Account>

    fun update(uuid : UUID, accountDTO : AccountDTO): Account

}
