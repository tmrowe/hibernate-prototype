package com.prototype.hibernate.configuration

import org.hibernate.boot.model.naming.Identifier
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy

/**
 * Extends [SpringPhysicalNamingStrategy] to strip off the Entity suffix of our entities.
 *
 * This allows us to suffix all of our entities and still have Hibernate infer our table names.
 */
class PhysicalNamingStrategy : SpringPhysicalNamingStrategy() {

    private val entitySuffix = "Entity"

    override fun toPhysicalTableName(name : Identifier, jdbcEnvironment : JdbcEnvironment) : Identifier {
        val strippedName = name.text.removeSuffix(entitySuffix)
        val identifier = Identifier(strippedName, name.isQuoted)
        return super.toPhysicalTableName(identifier, jdbcEnvironment)
    }

}
