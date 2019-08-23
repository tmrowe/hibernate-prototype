package com.prototype.hibernate.controller

import com.prototype.hibernate.model.Account
import com.prototype.hibernate.repository.AccountRepository
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
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
        value = ["/"],
        method = [RequestMethod.GET]
    )
    fun getAccounts() : List<Account> {
        return accountRepository.findAll()
    }

    @RequestMapping(
        value = ["/uuid"],
        method = [RequestMethod.GET]
    )
    fun getAccountByUuid(
        @PathVariable uuid : UUID
    ) : Optional<Account> {
        return accountRepository.findById(uuid)
    }

}
