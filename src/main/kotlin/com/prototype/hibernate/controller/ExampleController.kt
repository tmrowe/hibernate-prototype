package com.prototype.hibernate.controller

import com.prototype.hibernate.model.view.ActiveView
import com.prototype.hibernate.model.view.CustomView
import com.prototype.hibernate.model.view.EmailView
import com.prototype.hibernate.repository.crud.*
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(
    value = ["example"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ExampleController(
    private val accountRepository: AccountRepository
) {

    @GetMapping
    fun getAllAsEmailView() : List<EmailView> {
        return accountRepository.findAll(EmailView::class.java)
    }

    @GetMapping(value = ["/email/{email}/active_view"])
    fun getActiveView(
        @PathVariable email : String
    ) : Optional<ActiveView> {
        return accountRepository.findByEmail(email, ActiveView::class.java)
    }

    @GetMapping(value = ["/email/{email}/email_view"])
    fun getEmailView(
        @PathVariable email : String
    ) : Optional<EmailView> {
        return accountRepository.findByEmail(email, EmailView::class.java)
    }

    @GetMapping(value = ["/email/{email}/custom_view"])
    fun getCustomView(
        @PathVariable email : String
    ) : Optional<CustomView> {
        return accountRepository.findByEmail(email, CustomView::class.java)
    }

}
