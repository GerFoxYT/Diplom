package com.example.gerfoxmessage.ui.fragments

import androidx.fragment.app.Fragment
import com.example.gerfoxmessage.MainActivity
import com.example.gerfoxmessage.R
import com.example.gerfoxmessage.activites.RegisterActivity
import com.example.gerfoxmessage.utilits.*
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*

class EnterCode(val PhoneNumber: String, val id: String) :
    Fragment(R.layout.fragment_enter_code) {


    override fun onStart() {
        super.onStart()
        (activity as RegisterActivity).title = PhoneNumber
        register_input_code.addTextChangedListener(appTextWacher {
            val string = register_input_code.text.toString()
            if (string.length == 6) {
                enterCode()
            }
        })

    }

    private fun enterCode() {
        val code = register_input_code.text.toString()
        val credential = PhoneAuthProvider.getCredential(id, code)
        AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val uid = AUTH.currentUser?.uid.toString()
                val dateMap = mutableMapOf<String,Any>()
                dateMap[CHILD_ID] = uid
                dateMap[CHILD_PHONE] = PhoneNumber
                dateMap[CHILD_USERNAME] = uid

                REF_DATABASE_ROOT.child(NODE_USERS).child(uid).updateChildren(dateMap)
                    .addOnCompleteListener { task2 ->
                        if (task2.isSuccessful){
                            showToast("Добро пожаловать!")
                            (activity as RegisterActivity).replaceActivity(MainActivity())
                        }else showToast(task2.exception?.message.toString())
                    }
                showToast("Вход успшено выполнен")
                (activity as RegisterActivity).replaceActivity(MainActivity())
            } else showToast(task.exception?.message.toString())
        }
    }
}


