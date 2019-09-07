package com.prototype.hibernate.mock.repository.crud

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.prototype.hibernate.model.entity.Account
import com.prototype.hibernate.repository.crud.AccountRepository
import java.util.*

class MockBuilderAccountRepository : MockBuilderRepository<AccountRepository, Account, UUID>(
    repository = mock(),
    entity = mock(),
    uuid = mock()
) {

    init {

        whenever(repository.findByActiveFalse(any(), any<Class<Account>>()))
            .thenReturn(pageEntity)

        whenever(repository.findByActiveTrue(any(), any<Class<Account>>()))
            .thenReturn(pageEntity)

        whenever(repository.findByEmail(any(), any<Class<Account>>()))
            .thenReturn(optionalEntity)

        whenever(entity.uuid)
            .thenReturn(uuid)

        whenever(entity.copy())
            .thenReturn(entity)

    }

}
