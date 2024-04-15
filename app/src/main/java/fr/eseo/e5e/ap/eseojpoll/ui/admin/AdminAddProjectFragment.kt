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
import androidx.core.view.get
import androidx.core.view.size
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
    private lateinit var studentAdapter: ArrayAdapter<Student>

    private lateinit var rvStudents : RecyclerView
    private var listStudentsToAdd : ArrayList<Student> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminAddProjectBinding.inflate(inflater, container, false)
        val view = binding.root
        val studentDataList = ArrayList<Student>()

        spinStudents = view.findViewById(R.id.spin_project_students)


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
                    studentAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, studentDataList)
                    studentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinStudents.adapter = studentAdapter
                }
            }
            .addOnFailureListener { e -> Log.w(tag, "Error getting student documents: ", e) }

        //Spinner observers
        spinStudents.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position) as Student
                //The selection is added to the RecyclerView
                listStudentsToAdd.add(selectedItem)
                Log.d(tag, "selected $selectedItem")
                Log.d(tag, "listStudentToAdd size = " + listStudentsToAdd.size)
                //And removed from the Spinner
                studentDataList.remove(selectedItem)
                studentAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_add_project, container, false)
    }
}