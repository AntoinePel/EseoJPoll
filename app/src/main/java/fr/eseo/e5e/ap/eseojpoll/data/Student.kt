package fr.eseo.e5e.ap.eseojpoll.data

data class StudentInfo(
    var projectList: ArrayList<StudentItem> = arrayListOf()
)

data class StudentItem(
    var firstname: String,
    var lastname: String,
    var promotion: String,
    var speciality: String
)