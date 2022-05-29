package com.project.usereditorapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.usereditorapp.databinding.ActivityMainBinding
import com.project.usereditorapp.presentation.details.DetailsFragment
import com.project.usereditorapp.presentation.list.ListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var onBackListener: BackPressListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        navigateToList()
    }

    fun navigateToList() {
        supportFragmentManager.beginTransaction().replace(binding.content.id, ListFragment()).commit()
    }

    fun navigateToDetails() {
        val fragment = DetailsFragment();
        onBackListener = fragment
        supportFragmentManager.beginTransaction().replace(binding.content.id, fragment).addToBackStack("").commit()
    }

    override fun onBackPressed() {
        if(onBackListener?.backPressed() == false) {
            return
        }
        super.onBackPressed()
    }
}