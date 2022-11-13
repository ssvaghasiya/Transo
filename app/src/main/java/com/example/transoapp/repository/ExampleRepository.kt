package com.example.transoapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.transoapp.pojo.AgeData
import com.example.transoapp.retrofit.APIInterface
import com.example.transoapp.utils.NetworkResult
import com.example.transoapp.utils.SafeApiCall
import com.example.transoapp.pojo.ExampleData
import retrofit2.Response
import javax.inject.Inject

class ExampleRepository @Inject constructor(private val APIInterface: APIInterface) {

    private val _examples = MutableLiveData<List<ExampleData>?>()
    val examples: LiveData<List<ExampleData>?>
        get() = _examples

    suspend fun getExamples() {
        val result: NetworkResult<Response<List<ExampleData>?>> = SafeApiCall.safeApiCall {
            APIInterface.getExamples()
        }
        when (result) {
            is NetworkResult.Success -> {//Handle success
                if (result.data.isSuccessful && result.data.body() != null) {
                    _examples.postValue(result.data.body())
                }
            }
            is NetworkResult.Error -> { //Handle error
                Log.e("Error", result.message.toString())
                _examples.postValue(null)
            }
        }

    }


    private val _age = MutableLiveData<AgeData?>()
    val age: LiveData<AgeData?>
        get() = _age

    suspend fun callGetAgeApi(name: String) {
        val result: NetworkResult<Response<AgeData?>> = SafeApiCall.safeApiCall {
            APIInterface.getAge(name)
        }
        when (result) {
            is NetworkResult.Success -> {//Handle success
                if (result.data.isSuccessful && result.data.body() != null) {
                    _age.postValue(result.data.body())
                }
            }
            is NetworkResult.Error -> { //Handle error
                Log.e("Error", result.message.toString())
                _age.postValue(null)
            }
        }

    }

}