package com.prototype.hibernate.model.entity

import com.prototype.hibernate.model.entity.embeddable.AccountListId
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "account_list")
data class AccountListEntity (

    @EmbeddedId
    val uuid : AccountListId,

    @ManyToOne
    @MapsId(value = "account_uuid")
    val account : AccountEntity? = null,

    @ManyToOne
    @MapsId(value = "list_uuid")
    val list : ListEntity? = null,

    @Embedded
    val permission : AccountListPermission,

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @Column(nullable = false)
    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null

)
