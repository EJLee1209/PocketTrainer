package com.dldmswo1209.pockettrainer.calendar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.get
import androidx.core.view.isVisible
import com.dldmswo1209.pockettrainer.MainActivity
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.adapter.AddExerciseAdapter
import com.dldmswo1209.pockettrainer.adapter.ExerciseAdapter
import com.dldmswo1209.pockettrainer.databinding.ActivityAddRoutineBinding
import com.dldmswo1209.pockettrainer.datas.*
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.util.function.UnaryOperator

class AddRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRoutineBinding
    private val myRoutine = mutableListOf<ExerciseItem>()
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val user_uid = sharedPreferences.getString("user_uid", "").toString()
        db = Firebase.database.reference
            .child("Users")
            .child(user_uid)
            .child("Routines")
            .child(LocalDate.now().toString())

        val exerciseAdapter = AddExerciseAdapter{ exerciseItem ->
            if(exerciseItem.isSelected){
                myRoutine.add(exerciseItem)
                binding.routineSaveButton.isVisible = true
            }else{
                myRoutine.remove(exerciseItem)
                if(myRoutine.isEmpty()){
                    binding.routineSaveButton.isVisible = false
                }
            }
        }
        binding.exerciseRecyclerView.adapter = exerciseAdapter
        exerciseAdapter.submitList(chestExerciseList)

        binding.exerciseTab.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position){
                    0 -> {
                        // 가슴
                        exerciseAdapter.submitList(chestExerciseList)
                    }
                    1 -> {
                        // 등
                        exerciseAdapter.submitList(backExerciseList)
                    }
                    2 -> {
                        // 하체
                        exerciseAdapter.submitList(legExerciseList)
                    }
                    3 -> {
                        // 어깨
                        exerciseAdapter.submitList(shoulderExerciseList)
                    }
                    4 -> {
                        // 이두
                        exerciseAdapter.submitList(bicepsExerciseList)
                    }
                    5 -> {
                        // 삼두
                        exerciseAdapter.submitList(tricepsExerciseList)
                    }
                    6 -> {
                        // 승모근
                        exerciseAdapter.submitList(trapeziusExerciseList)
                    }
                    7 -> {
                        // 복근
                        exerciseAdapter.submitList(absExerciseList)
                    }
                    8 -> {
                        // 유산소
                        exerciseAdapter.submitList(aerobicExerciseList)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
        binding.routineSaveButton.setOnClickListener {
            chestExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            backExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            legExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            shoulderExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            bicepsExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            tricepsExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            trapeziusExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            aerobicExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            absExerciseList.replaceAll { p0 ->
                p0.isSelected = false
                p0
            }
            // 팝업창 띄우고 루틴 이름 받을 거임

            myRoutine.forEach {
                db.child("${it.name}").setValue(it)
            }

            finish()
        }

    }
}