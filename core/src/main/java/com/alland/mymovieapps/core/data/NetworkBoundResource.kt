package com.alland.mymovieapps.core.data

import com.alland.mymovieapps.core.data.remote.ApiResponse
import com.alland.mymovieapps.core.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType>{

    private var result: Flow<Result<ResultType>> = flow {
        emit(Result.Loading)
        val dbSource = loadDB().first()
        if(shouldFetch(dbSource)){
            when(val callDataResult = createCall().first()){
                is ApiResponse.Success ->{
                    callDataResult.data?.let { savedCallResult(it) }
                    emitAll(loadDB().map { Result.Success(it) })
                }
                is ApiResponse.Error -> {
                    emit(Result.Error(callDataResult.message))
                }
                is ApiResponse.Empty -> {
                    emitAll(loadDB().map { Result.Success(it) })
                }
            }

        }else{
            emitAll(loadDB().map { Result.Success(it) })
        }
    }.flowOn(Dispatchers.IO)

    abstract fun loadDB(): Flow<ResultType>
    abstract fun shouldFetch(data: ResultType?): Boolean
    abstract fun createCall(): Flow<ApiResponse<RequestType>>
    abstract suspend fun savedCallResult(data: RequestType)

    fun asFlow(): Flow<Result<ResultType>> = result
}