package com.neosoft.neostore.data.remote

import com.neosoft.neostore.domain.loginModal.LoginResponse
import com.neosoft.neostore.domain.registerModal.RegisterResponseModal
import com.neosoft.neostore.domain.registerModal.RegisterRequestModal
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiInterface {

    @FormUrlEncoded
    @POST("users/register")
    suspend fun userRegistration(@Field("first_name") first_name : String, @Field("last_name") last_name : String,
                                 @Field("email") email : String, @Field("password") password : String,
                                 @Field("confirm_password") confirm_password : String, @Field("gender") gender : String,
                                 @Field("phone_no") phone_no : Number): Response<RegisterResponseModal>

    @FormUrlEncoded
    @POST("users/login")
    suspend fun userLogin(@Field("email") email: String, @Field("password") password: String) : Response<LoginResponse>
}




