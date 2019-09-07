package com.prototype.hibernate.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
data class List (

    @Id
    @GeneratedValue
    @Column(
        unique = true,
        nullable = false
    )
    val uuid : UUID? = null,

    @OneToOne(optional = false)
    @JoinColumn
    val createdBy : Account,

    @Column(
        unique = true,
        nullable = false
    )
    val name : String,
    val description : String?,

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @Column(nullable = false)
    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null,

    @OneToMany(mappedBy = "list")
    val accountList : Set<AccountList>? = null

)
