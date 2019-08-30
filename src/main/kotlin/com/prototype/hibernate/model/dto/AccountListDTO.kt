package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.*

data class AccountListDTO(
    val permission : AccountListPermissionDTO
) {

    fun toEntity(accountUuid: AccountListEntityId, account: AccountEntity, list: ListEntity) = AccountListEntity(
        uuid = accountUuid,
        account = account,
        list = list,
        permission = AccountListPermissionEntity(
            canViewList = permission.canViewList,
            canEditList = permission.canEditList,
            canDeleteList = permission.canDeleteList
        )
    )

}
