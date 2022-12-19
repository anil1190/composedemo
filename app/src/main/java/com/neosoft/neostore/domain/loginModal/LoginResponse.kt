package com.neosoft.neostore.domain.loginModal

data class LoginResponse(
    val `data`: Data,
    val message: String,
    val status: Int,
    val user_msg: String
)