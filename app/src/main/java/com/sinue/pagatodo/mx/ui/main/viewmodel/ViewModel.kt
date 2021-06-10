package com.sinue.pagatodo.mx.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sinue.pagatodo.mx.data.repository.MainRepository
import com.sinue.pagatodo.mx.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getTransactions(responseType: MainRepository.ResponseType) = liveData(Dispatchers.IO) {

        emit(Resource.loading(data = null))

        try {
            emit(
                Resource.success(
//                    data = mainRepository.getTransactions().servicioPrueba
                    data = when (responseType) {
                        MainRepository.ResponseType.SUCCESS -> {
                            mainRepository.getTransactionsSuccesfully().servicioPrueba
                        }
                        MainRepository.ResponseType.EMPTY -> {
                            mainRepository.getTransactionsEmpty().servicioPrueba
                        }
                        MainRepository.ResponseType.MALFORMED -> {
                            mainRepository.getTransactionsMalformed().servicioPrueba
                        }
                    }
                )
            )

        } catch (exception: Exception) {
            emit(
                Resource.error(
                    data = null, message = exception.message ?: "Unkonwn error!"
                )
            )
        }
    }
}