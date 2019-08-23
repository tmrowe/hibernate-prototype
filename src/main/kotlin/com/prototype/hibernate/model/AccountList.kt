//package com.prototype.hibernate.model
//
//import java.sql.Timestamp
//import javax.persistence.*
//
//@Entity
//data class AccountList (
//
//    // TODO: Composite ID.
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("user_uuid")
//    val accounts : Set<Account> = setOf(),
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @MapsId("list_uuid")
//    val lists : Set<List> = setOf(),
//
//    @OneToOne
//    @Column(nullable = false)
//    val permission : UserListPermission,
//
//    @GeneratedValue
//    @Column(nullable = false)
//    val createdAt : Timestamp,
//
//    @GeneratedValue
//    @Column(nullable = false)
//    val updatedAt : Timestamp
//
//)
