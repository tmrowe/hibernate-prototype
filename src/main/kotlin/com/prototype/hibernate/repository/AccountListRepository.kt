package com.prototype.hibernate.repository

import com.prototype.hibernate.model.AccountList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountListRepository : JpaRepository<AccountList, UUID>
