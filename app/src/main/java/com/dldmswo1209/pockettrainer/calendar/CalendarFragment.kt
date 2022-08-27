package com.dldmswo1209.pockettrainer.calendar

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.adapter.CalendarFragmentAdapter
import com.dldmswo1209.pockettrainer.adapter.ExerciseAdapter
import com.dldmswo1209.pockettrainer.databinding.FragmentCalendarBinding

class CalendarFragment : Fragment(R.layout.fragment_calendar) {
    lateinit var binding: FragmentCalendarBinding
    val exerciseAdapter = ExerciseAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalendarBinding.bind(view)

        val calendarFragmentAdapter = CalendarFragmentAdapter(requireActivity())
        binding.calendarViewPager.apply {
            adapter = calendarFragmentAdapter
            orientation = ViewPager2.ORIENTATION_VERTICAL
            setCurrentItem(calendarFragmentAdapter.firstFragmentPosition, false)
        }
        binding.addButton.setOnClickListener {
            startActivity(Intent(requireContext(), AddRoutineActivity::class.java))
        }


        binding.todayRoutineRecyclerView.adapter = exerciseAdapter
    }

}