package com.prototype.hibernate.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account_list_permission")
data class AccountListPermissionEntity (

    @Id
    @GeneratedValue
    @Column(
        unique = true,
        nullable = false
    )
    val uuid : UUID? = null,

    @Column(nullable = false)
    val canViewList : Boolean = false,

    @Column(nullable = false)
    val canEditList : Boolean = false,

    @Column(nullable = false)
    val canDeleteList : Boolean = false,

    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null

)
