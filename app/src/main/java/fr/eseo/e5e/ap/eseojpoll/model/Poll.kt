package fr.eseo.e5e.ap.eseojpoll.model

import java.util.Date


data class Poll(
    var name: String,
    var date: Date,
    var isActive: Boolean = false
)