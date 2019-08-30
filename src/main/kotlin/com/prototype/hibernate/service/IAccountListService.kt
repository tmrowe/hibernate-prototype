package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.entity.AccountListEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import java.util.*

interface IAccountListService {

    fun create(accountUuid : UUID, listUuid : UUID, accountListDTO : AccountListDTO) : AccountListEntity

    fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<AccountListEntity>

    fun findByUuid(accountUuid : UUID, listUuid : UUID) : Optional<AccountListEntity>

    fun update(accountUuid : UUID, listUuid : UUID, accountListDTO: AccountListDTO): AccountListEntity

    fun deleteByUuid(accountUuid : UUID, listUuid : UUID)

}
