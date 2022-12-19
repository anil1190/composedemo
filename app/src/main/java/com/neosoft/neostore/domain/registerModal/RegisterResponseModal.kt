package com.neosoft.neostore.domain.registerModal

import com.neosoft.neostore.domain.registerModal.Data

data class RegisterResponseModal(
    val `data`: Data,
    val message: String,
    val status: Int,
    val user_msg: String
)