package com.example.gerfoxmessages.ui.screens.register

import com.example.gerfoxmessages.dataBase.USER
import com.example.gerfoxmessages.dataBase.setNameToDataBase
import com.example.gerfoxmessages.ui.screens.base.BaseChange
import com.example.gerfoxmessages.utilits.showToast
import gerfoxmessages.R
import kotlinx.android.synthetic.main.fragment_change_name.*

class ChangeName : BaseChange(R.layout.fragment_change_name) {


    override fun onResume() {
        super.onResume()
        initFullnameList()
    }

    private fun initFullnameList() {
        val fullnameList = USER.fullname.split(" ")
        if (fullnameList.size > 1) {
            s_input_name.setText(fullnameList[0])
            s_input_surname.setText(fullnameList[1])
        } else s_input_name.setText(fullnameList[0])
    }

    override fun change() {
        val name = s_input_name.text.toString()
        val surname = s_input_surname.text.toString()
        if (name.isEmpty()) {
            showToast("Имя не может быть пустным")
        } else {
            val fullname = "$name $surname"
            setNameToDataBase(fullname)

        }
    }


}