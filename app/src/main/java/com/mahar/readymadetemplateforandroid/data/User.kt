package com.mahar.readymadetemplateforandroid.data

import com.elemental.templateapplication.utils.networkUtils.DataResponse

data class User(
    val avatar: String,
    val email: String,
    val id: String,
    val name: String
):DataResponse<User>{
    override fun retrieveData(): User {
        return User(avatar,email,id,name)
    }

}