package com.prototype.hibernate.service

import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.List
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission
import com.prototype.hibernate.repository.crud.AccountListRepository
import com.prototype.hibernate.repository.crud.AccountRepository
import com.prototype.hibernate.repository.crud.ListRepository
import com.prototype.hibernate.service.utility.PageRequestFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ListService(
    private val listRepository : ListRepository,
    private val accountRepository : AccountRepository,
    private val accountListRepository : AccountListRepository,
    private val accountListService : AccountListService,
    private val pageRequestFactory : PageRequestFactory
) : IListService {

    override fun create(createdByUuid : UUID, listDTO : ListDTO) : List {
        val listOwner = accountRepository.findById(createdByUuid).get()
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
    ): Page<List> {
        val pageRequest = pageRequestFactory.build(page, size, sortDirection, sortField)
        return listRepository.findAll(pageRequest)
    }

    override fun findByUuid(uuid : UUID) : Optional<List> {
        return listRepository.findById(uuid)
    }

    @Transactional
    override fun update(uuid : UUID, listDTO : ListDTO) : List {
        val list = listRepository.findById(uuid).get()
        val updatedList = list.copy(
            name = listDTO.name,
            description = listDTO.description
        )
        return listRepository.save(updatedList)
    }

    @Transactional
    override fun deleteByUuid(uuid : UUID) {
        accountListRepository.deleteByListUuid(uuid)
        return listRepository.deleteById(uuid)
    }

}
