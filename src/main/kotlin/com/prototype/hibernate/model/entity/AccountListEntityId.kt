package com.prototype.hibernate.model.entity

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class AccountListEntityId (
    val account_uuid : UUID,
    val list_uuid : UUID
) : Serializable
