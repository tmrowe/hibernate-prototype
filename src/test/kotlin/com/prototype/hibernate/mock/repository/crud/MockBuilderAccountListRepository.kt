package com.prototype.hibernate.mock.repository.crud

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.prototype.hibernate.model.entity.AccountListEntity
import com.prototype.hibernate.model.entity.embeddable.AccountListId
import com.prototype.hibernate.repository.crud.AccountListRepository

class MockBuilderAccountListRepository : MockBuilderRepository<AccountListRepository, AccountListEntity, AccountListId>(
    repository = mock(),
    entity = mock(),
    uuid = mock()
) {

    init {

        whenever(entity.uuid)
            .thenReturn(uuid)

        whenever(entity.copy())
            .thenReturn(entity)

    }

}
