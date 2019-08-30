package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.AccountEntity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class TestListDTO {

    private val mockAccountEntity = Mockito.mock(AccountEntity::class.java)

    @Test
    fun `ListDTO#toEntity should copy all of its properties into an instance of ListEntity`() {
        val listDTO = ListDTO(
            name = "some name",
            description = "some description"
        )

        val accountListEntity = listDTO.toEntity(mockAccountEntity)

        assertEquals(mockAccountEntity, accountListEntity.createdBy)
        assertEquals(listDTO.name, accountListEntity.name)
        assertEquals(listDTO.description, accountListEntity.description)
    }

    @Test
    fun `ListDTO#toEntity should copy a null description into ListEntity`() {
        val listDTO = ListDTO(
            name = "some name",
            description = null
        )

        val accountListEntity = listDTO.toEntity(mockAccountEntity)

        assertNull(accountListEntity.description)
    }

}
