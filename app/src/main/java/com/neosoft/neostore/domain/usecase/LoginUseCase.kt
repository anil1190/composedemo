package com.neosoft.neostore.domain.usecase

import com.neosoft.neostore.domain.loginModal.LoginRequest
import com.neosoft.neostore.domain.loginModal.LoginResponse
import com.neosoft.neostore.domain.repository.LoginRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend fun userLogin(loginRequest: LoginRequest) : Response<LoginResponse>{
        return loginRepository.loginUser(loginRequest)
    }
}