package fr.eseo.e5e.ap.eseojpoll.ui.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import fr.eseo.e5e.ap.eseojpoll.R
import fr.eseo.e5e.ap.eseojpoll.databinding.FragmentAdminAddStudentBinding

class AdminAddStudentFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    private val tag = "Add Student Form"
    private lateinit var binding : FragmentAdminAddStudentBinding

    private lateinit var formLastName: String
    private lateinit var formFirstName: String
    private lateinit var formLevel: String
    private lateinit var formSpeciality: String

    private lateinit var etLastName: EditText
    private lateinit var etFirstName: EditText
    private lateinit var spinLevel: Spinner
    private lateinit var spinSpeciality: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminAddStudentBinding.inflate(inflater, container, false)
        val view = binding.root

        etLastName = view.findViewById<EditText>(R.id.et_student_lastname)
        etFirstName = view.findViewById<EditText>(R.id.et_student_firstname)
        spinLevel = view.findViewById<Spinner>(R.id.spin_student_level)
        spinSpeciality = view.findViewById<Spinner>(R.id.spin_student_speciality)

        val levels = resources.getStringArray(R.array.list_student_level)
        if (spinLevel != null){
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, levels)
            spinLevel.adapter = adapter
        }
        spinLevel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                formLevel = levels[position]
                Toast.makeText(requireContext(),
                    getString(R.string.student_level) + " " +
                            levels[position], Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
                println("Nothing at level")
            }
        }

        val specialities = resources.getStringArray(R.array.list_student_speciality)
        if (spinSpeciality != null){
            val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_spinner_item, specialities)
            spinSpeciality.adapter = adapter
        }
        spinSpeciality.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                formSpeciality = specialities[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
                println("Nothing at speciality")
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()
        binding.btnAdd.setOnClickListener {
            formLastName = etLastName.text.toString()
            formFirstName = etFirstName.text.toString()
            formLevel = spinLevel.selectedItem.toString()
            formSpeciality = spinSpeciality.selectedItem.toString()

            println("$formFirstName $formLastName, $formLevel, $formSpeciality")

            val student = mapOf(
                "firstName" to formFirstName,
                "lastName" to formLastName,
                "level" to formLevel,
                "speciality" to formSpeciality
            )
            db.collection("students").document("$formFirstName $formLastName")
                .set(student)
                .addOnSuccessListener { Log.d(tag, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(tag, "Error writing document", e)}
        }
        binding.btnClear.setOnClickListener {
            etFirstName.text.clear()
            etLastName.text.clear()
            spinLevel.setSelection(0)
            spinSpeciality.setSelection(0)
        }
    }
}