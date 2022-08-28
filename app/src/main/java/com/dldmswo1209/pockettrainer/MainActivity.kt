package com.dldmswo1209.pockettrainer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dldmswo1209.pockettrainer.calendar.CalendarFragment
import com.dldmswo1209.pockettrainer.databinding.ActivityMainBinding
import com.dldmswo1209.pockettrainer.food.FoodFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val homeFragment = HomeFragment()
    val calendarFragment = CalendarFragment()
    val foodFragment = FoodFragment()
    lateinit var user_uid: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        user_uid = sharedPreferences.getString("user_uid","").toString()

        replaceFragment(homeFragment)
        binding.bottomNavigation.selectedItemId = R.id.home

        binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(homeFragment)
                R.id.calendar -> replaceFragment(calendarFragment)
                R.id.food -> replaceFragment(foodFragment)
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrameLayout, fragment)
            .commit()
    }
}