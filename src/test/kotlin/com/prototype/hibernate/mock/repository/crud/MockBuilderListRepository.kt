package com.prototype.hibernate.mock.repository.crud

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.prototype.hibernate.model.entity.List
import com.prototype.hibernate.repository.crud.ListRepository
import java.util.*

class MockBuilderListRepository : MockBuilderRepository<ListRepository, List, UUID>(
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
