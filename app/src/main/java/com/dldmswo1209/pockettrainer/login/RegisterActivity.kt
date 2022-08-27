package com.dldmswo1209.pockettrainer.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.dldmswo1209.pockettrainer.R
import com.dldmswo1209.pockettrainer.databinding.ActivityRegisterBinding
import com.dldmswo1209.pockettrainer.datas.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        db = Firebase.database.reference.child("Users")

        binding.registerButton.setOnClickListener {
            val id = binding.inputIdEditTextView.text.toString()
            val pw = binding.inputPwEditTextView.text.toString()
            val nickname = binding.inputNickNameEditTextView.text.toString()
            if(id == "" || pw =="" || nickname == ""){
                Toast.makeText(this, "모두 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            register(id, pw, nickname)
        }
    }
    private fun register(id: String, pw: String, nickname: String){
        auth.createUserWithEmailAndPassword(id, pw)
            .addOnSuccessListener {
                Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                db.child(it.user!!.uid).setValue(UserInfo(it.user!!.uid, id, pw, nickname))
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "회원가입 실패, 올바르지 않은 이메일 입니다.", Toast.LENGTH_SHORT).show()
                Log.d("testt", it.message.toString())
            }
    }
}