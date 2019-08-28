package com.prototype.hibernate.model.entity

import javax.persistence.*

@Entity
@Table(name = "list")
data class ListEntity (

    @OneToOne(optional = false)
    @JoinColumn
    var createdBy : AccountEntity,

    @Column(
        unique = true,
        nullable = false
    )
    val name : String,

    @Column
    val description : String?

) : BaseEntity()
