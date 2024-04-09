package fr.eseo.e5e.ap.eseojpoll.ui

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

        val firstFragment=AdminNavFirstFragment()
        val secondFragment=AdminNavSecondFragment()

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
    }

    private fun setCurrentFragment(fragment:Fragment){
        parentFragmentManager.beginTransaction()
            .replace(R.id.flFragment,fragment)
            .commit()
    }
}