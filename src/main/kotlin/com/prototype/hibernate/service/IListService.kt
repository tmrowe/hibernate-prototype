package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.ListEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import java.util.*

interface IListService {

    fun create(createdByUuid : UUID, listDTO: ListDTO) : ListEntity

    fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<ListEntity>

    fun findByUuid(uuid : UUID) : Optional<ListEntity>

    fun update(uuid : UUID, listDTO : ListDTO): ListEntity

    fun deleteByUuid(uuid : UUID)

}
