package com.samirmaciel.techquiz.domain.model

import android.graphics.drawable.Drawable
import com.samirmaciel.techquiz.R

data class UserForm (

    val nickName: String,
    val fullName: String,
    val email: String,
    val password: String,
    var avatar: Drawable? = null
)
