package com.prototype.hibernate.repository.crud

import com.prototype.hibernate.model.entity.*
import org.junit.Assert.assertEquals
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

    private val account = AccountEntity(
        email = "some email address"
    )

    private val list = ListEntity(
        createdBy = account,
        name = "some name",
        description = "some description"
    )

    private val accountListPermission = AccountListPermissionEntity(
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
        val accountList = AccountListEntity(
            account = account,
            list = list,
            permission = accountListPermission
        )
        accountListRepository.save(accountList)
        assertEquals(1, accountRepository.count())
    }

}
