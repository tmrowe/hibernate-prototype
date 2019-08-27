package com.prototype.hibernate.repository

import com.prototype.hibernate.model.Account
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
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

    private val email = "test123@test.com"
    private val account = Account(
        email = email
    )

    @Before
    fun setup() {
        accountRepository.deleteAllInBatch()
    }

    @After
    fun teardown() {
        accountRepository.deleteAllInBatch()
    }

    @Test
    fun `create and save Account object with default values set`() {
        accountRepository.save(account)
        assertEquals(1, accountRepository.count())
    }

    @Test
    fun `AccountRepository#findByEmail should return an Account if an account with the email exists`() {
        accountRepository.save(account)
        val retrievedAccount = accountRepository.findByEmail(email).get()

        assertEquals(account, retrievedAccount)
    }

}
