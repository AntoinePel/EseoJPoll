package fr.eseo.e5e.ap.eseojpoll.ui.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import fr.eseo.e5e.ap.eseojpoll.R
import fr.eseo.e5e.ap.eseojpoll.databinding.FragmentAdminAddProjectBinding
import fr.eseo.e5e.ap.eseojpoll.model.Student

class AdminAddProjectFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    private val tag = "Add Project Form"
    private lateinit var binding : FragmentAdminAddProjectBinding

    private val studentsCollection = db.collection("students")
    private lateinit var spinStudents: Spinner
    private lateinit var studentSpinnerAdapter: ArrayAdapter<Student>
    private var studentDataList : ArrayList<Student> = arrayListOf()

    private lateinit var rvStudents : RecyclerView
    private var listStudentsToAdd : ArrayList<Student> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminAddProjectBinding.inflate(inflater, container, false)
        val view = binding.root

        spinStudents = view.findViewById(R.id.spin_project_students)

        studentDataList = ArrayList<Student>()
        studentDataList.add(Student(firstName = getString(R.string.default_text_spinner_students)))

        studentSpinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, studentDataList)
        studentSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinStudents.adapter = studentSpinnerAdapter

        //Student spinner adapter for the project
        studentsCollection.get()
            .addOnSuccessListener { documents ->
                if(documents != null){
                    //studentDataList.add(getString(R.string.default_text_spinner_students))
                    for (document in documents){
                        val firstName = document.getString("firstName")
                        val lastName = document.getString("lastName")
                        val level = document.getString("level")
                        val speciality = document.getString("speciality")

                        if (firstName != null && lastName != null && level != null && speciality != null) {
                            val student = Student(firstName, lastName, level, speciality)
                            studentDataList.add(student)
                            Log.d(tag, "found $student")
                        } else {
                            Log.w(tag, "One or more fields are null for document ${document.id}")
                        }
                    }
                    studentSpinnerAdapter.notifyDataSetChanged()
                }
            }
            .addOnFailureListener { e -> Log.w(tag, "Error getting student documents: ", e) }

        rvStudents = view.findViewById(R.id.rv_project_students)
        rvStudents.layoutManager = LinearLayoutManager(requireContext())
        val studentsRVAdapter = StudentsAdapter(listStudentsToAdd)
        rvStudents.adapter = studentsRVAdapter

        //Spinner observers
        spinStudents.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position > 0){
                    val selectedItem = parent.getItemAtPosition(position) as Student
                    //The selection is added to the RecyclerView
                    Log.d(tag, "listStudentToAdd size before = " + listStudentsToAdd.size)
                    listStudentsToAdd.add(selectedItem)
                    Log.d(tag, "listStudentToAdd size after = " + listStudentsToAdd.size)
                    Log.d(tag, "studentDataList size before = " + studentDataList.size)
                    //And removed from the Spinner
                    Log.d(tag, "selected n°${position} : $selectedItem")
                    studentDataList.remove(selectedItem)
                    Log.d(tag, "studentDataList size after = " + studentDataList.size)
                    studentSpinnerAdapter.notifyDataSetChanged()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        // Inflate the layout for this fragment
        return view
    }
}