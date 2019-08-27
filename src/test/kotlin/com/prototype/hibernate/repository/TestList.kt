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
class TestList {

    @Autowired
    lateinit var listRepository : ListRepository

    @Autowired
    lateinit var accountRepository : AccountRepository

    private val account = Account(
        email = "test123@test.com"
    )

    private val name = "some name"
    private val list = com.prototype.hibernate.model.List(
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

    @Test
    fun `ListRepository#findByName should return a List if an List with the given name exists`() {
        listRepository.save(list)
        val retrievedList = listRepository.findByName(name).get()

        assertEquals(list, retrievedList)
    }

}
