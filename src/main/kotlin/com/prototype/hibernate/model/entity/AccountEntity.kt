package com.prototype.hibernate.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
data class AccountEntity (

    @Id
    @GeneratedValue
    @Column(
        unique = true,
        nullable = false
    )
    val uuid : UUID? = null,

    @Column(
        unique = true,
        nullable = false
    )
    val email : String,

    @Column(nullable = false)
    val active : Boolean = false,

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @Column(nullable = false)
    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null

)
