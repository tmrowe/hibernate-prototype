package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.entity.AccountListEntity
import com.prototype.hibernate.model.entity.AccountListPermissionEntity
import com.prototype.hibernate.model.entity.ListEntity

data class AccountListDTO(
    val permission : AccountListPermissionDTO
) {

    fun toEntity(account : AccountEntity, list : ListEntity) = AccountListEntity(
        account = account,
        list = list,
        permission = AccountListPermissionEntity(
            canViewList = permission.canViewList,
            canEditList = permission.canEditList,
            canDeleteList = permission.canDeleteList
        )
    )

}
