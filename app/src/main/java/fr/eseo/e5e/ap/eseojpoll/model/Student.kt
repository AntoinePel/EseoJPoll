package fr.eseo.e5e.ap.eseojpoll.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Student(
    var firstName: String? = "",
    var lastName: String? = "",
    var level: String? = "",
    var speciality: String? = ""
) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}