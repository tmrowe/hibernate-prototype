package com.prototype.hibernate.controller

import com.prototype.hibernate.model.Account
import com.prototype.hibernate.repository.AccountRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
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
    private val accountRepository : AccountRepository
) {

    @RequestMapping(
        method = [RequestMethod.GET]
    )
    fun getAccounts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "email") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<Account> {
        return accountRepository.findAll(PageRequest.of(page, size, sortDirection, *sortField))
    }

    @RequestMapping(
        value = ["/active"],
        method = [RequestMethod.GET]
    )
    fun getActiveAccounts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "email") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<Account> {
        return accountRepository.findByActiveTrue(PageRequest.of(page, size, sortDirection, *sortField))
    }

    @RequestMapping(
        value = ["/inactive"],
        method = [RequestMethod.GET]
    )
    fun getInactiveAccounts(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "email") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<Account> {
        return accountRepository.findByActiveFalse(PageRequest.of(page, size, sortDirection, *sortField))
    }

    @RequestMapping(
        value = ["/uuid/{uuid}"],
        method = [RequestMethod.GET]
    )
    fun getAccountByUuid(
        @PathVariable uuid : UUID
    ) : ResponseEntity<Account> {
        val account = accountRepository.findById(uuid)
        if (account.isPresent)
            return ResponseEntity(account.get(), HttpStatus.OK)

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @RequestMapping(
        value = ["/email/{email}"],
        method = [RequestMethod.GET]
    )
    fun getAccountByEmail(
        @PathVariable email : String
    ) : ResponseEntity<Account> {
        val account = accountRepository.findByEmail(email)
        if (account.isPresent)
            return ResponseEntity(account.get(), HttpStatus.OK)

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

}
