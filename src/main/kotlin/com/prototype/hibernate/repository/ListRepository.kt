package com.prototype.hibernate.repository

import com.prototype.hibernate.model.List
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ListRepository : JpaRepository<List, UUID> {

    fun findByName(name : String) : Optional<List>

}
