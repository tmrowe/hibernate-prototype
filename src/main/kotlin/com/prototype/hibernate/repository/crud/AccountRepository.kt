package com.prototype.hibernate.repository.crud

import org.springframework.data.domain.Page
import com.prototype.hibernate.model.entity.Account
import com.prototype.hibernate.model.view.AccountListView
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {

    fun <T> findByActiveTrue(pageable : Pageable, type : Class<T>) : Page<T>
    fun <T> findByActiveFalse(pageable : Pageable, type : Class<T>) : Page<T>
    fun <T> findByEmail(email : String, type : Class<T>) : Optional<T>

    @Query("SELECT a FROM Account a")
    fun <T> findAll(type : Class<T>) : List<T>

    @Query("SELECT a.email as email, l.list.name as name FROM Account a JOIN a.accountList l")
    fun getAccountListView() : List<AccountListView>

}
