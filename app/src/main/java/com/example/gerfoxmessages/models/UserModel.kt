package com.example.gerfoxmessages.models

data class UserModel(
    val id:String = "",
    var username:String = "",
    var bio:String = "",
    var phone:String = "",
    var fullname:String = "",
    var state:String = "",
    var photoURL:String = "empty"
)