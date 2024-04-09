package fr.eseo.e5e.ap.eseojpoll.data

data class PollProjectInfo(
    var projectList: ArrayList<PollProjectItem> = arrayListOf()
)

data class PollProjectItem(
    var poll: PollItem,
    var project: ProjectItem,
    var nbVote: Int = 0
)