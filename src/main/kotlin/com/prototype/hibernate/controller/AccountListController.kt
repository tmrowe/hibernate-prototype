package com.prototype.hibernate.controller

import com.prototype.hibernate.model.dto.AccountListDTO
import com.prototype.hibernate.model.entity.AccountListEntity
import com.prototype.hibernate.service.AccountListService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(
    value = ["account_list"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AccountListController(
    private val accountListService : AccountListService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(
        @RequestParam accountUuid : UUID,
        @RequestParam listUuid : UUID,
        @RequestBody AccountListDTO : AccountListDTO
    ) : AccountListEntity {
        return accountListService.create(accountUuid, listUuid, AccountListDTO)
    }

    @GetMapping
    fun find(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "uuid") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<AccountListEntity> {
        return accountListService.findAll(page, size, sortDirection, sortField)
    }

    @GetMapping(value = ["/uuid/{uuid}"])
    fun findByUuid(
        @PathVariable uuid : UUID
    ) : ResponseEntity<AccountListEntity> {
        val account = accountListService.findByUuid(uuid)
        if (account.isPresent) {
            return ResponseEntity(account.get(), HttpStatus.OK)
        }

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(
        value = ["/uuid/{uuid}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(
        @PathVariable uuid : UUID,
        @RequestBody accountListDTO : AccountListDTO
    ) : AccountListEntity {
        return accountListService.update(uuid, accountListDTO)
    }

    @DeleteMapping(value = ["/uuid/{uuid}"])
    fun deleteByUuid(
        @PathVariable uuid : UUID
    ) {
        return accountListService.deleteByUuid(uuid)
    }

}
