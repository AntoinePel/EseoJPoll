package fr.eseo.e5e.ap.eseojpoll.model

data class PollProject(
    var poll: Poll,
    var project: Project,
    var nbVote: Int? = 0
)