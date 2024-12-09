package com.example.demoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.demoapp.data.ApiError
import com.example.demoapp.data.ApiLoading
import com.example.demoapp.data.ApiResult
import com.example.demoapp.data.ApiSuccess
import com.example.demoapp.data.model.MoviesResponse
import com.example.demoapp.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel @Inject constructor( private val moviesRepository : MovieRepository):AndroidViewModel(Application()) {

    private var liveDataList:MutableLiveData<ApiResult<MoviesResponse?>> = MutableLiveData()

    fun getMoviesLiveData():MutableLiveData<ApiResult<MoviesResponse?>>{
        return liveDataList
    }

    fun makeApiCall(vmNum:Int){
        if(vmNum<0){
            liveDataList.postValue(ApiError("ViewModel parameter not passed correctly"))
        }
        else if (vmNum == 0) {
            viewModelScope.launch {
                liveDataList.postValue(ApiLoading)
                val moviesResult = moviesRepository.getTopRatedMovies()
                if (moviesResult.isSuccessful) {
                    liveDataList.postValue(ApiSuccess(moviesResult.body()))
                } else {
                    liveDataList.postValue(ApiError(moviesResult.message()))
                }
            }
        }
        else if(vmNum == 1){
            viewModelScope.launch {
                liveDataList.postValue(ApiLoading)
                val moviesResult = moviesRepository.getBeforeSearchMovies()
                if (moviesResult.isSuccessful) {
                    liveDataList.postValue(ApiSuccess(moviesResult.body()))
                } else {
                    liveDataList.postValue(ApiError(moviesResult.message()))
                }
            }
        }

    }
}