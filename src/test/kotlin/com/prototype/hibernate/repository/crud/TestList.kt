package com.prototype.hibernate.repository.crud

import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.entity.ListEntity
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
class TestList {

    @Autowired
    lateinit var listRepository : ListRepository

    @Autowired
    lateinit var accountRepository : AccountRepository

    private val account = AccountEntity(
        email = "test123@test.com"
    )

    private val name = "some name"
    private val list = ListEntity(
        createdBy = account,
        name = name,
        description = "some description"
    )

    @Before
    fun setup() {
        listRepository.deleteAllInBatch()
        accountRepository.deleteAllInBatch()

        accountRepository.save(account)
    }

    @After
    fun teardown() {
        listRepository.deleteAllInBatch()
        accountRepository.deleteAllInBatch()
    }

    @Test
    fun `create and save List object with default values set`() {
        listRepository.save(list)
        assertEquals(1, accountRepository.count())
    }

}
