package com.example.schedule

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.schedule.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, MainFragment.newInstance())
            .commit()
    }
}