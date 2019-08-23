package com.prototype.hibernate.repository

import org.springframework.data.domain.Page
import com.prototype.hibernate.model.Account
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {

    fun findByActiveTrue(pageable : Pageable) : Page<Account>
    fun findByActiveFalse(pageable : Pageable) : Page<Account>
    fun findByEmail(email: String) : Optional<Account>

}
