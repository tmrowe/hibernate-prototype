package com.prototype.hibernate.model.entity

import com.prototype.hibernate.model.entity.embeddable.AccountListId
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class AccountList (

    @EmbeddedId
    val uuid : AccountListId,

    @ManyToOne
    @MapsId(value = "account_uuid")
    val account : Account,

    @ManyToOne
    @MapsId(value = "list_uuid")
    val list : List,

    @Embedded
    val permission : AccountListPermission,

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @Column(nullable = false)
    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null

)
