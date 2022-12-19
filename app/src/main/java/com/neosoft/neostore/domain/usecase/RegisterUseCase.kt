package com.neosoft.neostore.domain.usecase

import com.neosoft.neostore.domain.registerModal.RegisterRequestModal
import com.neosoft.neostore.domain.registerModal.RegisterResponseModal
import com.neosoft.neostore.domain.repository.RegisterRepository
import retrofit2.Response
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {

    suspend fun userRegistration(registerRequestModal: RegisterRequestModal) : Response<RegisterResponseModal>{
       return registerRepository.registerUser(registerRequestModal)
    }
}