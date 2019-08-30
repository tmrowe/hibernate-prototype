package com.prototype.hibernate.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "account_list")
data class AccountListEntity (

    @EmbeddedId
    val uuid : AccountListEntityId? = null,

    @ManyToOne
    @MapsId(value = "account_uuid")
    val account : AccountEntity? = null,

    @ManyToOne
    @MapsId(value = "list_uuid")
    val list : ListEntity? = null,

    @OneToOne(
        optional = false,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn
    val permission : AccountListPermissionEntity,

    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null

)
