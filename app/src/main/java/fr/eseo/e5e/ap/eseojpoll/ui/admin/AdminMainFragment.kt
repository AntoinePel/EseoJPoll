package fr.eseo.e5e.ap.eseojpoll.ui.admin

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.eseo.e5e.ap.eseojpoll.R
import fr.eseo.e5e.ap.eseojpoll.databinding.FragmentAdminMainBinding

class AdminMainFragment : Fragment() {
    private lateinit var fragment: AdminMainFragment
    private lateinit var binding: FragmentAdminMainBinding
    companion object {
        fun newInstance(): AdminMainFragment {
            return AdminMainFragment()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstFragment= AdminNavFirstFragment()
        val secondFragment= AdminNavSecondFragment()

        setCurrentFragment(firstFragment)

        var bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ongoing_poll->setCurrentFragment(firstFragment)
                R.id.other_polls->setCurrentFragment(secondFragment)
            }
            true
        }

        bottomNavigationView.selectedItemId = R.id.ongoing_poll


        val fab: View = view.findViewById(R.id.fab_add)
        fab.setOnClickListener {//view ->
            //val dialog = PasswordDialogFragment()
            //dialog.show(parentFragmentManager, "AddElementDialog")
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Add an element")
            val listItems = arrayOf("Student", "Project", "Poll")
            alertDialog.setSingleChoiceItems(listItems, -1) {dialog, which ->
                when(which){
                    0->setNewFormFragment(AdminAddStudentFragment(), "Add Student")
                    1->setNewFormFragment(AdminAddProjectFragment(), "Add Project")
                    2->setNewFormFragment(AdminAddPollFragment(), "Add Poll")
                }
                dialog.dismiss()
            }
            alertDialog.setNegativeButton("Cancel"){ _, _ -> }
            val customAlertDialog = alertDialog.create()
            customAlertDialog.show()
        }
    }

    private fun setCurrentFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.flFragment,fragment)
            .commit()
    }
    private fun setNewFormFragment(fragment:Fragment, backStash:String){
        parentFragmentManager.beginTransaction()
            .replace(R.id.flFragment,fragment)
            .addToBackStack(backStash)
            .commit()
        //TODO remove the navBar and addButton display
    }
}