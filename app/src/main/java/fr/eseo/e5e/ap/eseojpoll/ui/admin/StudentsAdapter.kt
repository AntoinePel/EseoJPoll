package fr.eseo.e5e.ap.eseojpoll.ui.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.eseo.e5e.ap.eseojpoll.R
import fr.eseo.e5e.ap.eseojpoll.model.Student

class StudentsAdapter(private val studentList : ArrayList<Student>) :
    RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder>() {

    class StudentsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val studentFullName : TextView = itemView.findViewById(R.id.name_student_from_project)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student_from_project, parent, false)
        return StudentsViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: StudentsViewHolder, position: Int) {
        val currentItem = studentList[position]
        holder.studentFullName.text = currentItem.toString()
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}