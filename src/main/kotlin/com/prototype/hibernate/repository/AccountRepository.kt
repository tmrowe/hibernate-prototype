package com.prototype.hibernate.repository

import org.springframework.data.domain.Page
import com.prototype.hibernate.model.entity.AccountEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountRepository : JpaRepository<AccountEntity, UUID> {

    fun findByActiveTrue(pageable : Pageable) : Page<AccountEntity>
    fun findByActiveFalse(pageable : Pageable) : Page<AccountEntity>
    fun findByEmail(email : String) : Optional<AccountEntity>

}
