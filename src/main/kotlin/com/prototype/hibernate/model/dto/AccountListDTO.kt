package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.*
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission

data class AccountListDTO(
    val permission : AccountListPermission
) {

    fun toEntity(accountUuid: AccountListId, account: AccountEntity, list: ListEntity) = AccountListEntity(
        uuid = accountUuid,
        account = account,
        list = list,
        permission = permission
    )

}
