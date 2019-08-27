package com.prototype.hibernate.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
data class Account (

    @NaturalId
    @Column(
        unique = true,
        nullable = false
    )
    val email : String,

    @Column(nullable = false)
    val active : Boolean = false

) : BaseEntity()
