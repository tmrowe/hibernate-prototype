package com.prototype.hibernate.repository.crud

import com.prototype.hibernate.model.entity.AccountListEntity
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountListRepository : JpaRepository<AccountListEntity, AccountListId>
