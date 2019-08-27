package com.prototype.hibernate.model

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
data class List (

    @OneToOne(optional = false)
    @JoinColumn
    var createdBy : Account,

    @NaturalId
    @Column(
        unique = true,
        nullable = false
    )
    val name : String,

    @Column
    val description : String?

) : BaseEntity()
