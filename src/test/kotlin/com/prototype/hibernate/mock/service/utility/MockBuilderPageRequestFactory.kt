package com.prototype.hibernate.mock.service.utility

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import com.prototype.hibernate.service.utility.PageRequestFactory
import org.springframework.data.domain.PageRequest

class MockBuilderPageRequestFactory {

    val pageRequestFactory = mock<PageRequestFactory>()
    val pageRequest = mock<PageRequest>()

    init {

        whenever(pageRequestFactory.build(any(), any(), any(), any()))
            .thenReturn(pageRequest)

    }

}
