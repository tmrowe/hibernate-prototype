package com.prototype.hibernate.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account_list")
data class AccountListEntity (

    @Id
    @GeneratedValue
    @Column(
        unique = true,
        nullable = false
    )
    val uuid : UUID? = null,

    @ManyToOne
    val account : AccountEntity,

    @ManyToOne
    val list : ListEntity,

    @OneToOne(
        optional = false,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn
    val permission : AccountListPermissionEntity = AccountListPermissionEntity(),

    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null

)
