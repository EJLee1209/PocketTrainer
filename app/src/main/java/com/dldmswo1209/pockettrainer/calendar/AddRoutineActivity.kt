package com.dldmswo1209.pockettrainer.calendar

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.get
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.dldmswo1209.pockettrainer.MainActivity
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.adapter.AddExerciseAdapter
import com.dldmswo1209.pockettrainer.adapter.ExerciseAdapter
import com.dldmswo1209.pockettrainer.adapter.MyRoutineAdapter
import com.dldmswo1209.pockettrainer.databinding.ActivityAddRoutineBinding
import com.dldmswo1209.pockettrainer.datas.*
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.util.function.UnaryOperator

class AddRoutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddRoutineBinding
    private val myRoutine = mutableListOf<ExerciseItem>()
    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var scheduleDB: DatabaseReference
    private lateinit var routineDB: DatabaseReference
    private var myRoutineIsSelect = false
    private var exerciseListIsSelect = false
    private val exerciseList = mutableListOf<RoutineItem>()
    private val myRoutineAdapter = MyRoutineAdapter{ routineItem ->
        if(routineItem.isSelected){
            myRoutineIsSelect = true
            binding.routineSaveButton.isVisible = true
            routineDB.child("${routineItem.routineName}").get()
                .addOnSuccessListener {
                    it.children.forEach { item ->
                        val routine = item.getValue(RoutineItem::class.java)?:return@addOnSuccessListener
                        val newExercise = ExerciseItem(routine.name, routine.target, false)
                        myRoutine.add(newExercise)
                    }
                }
        }else{
            myRoutineIsSelect = false
            routineDB.child("${routineItem.routineName}").get()
                .addOnSuccessListener {
                    it.children.forEach { item ->
                        val routine = item.getValue(RoutineItem::class.java)?:return@addOnSuccessListener
                        val newExercise = ExerciseItem(routine.name, routine.target, false)
                        myRoutine.remove(newExercise)
                    }
                    if(myRoutine.isEmpty()){
                        binding.routineSaveButton.isVisible = false
                    }
                }
        }
    }
    private val routineListener = object: ChildEventListener{
        override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
            val key = snapshot.key ?: return
            exerciseList.add(RoutineItem("","",key,false))
            myRoutineAdapter.submitList(exerciseList)
            myRoutineAdapter.notifyDataSetChanged()

            binding.plzAddRoutineTextView.isGone = true
        }

        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
            if(exerciseList.isNotEmpty()){
                binding.plzAddRoutineTextView.isGone = true
            }else{
                binding.plzAddRoutineTextView.isVisible = true
            }
        }

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRoutineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = intent
        val date = intent.getStringExtra("date").toString()

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)
        val user_uid = sharedPreferences.getString("user_uid", "").toString()
        scheduleDB = Firebase.database.reference
            .child("Users")
            .child(user_uid)
            .child("schedules")
            .child(date)
        routineDB = Firebase.database.reference
            .child("Users")
            .child(user_uid)
            .child("routines")


        routineDB.addChildEventListener(routineListener)

        val exerciseAdapter = AddExerciseAdapter{ exerciseItem ->
            if(exerciseItem.isSelected){
                exerciseListIsSelect = true
                myRoutine.add(exerciseItem)
                binding.routineSaveButton.isVisible = true
            }else{
                exerciseListIsSelect = false
                myRoutine.remove(exerciseItem)
                if(myRoutine.isEmpty()){
                    binding.routineSaveButton.isVisible = false
                }
            }
        }
        binding.exerciseRecyclerView.adapter = exerciseAdapter
        exerciseAdapter.submitList(chestExerciseList)

        binding.myRoutineRecyclerView.adapter = myRoutineAdapter

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
            if((exerciseListIsSelect && !myRoutineIsSelect) || (myRoutineIsSelect && exerciseListIsSelect)) {
                // 운동 목록에서만 선택했거나 운동목록과 내 루틴 모두에서 선택한 경우 팝업을 띄워 루틴명을 설정하고 루틴을 저장함
                val dialog = LayoutInflater.from(this).inflate(R.layout.save_routine_dialog, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(dialog)
                    .setTitle("루틴 추가")

                mBuilder.show()

                val saveButton = dialog.findViewById<AppCompatButton>(R.id.saveButton)
                val editText = dialog.findViewById<EditText>(R.id.routineNameEditText)
                saveButton.setOnClickListener {
                    // 루틴 저장
                    val routineName = editText.text.toString()
                    myRoutine.forEach {
                        val routineItem = RoutineItem(it.name, it.target, routineName, false)
                        scheduleDB.child("${it.name}").setValue(it) // 스케줄 저장
                        routineDB.child(routineName).child("${it.name}")
                            .setValue(routineItem) // 루틴 저장
                    }
                    finish()
                }
            }else{
                // 루틴만 선택한 경우, 팝업을 따로 띄우지 않고 바로 스케줄만 추가하면 됨.
                myRoutine.forEach {
                    scheduleDB.child("${it.name}").setValue(it) // 스케줄 저장
                }
                finish()
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        routineDB.removeEventListener(routineListener)

    }
    companion object{
        const val STATE_SELECT_MY_ROUTINE = 1
        const val STATE_SELECT_NOT_MY_ROTINE = 2
        const val STATE_SELECT_BOTH = 3
    }
}