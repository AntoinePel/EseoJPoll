package fr.eseo.e5e.ap.eseojpoll.data

import java.util.Date

data class PollInfo(
    var projectList: ArrayList<PollItem> = arrayListOf()
)

data class PollItem(
    var name: String,
    var date: Date,
    var isActive: Boolean = false
)