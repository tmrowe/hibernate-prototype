package com.prototype.hibernate.model.entity.embeddable

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class AccountListPermission (

    @Column(nullable = false)
    val canViewList : Boolean,

    @Column(nullable = false)
    val canEditList : Boolean,

    @Column(nullable = false)
    val canDeleteList : Boolean

) : Serializable
