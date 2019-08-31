package com.prototype.hibernate.model.dto

import com.prototype.hibernate.model.entity.AccountEntity
import com.prototype.hibernate.model.entity.ListEntity
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import com.prototype.hibernate.model.entity.embeddable.AccountListPermission
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import com.nhaarman.mockito_kotlin.*
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
class TestAccountListDTO {

    private val mockAccountListId = mock<AccountListId>()
    private val mockAccountEntity = mock<AccountEntity>()
    private val mockListEntity = mock<ListEntity>()

    private val accountListDTO = AccountListDTO(
        permission = AccountListPermission(
            canViewList = true,
            canEditList = true,
            canDeleteList = false
        )
    )

    @Test
    fun `AccountListDTO#toEntity should copy all of its properties into an instance of AccountListEntity`() {
        val accountListEntity = accountListDTO.toEntity(
            mockAccountListId,
            mockAccountEntity,
            mockListEntity
        )

        assertEquals(mockAccountListId, accountListEntity.uuid)
        assertEquals(mockAccountEntity, accountListEntity.account)
        assertEquals(mockListEntity, accountListEntity.list)
        assertEquals(accountListDTO.permission, accountListEntity.permission)
    }

}
