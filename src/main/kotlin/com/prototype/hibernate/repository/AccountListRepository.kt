package com.prototype.hibernate.repository

import com.prototype.hibernate.model.entity.AccountListEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AccountListRepository : JpaRepository<AccountListEntity, UUID>
