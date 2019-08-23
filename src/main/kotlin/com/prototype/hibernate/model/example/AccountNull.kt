package com.prototype.hibernate.model.example

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Table(name = "account")
@Entity
data class AccountNull (

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    val uuid : UUID? = null,

    @Column(nullable = false)
    val isActive : Boolean = false,

    @CreationTimestamp
    val createdAt : Timestamp? = null,

    @UpdateTimestamp
    val updatedAt : Timestamp? = null

)