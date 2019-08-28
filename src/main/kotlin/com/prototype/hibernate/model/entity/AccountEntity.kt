package com.prototype.hibernate.model.entity

import javax.persistence.*

@Entity
@Table(name = "account")
data class AccountEntity (

    @Column(
        unique = true,
        nullable = false
    )
    val email : String,

    @Column(nullable = false)
    val active : Boolean = false

) : BaseEntity()
