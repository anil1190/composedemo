package com.neosoft.neostore.data.repositoryImpl

import com.neosoft.neostore.data.remote.ApiInterface
import com.neosoft.neostore.domain.registerModal.RegisterResponseModal
import com.neosoft.neostore.domain.registerModal.RegisterRequestModal
import com.neosoft.neostore.domain.repository.RegisterRepository
import retrofit2.Response
import javax.inject.Inject

class RegisterRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface): RegisterRepository {

    override suspend fun registerUser(user: RegisterRequestModal): Response<RegisterResponseModal> {
        return apiInterface.userRegistration(user.first_name,user.last_name,user.email,user.password,user.confirm_password,user.gender,user.phone_no)
    }
}