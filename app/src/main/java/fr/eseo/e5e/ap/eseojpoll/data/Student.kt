package fr.eseo.e5e.ap.eseojpoll.data

import com.google.firebase.firestore.FirebaseFirestore

data class StudentInfo(
    var projectList: ArrayList<StudentItem> = arrayListOf()
)

data class StudentItem(
    var firstname: String,
    var lastname: String,
    var promotion: String,
    var speciality: String
)


private fun writeDataOnFirestore(studentItem: StudentItem){
    /*val student = HashMap<String, Any>()
    student["firstname"] = studentItem.firstname
    student["lastname"] = studentItem.lastname
    student["promotion"] = studentItem.promotion
    student["speciality"] = studentItem.speciality
    mFirestore.collection("students").document("")*/
}