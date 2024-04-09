package fr.eseo.e5e.ap.eseojpoll.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fr.eseo.e5e.ap.eseojpoll.R
import fr.eseo.e5e.ap.eseojpoll.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        binding.btnLogVisitor.setOnClickListener {
            val fragmentManager = parentFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            val visitorPollFragment: VisitorPollFragment = VisitorPollFragment.newInstance()
            fragmentTransaction.replace(R.id.container, visitorPollFragment)
            fragmentTransaction.addToBackStack("VisitorPoll")
            fragmentTransaction.commit()
        }

        binding.btnLogAdmin.setOnClickListener {
            val dialog = PasswordAdminFragment()
            dialog.show(parentFragmentManager, "PasswordAdminFragment")
        }
    }
}