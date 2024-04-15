package fr.eseo.e5e.ap.eseojpoll.ui.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_admin_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstFragment = AdminNavFirstFragment()
        val secondFragment = AdminNavSecondFragment()

        var bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.ongoing_poll->setCurrentFragment(firstFragment)
                R.id.other_polls->setCurrentFragment(secondFragment)
            }
            true
        }
        bottomNavigationView.selectedItemId = R.id.ongoing_poll
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.admin_app_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_add_student -> {
                setNewFormFragment(AdminAddStudentFragment(), "Add Student")
                true
            }
            R.id.action_add_project -> {
                setNewFormFragment(AdminAddProjectFragment(), "Add Project")
                true
            }
            R.id.action_add_poll -> {
                setNewFormFragment(AdminAddPollFragment(), "Add Poll")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setCurrentFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment,fragment)
            .commit()
    }
    private fun setNewFormFragment(fragment:Fragment, backStash:String){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment,fragment)
            .addToBackStack(backStash)
            .commit()
    }
}