package com.prototype.hibernate.model.example

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

@Table(name = "account")
@Entity
data class AccountDefault (

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    val uuid : UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(unique = true, nullable = false)
    val email : String,

    @Column(nullable = false)
    val active : Boolean = false,

    @CreationTimestamp
    val createdAt : LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    val updatedAt : LocalDateTime = LocalDateTime.now()

)
