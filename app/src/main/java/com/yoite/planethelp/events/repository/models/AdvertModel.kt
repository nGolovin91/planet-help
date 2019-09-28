package com.yoite.planethelp.events.repository.models

import java.io.Serializable


data class AdvertModel(
    var id: Long = 0,
    var name: String = "",
    var phone: String = "",
    var email: String = "",
    var address: String = ""
) : Serializable