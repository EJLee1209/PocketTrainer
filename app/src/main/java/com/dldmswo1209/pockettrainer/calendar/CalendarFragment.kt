package com.dldmswo1209.pockettrainer.calendar

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.dldmswo1209.pockettrainer.MainActivity
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.adapter.CalendarFragmentAdapter
import com.dldmswo1209.pockettrainer.adapter.ExerciseAdapter
import com.dldmswo1209.pockettrainer.databinding.FragmentCalendarBinding
import com.dldmswo1209.pockettrainer.datas.ExerciseItem
import com.dldmswo1209.pockettrainer.datas.Routine
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class CalendarFragment : Fragment(R.layout.fragment_calendar) {
    lateinit var binding: FragmentCalendarBinding
    val exerciseAdapter = ExerciseAdapter()
    private lateinit var scheduleDB: DatabaseReference
    private lateinit var routineNameDB: DatabaseReference
    private lateinit var routineDB: DatabaseReference
    private val scheduleList = mutableListOf<ExerciseItem>()
    private val scheduleListener = object: ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val schedule = snapshot.getValue(ExerciseItem::class.java)?:return
            scheduleList.add(schedule)
            exerciseAdapter.submitList(scheduleList)
            exerciseAdapter.notifyDataSetChanged()
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalendarBinding.bind(view)

        scheduleList.clear()

        val user_uid = (activity as MainActivity).user_uid


        binding.dateTextView.text = LocalDate.now().toString()

        val calendarFragmentAdapter = CalendarFragmentAdapter(requireActivity())
        binding.calendarViewPager.apply {
            adapter = calendarFragmentAdapter
            orientation = ViewPager2.ORIENTATION_VERTICAL
            setCurrentItem(calendarFragmentAdapter.firstFragmentPosition, false)
        }
        binding.addButton.setOnClickListener {
            val intent = Intent(requireContext(), AddRoutineActivity::class.java)
            intent.putExtra("date", binding.dateTextView.text.toString())
            startActivity(intent)
        }
        scheduleDB = Firebase.database.reference
            .child("Users")
            .child(user_uid)
            .child("schedules")
            .child(binding.dateTextView.text.toString())

        routineDB = Firebase.database.reference
            .child("Users")
            .child(user_uid)
            .child("routines")

        routineNameDB = Firebase.database.reference
            .child("Users")
            .child(user_uid)
            .child("routineNames")


        binding.dateTextView.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                // 캘린더에서 날짜 선택시(날짜가 변경 됨)
                scheduleDB.removeEventListener(scheduleListener) // 기존에 있던 db 리스너를 제거
                scheduleList.clear() // 기존에 있던 스케줄을 제거
                exerciseAdapter.notifyDataSetChanged() // 어답터에 데이터가 변경되었음을 알림
                scheduleDB = Firebase.database.reference
                    .child("Users")
                    .child(user_uid)
                    .child("schedules")
                    .child(text.toString()) // 변경된 날짜를 db 경로로 지정
                scheduleDB.addChildEventListener(scheduleListener) // 리스너를 다시 붙임

            }
        })

        scheduleDB.addChildEventListener(scheduleListener)

        binding.todayRoutineRecyclerView.adapter = exerciseAdapter
    }



    override fun onDestroy() {
        super.onDestroy()
        scheduleDB.removeEventListener(scheduleListener)
    }
}