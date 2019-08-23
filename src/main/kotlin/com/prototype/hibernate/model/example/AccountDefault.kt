package com.prototype.hibernate.model.example

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@Table(name = "account")
@Entity
data class AccountDefault (

    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    val uuid : UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),

    @Column(nullable = false)
    val isActive : Boolean = false,

    @CreationTimestamp
    val createdAt : Timestamp = Timestamp(Date().time),

    @UpdateTimestamp
    val updatedAt : Timestamp = Timestamp(Date().time)

)
