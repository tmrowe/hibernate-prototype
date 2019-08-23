package com.prototype.hibernate.model.example

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import java.sql.Timestamp
import java.util.*

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner::class)
class TestAccountCreation {

    @Test
    fun `create Account object with default values set`() {
        val account = AccountDefault(
            email = "test456@test.org",
            active = true
        )
    }

    @Test
    fun `create Account object with null values set`() {
        val account = AccountNull(
            email = "test456@test.org",
            active = true
        )
    }

    @Test
    fun `create Account object with no default values set, not nullable`() {
        val account = AccountNoDefaultNotNullable(
            uuid = UUID.fromString("00000000-0000-0000-0000-000000000000"),
            email = "test456@test.org",
            active = true,
            createdAt = Timestamp(Date().time),
            updatedAt = Timestamp(Date().time)
        )
    }

    @Test
    fun `create Account object with no default values set, is nullable`() {
        val account = AccountNoDefaultNullable(
            uuid = null,
            email = "test456@test.org",
            active = true,
            createdAt = null,
            updatedAt = null
        )
    }

    @Test
    fun `create Account object with a Java object`() {
        val account = AccountJava()
        account.active = true
    }

}
