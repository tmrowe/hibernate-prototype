package com.prototype.hibernate.model

import javax.persistence.*

@Entity
data class AccountList (

    @ManyToOne
    val account : Account,

    @ManyToOne
    val list : List,

    @OneToOne(
        optional = false,
        cascade = [CascadeType.ALL]
    )
    @JoinColumn
    val permission : AccountListPermission = AccountListPermission()

) : BaseEntity()
