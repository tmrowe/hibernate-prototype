package com.prototype.hibernate.model.entity

import javax.persistence.*

@Entity
@Table(name = "account_list_permission")
data class AccountListPermissionEntity (

    @Column(nullable = false)
    val canViewList : Boolean = false,

    @Column(nullable = false)
    val canEditList : Boolean = false,

    @Column(nullable = false)
    val canDeleteList : Boolean = false

) : BaseEntity()
