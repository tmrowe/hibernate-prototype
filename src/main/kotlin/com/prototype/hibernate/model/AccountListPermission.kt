package com.prototype.hibernate.model

import java.util.*
import javax.persistence.*

@Entity
data class AccountListPermission (

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    val uuid : UUID,

    @Column(nullable = false)
    val canViewList : Boolean = false,

    @Column(nullable = false)
    val canEditList : Boolean = false,

    @Column(nullable = false)
    val canDeleteList : Boolean = false

)
