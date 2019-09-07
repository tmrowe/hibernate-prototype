package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.entity.AccountList
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import java.util.*

interface IAccountListService {

    fun create(accountUuid : UUID, listUuid : UUID, accountListDTO : AccountListDTO) : AccountList

    fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountList>

    fun findByUuid(accountUuid : UUID, listUuid : UUID) : Optional<AccountList>

    fun update(accountUuid : UUID, listUuid : UUID, accountListDTO: AccountListDTO): AccountList

    fun deleteByUuid(accountUuid : UUID, listUuid : UUID)

}
