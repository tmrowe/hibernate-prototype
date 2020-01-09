package com.prototype.hibernate.controller

import com.prototype.hibernate.model.dto.ListDTO
import com.prototype.hibernate.model.entity.List
import com.prototype.hibernate.service.ListService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(
    value = ["list"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class ListController(
    private val listService : ListService
) {

    // Once we have awareness of the user making the request we can use that uuid rather than passing it in.
    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun create(
        @RequestParam createdByUuid : UUID,
        @RequestBody listDTO : ListDTO
    ) : List {
        return listService.create(createdByUuid, listDTO)
    }

    @GetMapping
    fun find(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "20") size: Int,
        @RequestParam(defaultValue = "name") sortField: Array<String>,
        @RequestParam(defaultValue = "DESC") sortDirection: Sort.Direction
    ) : Page<List> {
        return listService.findAll(page, size, sortDirection, sortField)
    }

    @GetMapping(value = ["/uuid/{uuid}"])
    fun findByUuid(
        @PathVariable uuid : UUID
    ) : ResponseEntity<List> {
        val account = listService.findByUuid(uuid)
        if (account.isPresent) {
            return ResponseEntity(account.get(), HttpStatus.OK)
        }

        return ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(
        value = ["/uuid/{uuid}"],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun update(
        @PathVariable uuid : UUID,
        @RequestBody listDTO : ListDTO
    ) : List {
        return listService.update(uuid, listDTO)
    }

    @DeleteMapping(value = ["/uuid/{uuid}"])
    fun deleteByUuid(
        @PathVariable uuid : UUID
    ) {
        return listService.deleteByUuid(uuid)
    }

}
