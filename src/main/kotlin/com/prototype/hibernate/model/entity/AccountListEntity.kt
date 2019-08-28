package com.prototype.hibernate.model.entity

import javax.persistence.*

@Entity
@Table(name = "account_list")
data class AccountListEntity (

    @ManyToOne
    val account : AccountEntity,

    @ManyToOne
    val list : ListEntity,

    @OneToOne(
        optional = false,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn
    val permission : AccountListPermissionEntity = AccountListPermissionEntity()

) : BaseEntity()
