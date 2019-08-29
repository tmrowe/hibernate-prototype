package com.prototype.hibernate.model.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "list")
data class ListEntity (

    @Id
    @GeneratedValue
    @Column(
        unique = true,
        nullable = false
    )
    val uuid : UUID? = null,

    @OneToOne(optional = false)
    @JoinColumn
    val createdBy : AccountEntity,

    @Column(
        unique = true,
        nullable = false
    )
    val name : String,
    val description : String?,

    @CreationTimestamp
    val createdAt : LocalDateTime? = null,

    @UpdateTimestamp
    val updatedAt : LocalDateTime? = null

)
