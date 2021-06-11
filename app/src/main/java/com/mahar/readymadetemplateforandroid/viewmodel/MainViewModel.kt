package com.mahar.readymadetemplateforandroid.viewmodel


import androidx.lifecycle.ViewModel
import com.mahar.readymadetemplateforandroid.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject constructor(private val mainRepository: MainRepository):ViewModel() {

    fun getUsersLiveData()= mainRepository.getUser()

}