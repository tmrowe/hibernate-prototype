package com.prototype.hibernate.repository.crud

import com.prototype.hibernate.model.entity.List
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ListRepository : JpaRepository<List, UUID>
