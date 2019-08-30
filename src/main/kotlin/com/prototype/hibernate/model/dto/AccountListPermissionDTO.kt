package com.prototype.hibernate.model.dto

data class AccountListPermissionDTO(
    val canViewList : Boolean,
    val canEditList : Boolean,
    val canDeleteList : Boolean
)
