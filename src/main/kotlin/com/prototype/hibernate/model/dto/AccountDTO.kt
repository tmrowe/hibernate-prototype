package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.Account

data class AccountDTO(
    val email : String,
    val active : Boolean
) {

    fun toEntity() = Account(
        email = email,
        active = active
    )

}
