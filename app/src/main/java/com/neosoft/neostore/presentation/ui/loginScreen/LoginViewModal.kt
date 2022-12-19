package com.neosoft.neostore.presentation.ui.loginScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.neostore.MyApplication
import com.neosoft.neostore.R
import com.neosoft.neostore.domain.loginModal.LoginRequest
import com.neosoft.neostore.domain.usecase.LoginUseCase
import com.neosoft.neostore.presentation.utils.ApiState
import com.neosoft.neostore.presentation.utils.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModal @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel(){

    var response : MutableState<ApiState> = mutableStateOf(ApiState.EMPTY)

    fun validateLoginData(email : String, password : String){

        if (email.isNullOrEmpty()){
            MyApplication.getAppInstance().toast(R.string.email_error)
        }else if (password.isNullOrEmpty()){
            MyApplication.getAppInstance().toast(R.string.password_error)
        }else{
            viewModelScope.launch {
                loginUseCase.userLogin(LoginRequest(email = email, password = password)).let {
                    response.value = ApiState.LOADING
                    Log.e("loginResponse-->",it.toString())
                    if (it.isSuccessful){
                        response.value = ApiState.SUCCESS(it.body().toString(),it.message())
                    }else{
                        response.value = ApiState.FAILURE(it.errorBody().toString())
                    }
                }

            }
        }
    }
}