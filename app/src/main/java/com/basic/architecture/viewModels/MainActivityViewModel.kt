package com.basic.architecture.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.basic.architecture.Coroutines
import com.basic.architecture.data.network.repositories.MainActivityRepository
import com.basic.architecture.data.network.responses.BreedResponse
import kotlinx.coroutines.Job

class MainActivityViewModel(
    private val repository: MainActivityRepository
) : ViewModel() {

    private lateinit var job: Job

    private val _getBreedsResponse = MutableLiveData<BreedResponse?>()
    val getBreedsResponse: LiveData<BreedResponse?> = _getBreedsResponse

    fun getBreeds(pageCount: String, listLimit: String) {
        job = Coroutines.ioThenMain(
            { repository.getBreedsCall(pageCount, listLimit) },
            { _getBreedsResponse.value = it }
        )
    }
}