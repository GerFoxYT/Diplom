package com.example.gerfoxmessage.ui.fragments

import androidx.fragment.app.Fragment
import com.example.gerfoxmessage.MainActivity
import com.example.gerfoxmessage.R
import com.example.gerfoxmessage.activites.RegisterActivity
import com.example.gerfoxmessage.utilits.AUTH
import com.example.gerfoxmessage.utilits.replaceActivity
import com.example.gerfoxmessage.utilits.replaceFragment
import com.example.gerfoxmessage.utilits.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.fragment_enter_code.*
import kotlinx.android.synthetic.main.fragment_enter_phone_num.*
import java.util.concurrent.TimeUnit


class EnterPhoneNum : Fragment(R.layout.fragment_enter_phone_num) {

    private lateinit var mPhoneNumber: String
    private lateinit var mCallBack: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onStart() {
        super.onStart()
        register_btn_next.setOnClickListener { sendCode() }
        mCallBack = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                AUTH.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        showToast("Вход успшено выполнен")
                        (activity as RegisterActivity).replaceActivity(MainActivity())
                    } else showToast(task.exception?.message.toString())
                }
            }

            override fun onVerificationFailed(p0: FirebaseException) {
                showToast(p0.message.toString())
            }

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                replaceFragment(EnterCode(mPhoneNumber, id))
            }
        }
    }

    private fun sendCode() {
        if (register_input_number.text.toString().isEmpty()) {
            showToast(getString(R.string.register_toast_enter_phone))

        } else {
            authUser()
        }

    }

    private fun authUser() {
        mPhoneNumber = register_input_number.text.toString()
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            mPhoneNumber, 60, TimeUnit.SECONDS, activity as RegisterActivity, mCallBack
        )
    }

}
