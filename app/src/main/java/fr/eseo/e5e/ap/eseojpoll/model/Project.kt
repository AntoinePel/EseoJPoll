package fr.eseo.e5e.ap.eseojpoll.model


data class Project(
    var name: String,
    var description: String,
    var photo: String,
    var students: ArrayList<Student> = arrayListOf()
)
