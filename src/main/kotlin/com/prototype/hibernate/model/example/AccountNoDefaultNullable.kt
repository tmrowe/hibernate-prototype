package com.prototype.hibernate.model.example

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Table(name = "account")
@Entity
data class AccountNoDefaultNullable (

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    val uuid : UUID?,

    @Column(unique = true, nullable = false)
    val email : String,

    @Column(nullable = false)
    val active : Boolean = false,

    @CreationTimestamp
    val createdAt : Timestamp?,

    @UpdateTimestamp
    val updatedAt : Timestamp?

)
