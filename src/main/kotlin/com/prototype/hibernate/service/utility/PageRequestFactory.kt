package com.prototype.hibernate.service.utility

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Component

/**
 * This class is responsible for building instances of [PageRequest].
 *
 * This has been abstracted so that we can mock out this functionality in tests.
 */
@Component
class PageRequestFactory {

    fun build(
        page : Int,
        size: Int,
        sortDirection : Sort.Direction,
        sortField : Array<String>
    ) : PageRequest {
        return PageRequest.of(page, size, sortDirection, *sortField)
    }

}
