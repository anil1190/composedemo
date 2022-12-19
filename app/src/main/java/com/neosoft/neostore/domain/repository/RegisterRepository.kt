package com.neosoft.neostore.domain.repository

import com.neosoft.neostore.domain.registerModal.RegisterResponseModal
import com.neosoft.neostore.domain.registerModal.RegisterRequestModal
import retrofit2.Response

interface RegisterRepository {

    suspend fun registerUser(user : RegisterRequestModal) : Response<RegisterResponseModal>
}