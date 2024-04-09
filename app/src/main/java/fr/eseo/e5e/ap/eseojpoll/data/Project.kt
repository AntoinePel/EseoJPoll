package fr.eseo.e5e.ap.eseojpoll.data

data class ProjectInfo(
    var projectList: ArrayList<ProjectItem> = arrayListOf()
)

data class ProjectItem(
    var name: String,
    var photo: String,
    var description: String,
    var students: ArrayList<StudentItem> = arrayListOf()
)
