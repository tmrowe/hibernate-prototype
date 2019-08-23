package com.prototype.hibernate.model

import com.prototype.hibernate.repository.AccountRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner::class)
class TestAccount {

    @Autowired
    lateinit var accountRepository : AccountRepository

    @Test
    fun `create and save Account object with default values set`() {
        val account = Account(
            email = "test123@test.org",
            active = false
        )
        accountRepository.save(account)
    }

    @Test
    fun findByEmail() {
        val account = accountRepository.findByEmail("test234@test.org")
        println(account)
    }

}
