package com.prototype.hibernate.repository

import com.prototype.hibernate.model.entity.ListEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ListRepository : JpaRepository<ListEntity, UUID> {

    fun findByName(name : String) : Optional<ListEntity>

}
