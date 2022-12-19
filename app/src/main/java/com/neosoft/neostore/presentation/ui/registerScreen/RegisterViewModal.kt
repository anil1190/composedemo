package com.neosoft.neostore.presentation.ui.registerScreen

import android.annotation.SuppressLint
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neosoft.neostore.MyApplication
import com.neosoft.neostore.R
import com.neosoft.neostore.domain.registerModal.RegisterRequestModal
import com.neosoft.neostore.domain.registerModal.RegisterResponseModal
import com.neosoft.neostore.domain.usecase.RegisterUseCase
import com.neosoft.neostore.presentation.utils.ApiState
import com.neosoft.neostore.presentation.utils.Gender
import com.neosoft.neostore.presentation.utils.toast
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RegisterViewModal @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel(){

    var response : MutableState<ApiState> = mutableStateOf(ApiState.EMPTY)

    @SuppressLint("SuspiciousIndentation")
    fun validateUserDetail(f_name : String, l_name:String, email : String, password : String, confirm_pass : String,gender : String,phone : String){

       if (f_name.isNullOrEmpty()){
            MyApplication.getAppInstance().toast(R.string.f_name_error)
       }else if (l_name.isNullOrEmpty()){
           MyApplication.getAppInstance().toast(R.string.l_name_error)
       }else if (email.isNullOrEmpty()){
           MyApplication.getAppInstance().toast(R.string.email_error)
       }else if (password.isNullOrEmpty() || confirm_pass.isNullOrEmpty()){
           MyApplication.getAppInstance().toast(R.string.password_error)
       }else if (!isPasswordMatched(password,confirm_pass)){
           MyApplication.getAppInstance().toast(R.string.password_matching_error)
       }else if (phone.isNullOrEmpty()){
           MyApplication.getAppInstance().toast(R.string.phone_error)
       }else if (gender.isNullOrEmpty()){
           MyApplication.getAppInstance().toast(R.string.gender_error)
       }else{
          var m_gender : String? = null
            if (gender.equals(Gender.male)){
                m_gender = "M"
            }else{
                m_gender = "F"
            }

           viewModelScope.launch {
               try {
                   val phoneNumber = phone.toLong()
                   registerUseCase.userRegistration(RegisterRequestModal(f_name,l_name,email,password,confirm_pass,m_gender,phoneNumber)).let {
                       response.value = ApiState.LOADING
                       if (it.isSuccessful){
                           response.value = ApiState.SUCCESS(it.body().toString(),it.message())
                       }else{
                           response.value = ApiState.FAILURE(it.errorBody().toString())
                       }
                   }
               }catch (e:NumberFormatException){
                   Log.e("NumberFormateException",e.message.toString())
               }


           }

       }


    }

    fun isPasswordMatched(password: String,confirm_pass: String):Boolean{
        var matched : Boolean = false
        matched = password.equals(confirm_pass)

        return matched
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}