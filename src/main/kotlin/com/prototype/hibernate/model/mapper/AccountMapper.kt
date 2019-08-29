package com.prototype.hibernate.model.mapper

import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.AccountEntity

fun AccountDTO.toEntity() = AccountEntity(
    email = email,
    active = active
)
