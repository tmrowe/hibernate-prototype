package com.prototype.hibernate.model.mapper

import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.entity.ListEntity

fun ListDTO.toEntity(account : AccountEntity) = ListEntity(
    createdBy = account,
    name = name,
    description = description
)
