package com.prototype.hibernate.repository.crud

import com.prototype.hibernate.model.entity.ListEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ListRepository : JpaRepository<ListEntity, UUID>
