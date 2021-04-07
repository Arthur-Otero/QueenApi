package com.example.queenapi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.queenapi.model.Queen
import com.example.queenapi.repositoryApi.QueensRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException

class QueenViewModel():ViewModel() {
    val listQueen: MutableLiveData<List<Queen>> by lazy { MutableLiveData<List<Queen>>() }
    val loading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    val errorMessage: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    private val repository = QueensRepository()

    init {
        queensListApi()
    }

    private fun queensListApi() = CoroutineScope(IO).launch {
        try {
            loading.postValue(true)
            repository.getAllQueens().let { queens->
                listQueen.postValue(queens)
            }
        }catch (error:Throwable){
            ErrorApi(error, errorMessage)
        }finally {
            loading.postValue(false)
        }
    }

    private fun ErrorApi(error: Throwable, errorMessage: MutableLiveData<String>){
        when (error){
            is HttpException -> errorMessage.postValue("Erro de conexão código: ${error.message}")
            is UnknownHostException -> errorMessage.postValue("Verifique sua conexão")
        }
    }
}
