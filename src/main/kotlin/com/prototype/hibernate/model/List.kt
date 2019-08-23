package com.prototype.hibernate.model

import java.sql.Timestamp
import java.util.*
import javax.persistence.*

//@Entity
//data class List (
//
//    @Id
//    @GeneratedValue
//    @Column(unique = true, nullable = false)
//    val uuid : UUID = UUID.fromString("00000000-0000-0000-0000-000000000000"),
//
//    @OneToOne
//    //@JoinColumn(name = "fk_account")
//    val createdBy : Account,
//
//    @Column(nullable = false)
//    val name : String,
//
//    @Column
//    val description : String?,
//
//    @GeneratedValue
//    @Column(nullable = false)
//    val createdAt : Timestamp = Timestamp(Date().time),
//
//    @GeneratedValue
//    @Column(nullable = false)
//    val updatedAt : Timestamp = Timestamp(Date().time)
//
//)
