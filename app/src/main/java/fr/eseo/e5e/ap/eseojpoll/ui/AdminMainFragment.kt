package fr.eseo.e5e.ap.eseojpoll.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fr.eseo.e5e.ap.eseojpoll.R

class AdminMainFragment : Fragment() {
    private lateinit var fragment: AdminMainFragment
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
}