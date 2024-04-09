package fr.eseo.e5e.ap.eseojpoll.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import fr.eseo.e5e.ap.eseojpoll.R

class WelcomePasswordFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?) : Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val dialogView = inflater.inflate(R.layout.fragment_password_admin, null)
            val passwordInput = dialogView.findViewById<EditText>(R.id.text_password_admin)

            builder.apply {
                setView(dialogView)
                setPositiveButton("OK") { dialog, id ->
                        val enteredPassword = passwordInput.text.toString()
                        val encryptedPassword = encryptPassword(enteredPassword)
                        if(isCorrectPassword(encryptedPassword)){
                            val fragmentManager = parentFragmentManager
                            val fragmentTransaction = fragmentManager.beginTransaction()
                            val adminMainFragment: AdminMainFragment =
                                AdminMainFragment.newInstance()
                            fragmentTransaction.replace(R.id.container, adminMainFragment)
                            fragmentTransaction.addToBackStack("Admin Main")
                            fragmentTransaction.commit()
                        }
                    }
                setNegativeButton("Cancel") { dialog, id ->
                        dialog.cancel()
                    }
            }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun encryptPassword(password: String): String {
        return password
    }
    private fun isCorrectPassword(password: String): Boolean {
        val correctPassword = "Password123"
        return password == correctPassword
    }
}