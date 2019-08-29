package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.ListEntity
import com.prototype.hibernate.model.mapper.toEntity
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.repository.crud.ListRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class ListService(
    private val listRepository : ListRepository,
    private val accountRepository : AccountRepository
) : IListService {

    override fun create(createdByUuid : UUID, listDTO : ListDTO) : ListEntity {
        val account = accountRepository.getOne(createdByUuid)
        val list = listDTO.toEntity(account)
        return listRepository.save(list)
    }

    override fun findAll(
        page : Int,
        size : Int,
        sortDirection : Sort.Direction,
        sortField : Array<String>
    ): Page<ListEntity> {
        val pageRequest = PageRequest.of(page, size, sortDirection, *sortField)
        return listRepository.findAll(pageRequest)
    }

    override fun findByUuid(uuid : UUID) : Optional<ListEntity> {
        return listRepository.findById(uuid)
    }

    override fun update(uuid : UUID, listDTO : ListDTO) : ListEntity {
        val list = listRepository.findById(uuid).get()
        val updatedList = list.copy(
            name = listDTO.name,
            description = listDTO.description
        )
        return listRepository.save(updatedList)
    }

    override fun deleteByUuid(uuid : UUID) {
        return listRepository.deleteById(uuid)
    }

}
