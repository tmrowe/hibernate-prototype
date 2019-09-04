package com.prototype.hibernate.mock.repository.crud

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.mockito.ArgumentMatchers
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

open class MockBuilderRepository <Repository : JpaRepository<Entity, UUID>, Entity : Any, UUID : Any>(
    val repository : Repository,
    val entity : Entity,
    val uuid : UUID
) {

    val optionalEntity = mock<Optional<Entity>>()
    val listEntity = mock<List<Entity>>()
    val pageEntity = mock<Page<Entity>>()

    init {

        whenever(optionalEntity.get())
            .thenReturn(entity)

        whenever(repository.findById(ArgumentMatchers.any(uuid::class.java)))
            .thenReturn(optionalEntity)

        whenever(repository.save(ArgumentMatchers.any(entity::class.java)))
            .thenReturn(entity)

        whenever(repository.findAll())
            .thenReturn(listEntity)

        whenever(repository.findAll(any<Sort>()))
            .thenReturn(listEntity)

        whenever(repository.findAll(any<Example<Entity>>()))
            .thenReturn(listEntity)

        whenever(repository.findAll(any<Example<Entity>>(), any<Sort>()))
            .thenReturn(listEntity)

        whenever(repository.findAll(any<Example<Entity>>(), any<PageRequest>()))
            .thenReturn(pageEntity)

        whenever(repository.findAll(any<PageRequest>()))
            .thenReturn(pageEntity)

        whenever(repository.getOne(ArgumentMatchers.any(uuid::class.java)))
            .thenReturn(entity)

        whenever(repository.findOne(any<Example<Entity>>()))
            .thenReturn(optionalEntity)

        whenever(repository.findAllById(any<Iterable<UUID>>()))
            .thenReturn(listEntity)

        whenever(repository.saveAll(any<Iterable<Entity>>()))
            .thenReturn(listEntity)

    }

}
