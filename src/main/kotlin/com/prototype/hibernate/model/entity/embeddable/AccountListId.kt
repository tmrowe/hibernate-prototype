package com.prototype.hibernate.model.entity.embeddable

import java.io.Serializable
import java.util.*
import javax.persistence.Embeddable

@Embeddable
data class AccountListId (

    val account_uuid : UUID,
    val list_uuid : UUID

) : Serializable
