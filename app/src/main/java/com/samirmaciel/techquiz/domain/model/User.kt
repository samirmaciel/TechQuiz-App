package com.samirmaciel.techquiz.domain.model

data class User(

    val UUID: String,
    val nickName: String,
    val fullName: String,
    val email: String? = null,
    var avatar: String? = null ,
    var scoreDefault: String? = null,
    var rankPosition: String? = null,
    var rightQuestions: String? = null,
    var wrongQuestions: String? = null,
    var win: String? = null,
    var loss: String? = null

)
