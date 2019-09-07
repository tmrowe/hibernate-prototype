package com.prototype.hibernate.controller

import com.prototype.hibernate.model.dto.AccountDTO
import com.prototype.hibernate.model.entity.Account
import com.prototype.hibernate.service.IAccountService
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Sort
import java.util.*

@RestController
@RequestMapping(
    value = ["account"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class AccountController(
    private val accountService : IAccountService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(
        @RequestBody accountDTO : AccountDTO
    ) : Account {
        return accountService.create(accountDTO)
    }

    @GetMapping
    fun find(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "email") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<Account> {
        return accountService.findAll(page, size, sortDirection, sortField)
    }

    @GetMapping(value = ["/active"])
    fun findActive(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "email") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<Account> {
        return accountService.findActive(page, size, sortDirection, sortField)
    }

    @GetMapping(value = ["/inactive"])
    fun findInactive(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "email") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<Account> {
        return accountService.findInactive(page, size, sortDirection, sortField)
    }

    @GetMapping(value = ["/uuid/{uuid}"])
    fun findByUuid(
        @PathVariable uuid : UUID
    ) : ResponseEntity<Account> {
        val account = accountService.findByUuid(uuid)
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
        @RequestBody accountDTO : AccountDTO
    ) : Account {
        return accountService.update(uuid, accountDTO)
    }

    @GetMapping(value = ["/email/{email}"])
    fun findByEmail(
        @PathVariable email : String
    ) : ResponseEntity<Account> {
        val account = accountService.findByEmail(email)
        if (account.isPresent)
            return ResponseEntity(account.get(), HttpStatus.OK)

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

}
