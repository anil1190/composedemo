package com.neosoft.neostore.data.repositoryImpl

import com.neosoft.neostore.data.remote.ApiInterface
import com.neosoft.neostore.domain.loginModal.LoginRequest
import com.neosoft.neostore.domain.loginModal.LoginResponse
import com.neosoft.neostore.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) : LoginRepository {

    override suspend fun loginUser(login: LoginRequest): Response<LoginResponse> {
       return apiInterface.userLogin(login.email,login.password)
    }
}