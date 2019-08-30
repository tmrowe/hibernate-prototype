package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.entity.ListEntity

data class ListDTO(
    val name : String,
    val description : String?
) {

    fun toEntity(account : AccountEntity) = ListEntity(
        createdBy = account,
        name = name,
        description = description
    )

}
