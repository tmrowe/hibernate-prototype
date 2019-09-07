package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.List
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import java.util.*

interface IListService {

    fun create(createdByUuid : UUID, listDTO: ListDTO) : List

    fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField: Array<String>
    ) : Page<List>

    fun findByUuid(uuid : UUID) : Optional<List>

    fun update(uuid : UUID, listDTO : ListDTO): List

    fun deleteByUuid(uuid : UUID)

}
