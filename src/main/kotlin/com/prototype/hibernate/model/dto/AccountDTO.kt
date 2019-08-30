package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.AccountEntity

data class AccountDTO(
    val email : String,
    val active : Boolean
) {

    fun toEntity() = AccountEntity(
        email = email,
        active = active
    )

}
