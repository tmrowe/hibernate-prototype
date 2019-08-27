package com.prototype.hibernate.repository

import com.prototype.hibernate.model.Account
import com.prototype.hibernate.model.AccountList
import com.prototype.hibernate.model.AccountListPermission
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
class TestAccountList {

    @Autowired
    lateinit var listRepository : ListRepository

    @Autowired
    lateinit var accountRepository : AccountRepository

    @Autowired
    lateinit var accountListRepository : AccountListRepository

    private val account = Account(
        email = "some email address"
    )

    private val list = com.prototype.hibernate.model.List(
        createdBy = account,
        name = "some name",
        description = "some description"
    )

    private val accountListPermission = AccountListPermission(
        canViewList = true,
        canEditList = true,
        canDeleteList = false
    )

    @Before
    fun setup() {
        accountListRepository.deleteAllInBatch()
        listRepository.deleteAllInBatch()
        accountRepository.deleteAllInBatch()

        accountRepository.save(account)
        listRepository.save(list)
    }

    @After
    fun teardown() {
        accountListRepository.deleteAllInBatch()
        listRepository.deleteAllInBatch()
        accountRepository.deleteAllInBatch()
    }

    @Test
    fun `create and save AccountList object with default values set`() {
        val accountList = AccountList(
            account = account,
            list = list,
            permission = accountListPermission
        )
        accountListRepository.save(accountList)
        assertEquals(1, accountRepository.count())
    }

}
