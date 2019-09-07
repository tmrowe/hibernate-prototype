package com.prototype.hibernate.repository.crud

import com.prototype.hibernate.model.entity.AccountList
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*
import javax.transaction.Transactional

@Repository
interface AccountListRepository : JpaRepository<AccountList, AccountListId> {

    @Modifying
    @Transactional
    @Query("DELETE FROM AccountList a WHERE a.uuid.list_uuid = :listUuid")
    fun deleteByListUuid(listUuid : UUID)

}
