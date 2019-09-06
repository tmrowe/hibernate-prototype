package com.prototype.hibernate.model.view

import org.springframework.beans.factory.annotation.Value

interface CustomView {

    @get:Value("#{'The account with email' + target.email + ' ' + 'is active? ' + target.active}")
    val customString : String

}
