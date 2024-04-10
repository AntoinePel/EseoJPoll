package fr.eseo.e5e.ap.eseojpoll.ui.visitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.eseo.e5e.ap.eseojpoll.R

class VisitorPollFragment : Fragment() {
    private lateinit var fragment: VisitorPollFragment
    companion object {
        fun newInstance(): VisitorPollFragment {
            return VisitorPollFragment()
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var itemClickListener: OnItemClickListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_visitor_poll, container, false)
    }
}