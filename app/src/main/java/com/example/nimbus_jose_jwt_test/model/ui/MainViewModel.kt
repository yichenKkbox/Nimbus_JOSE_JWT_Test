package com.example.nimbus_jose_jwt_test.model.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nimbus_jose_jwt_test.model.repository.TestRepository
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"
class MainViewModel: ViewModel() {
    private val testRepository = TestRepository()

    fun testRsa(encryptedText: String) {
        viewModelScope.launch {
            testRepository.testRsa(encryptedText).let {
                if (it.isSuccessful) {
                    Log.d(TAG, "testRsa: result: $it")
                } else {
                    Log.d(TAG, "testRsa: error: ${it.errorBody()}")
                }
            }
        }
    }

    fun testEcdh(encryptedText: String) {
        viewModelScope.launch {
            testRepository.testEcdh(encryptedText).let {
                if (it.isSuccessful) {
                    Log.d(TAG, "testRsa: result: $it")
                } else {
                    Log.d(TAG, "testRsa: error: ${it.errorBody()}")
                }
            }
        }
    }
}