package com.samirmaciel.techquiz.domain.model

data class Notification(

    var userReceptionUUID: String? = null,
    var userReceptionName: String? = null,
    var userRequisitionUUID: String? = null,
    var userRequisitionName: String? = null,
    var isAccept: Boolean? = false,
    var isVisualized: Boolean? = false
)
