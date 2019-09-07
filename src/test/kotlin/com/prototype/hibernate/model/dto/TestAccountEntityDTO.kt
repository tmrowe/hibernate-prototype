package com.prototype.hibernate.model.dto

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.junit.Assert.*

@RunWith(SpringJUnit4ClassRunner::class)
class TestAccountEntityDTO {

    private val accountDTO = AccountDTO(
        email = "some email",
        active = false
    )

    @Test
    fun `AccountDTO#toEntity should copy all of its properties into an instance of AccountEntity`() {
        val accountEntity = accountDTO.toEntity()

        assertEquals(accountDTO.email, accountEntity.email)
        assertEquals(accountDTO.active, accountEntity.active)
    }

}
