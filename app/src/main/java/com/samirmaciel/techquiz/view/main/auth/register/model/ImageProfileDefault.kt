package com.samirmaciel.techquiz.view.main.auth.register.model

import com.samirmaciel.techquiz.R

data class ImageProfileDefault (
    val resourceID: Int,
    var isSelected: Boolean = false
)

fun ImageProfileDefault.getAllImageProfileDeafult(): List<ImageProfileDefault>{
    return listOf(
        ImageProfileDefault(R.drawable.default_avatar_1),
        ImageProfileDefault(R.drawable.default_avatar_2),
        ImageProfileDefault(R.drawable.default_avatar_3),
        ImageProfileDefault(R.drawable.default_avatar_4),
        ImageProfileDefault(R.drawable.default_avatar_5),
        ImageProfileDefault(R.drawable.default_avatar_6),
        ImageProfileDefault(R.drawable.default_avatar_7),
        ImageProfileDefault(R.drawable.default_avatar_8),
        ImageProfileDefault(R.drawable.default_avatar_9),
        ImageProfileDefault(R.drawable.default_avatar_10),
        ImageProfileDefault(R.drawable.default_avatar_11),
        ImageProfileDefault(R.drawable.default_avatar_12),
        ImageProfileDefault(R.drawable.default_avatar_13),
        ImageProfileDefault(R.drawable.default_avatar_14),
        ImageProfileDefault(R.drawable.default_avatar_15),
    )
}