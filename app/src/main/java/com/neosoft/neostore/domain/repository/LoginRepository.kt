package com.neosoft.neostore.domain.repository

import com.neosoft.neostore.domain.loginModal.LoginRequest
import com.neosoft.neostore.domain.loginModal.LoginResponse
import retrofit2.Response

interface LoginRepository {

    suspend fun loginUser(login: LoginRequest) : Response<LoginResponse>
}