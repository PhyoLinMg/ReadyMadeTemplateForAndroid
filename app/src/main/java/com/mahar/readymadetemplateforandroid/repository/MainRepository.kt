package com.mahar.readymadetemplateforandroid.repository

import android.app.Application
import androidx.lifecycle.liveData
import com.elemental.templateapplication.remote.network.services.ApiService
import com.mahar.readymadetemplateforandroid.network.Resource
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.inject.Singleton

class MainRepository(private val apiService: ApiService) {

    fun getUser() =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(Resource.success(data = apiService.getUsers()))
            } catch (exception: SocketTimeoutException) {
                emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!",code=null))
            } catch (httpException: HttpException) {
                emit(
                    Resource.error(
                        data = httpException.response()?.body(),
                        message = httpException.message ?: "Error Occurred!",
                        code = httpException.code()
                    )
                )
            }
        }
}