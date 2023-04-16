package com.example.factorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView

class MainActivity : AppCompatActivity() {

    private var container: FragmentContainerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        container = findViewById(R.id.container)
        if (savedInstanceState == null) {
            setMainFragmentContainer()
        }
    }

    private fun setMainFragmentContainer() {
        val fragment = MainFragment()
        container?.let {
            supportFragmentManager.beginTransaction()
                .add(it.id, fragment)
                .commit()
        }
    }
}