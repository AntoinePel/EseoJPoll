package fr.eseo.e5e.ap.eseojpoll.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.eseo.e5e.ap.eseojpoll.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
    }
}