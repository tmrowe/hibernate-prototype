package com.prototype.hibernate.model

import javax.persistence.*

@Entity
data class AccountListPermission (

    @Column(nullable = false)
    val canViewList : Boolean = false,

    @Column(nullable = false)
    val canEditList : Boolean = false,

    @Column(nullable = false)
    val canDeleteList : Boolean = false

) : BaseEntity()
