package com.sebqv97.imagesearchapp.feature_image_search.domain.use_case

import com.sebqv97.imagesearchapp.core.util.ErrorTypes
import com.sebqv97.imagesearchapp.core.util.ResultState
import com.sebqv97.imagesearchapp.feature_image_search.data.remote.dto.ResponseDto
import com.sebqv97.imagesearchapp.feature_image_search.domain.model.ImagesResponse
import com.sebqv97.imagesearchapp.feature_image_search.domain.repository.ImagesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.CancellationException
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val imagesRepository: ImagesRepository
) {
    operator fun invoke(searchedWord: String?, pageNumber: Int = 1, pageSize: Int = 10) =
        flow<ResultState<ImagesResponse>> {

            if (searchedWord.isNullOrEmpty()) {
                emit(ResultState.Error(errorType = ErrorTypes.EmptySearchField()))
            } else {
                emit(ResultState.Loading())
                try {
                    coroutineScope {
                        val response = async {
                            imagesRepository
                                .getImagesFromApi(
                                    searchedWord = searchedWord,
                                    pageNumber = pageNumber,
                                    pageSize = pageSize
                                )
                        }.await()
                        if (response.isSuccessful) {
                            if (response.body() != null) {
                                response.body()?.let { emit(ResultState.Success(it.toImagesResponse())) }
                            } else {
                                emit(ResultState.Error(errorType = ErrorTypes.EmptyQuery()))
                            }
                        } else {
                            throw HttpException(response)

                        }
                    }

                } catch (e: IOException) {
                    emit(ResultState.Error(ErrorTypes.InternetConnectionFailed()))

                } catch (e: HttpException) {
                    val errorCode = e.code()
                    emit(ResultState.Error(ErrorTypes.ProblematicHttpRequest(errorCode)))

                } catch (e: CancellationException) {
                    emit(ResultState.Error(ErrorTypes.JobCancellationError(e.cause?.message)))
                }
            }

        }
}