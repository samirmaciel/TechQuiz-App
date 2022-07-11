package com.samirmaciel.techquiz.domain.model

data class User(

    var UUID: String = "",
    var nickName: String = "",
    var fullName: String = "",
    var email: String = "",
    var avatar: String = "",
    var scoreDefault: String = "",
    var rankPosition: String = "",
    var rightQuestions: String = "",
    var wrongQuestions: String = "",
    var win: String = "",
    var loss: String = "",

)
