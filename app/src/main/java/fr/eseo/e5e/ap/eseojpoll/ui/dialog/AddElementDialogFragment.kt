package fr.eseo.e5e.ap.eseojpoll.ui.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AddElementDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(context)
            builder
                .setTitle("Add an element")
                .setPositiveButton("Add"){ dialog, which ->

                }
                .setNegativeButton("Cancel"){ dialog, which ->

                }
                .setSingleChoiceItems(
                    arrayOf("Student", "Project", "Poll"), 0
                ){ dialog, which ->

                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}