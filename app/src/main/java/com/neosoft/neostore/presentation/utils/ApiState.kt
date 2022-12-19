package com.neosoft.neostore.presentation.utils

import com.neosoft.neostore.domain.registerModal.RegisterResponseModal
import retrofit2.Response

sealed class ApiState{
    class SUCCESS(val data : String, val msg : String) : ApiState()
    class FAILURE(val message : String) : ApiState()
    object LOADING : ApiState()
    object EMPTY : ApiState()
}
