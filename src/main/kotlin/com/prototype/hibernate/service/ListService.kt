package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.dto.AccountListPermissionDTO
import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.ListEntity
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.repository.crud.ListRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ListService(
    private val listRepository : ListRepository,
    private val accountRepository : AccountRepository,
    private val accountListService : AccountListService
) : IListService {

    override fun create(createdByUuid : UUID, listDTO : ListDTO) : ListEntity {
        val listOwner = accountRepository.getOne(createdByUuid)
        val createdList = listRepository.save(listDTO.toEntity(listOwner))
        grantListOwnerPermission(createdByUuid, createdList.uuid!!)
        return createdList
    }

    private fun grantListOwnerPermission(userUuid : UUID, listUuid: UUID) {
        accountListService.create(userUuid, listUuid, AccountListDTO(
            permission = AccountListPermission(
                canViewList = true,
                canEditList = true,
                canDeleteList = true
            )
        ))
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

    @Transactional
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
