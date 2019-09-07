package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.*
import com.prototype.hibernate.model.entity.List
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission

data class AccountListDTO(
    val permission : AccountListPermission
) {

    fun toEntity(uuid: AccountListId, account: Account, list: List) = AccountList(
        uuid = uuid,
        account = account,
        list = list,
        permission = permission
    )

}
