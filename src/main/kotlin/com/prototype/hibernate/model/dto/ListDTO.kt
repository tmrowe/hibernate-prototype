package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.Account
import com.prototype.hibernate.model.entity.List

data class ListDTO(
    val name : String,
    val description : String?
) {

    fun toEntity(account : Account) = List(
        createdBy = account,
        name = name,
        description = description
    )

}
