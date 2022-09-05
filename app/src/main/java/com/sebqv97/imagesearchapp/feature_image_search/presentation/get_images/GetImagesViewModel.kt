package com.sebqv97.imagesearchapp.feature_image_search.presentation.get_images

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebqv97.imagesearchapp.core.util.ResultState
import com.sebqv97.imagesearchapp.feature_image_search.domain.use_case.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetImagesViewModel @Inject constructor(
    private val getImagesUseCase: GetImagesUseCase
): ViewModel() {


    fun getImages(searchedWord:String?){

        viewModelScope.launch {
            getImagesUseCase(searchedWord).collect{
                when(it){
                    is ResultState.Success -> {
                        it.data?.toString()?.let { it1 -> Log.d("Response", it1) }
                    }
                    is ResultState.Error -> {
                        it.errorType?.toString()?.let { it1 -> Log.d("Response", it1) }

                    }
                    is ResultState.Loading -> {
                        Log.d("Response","Loading")

                    }
                }
            }

        }
    }
}