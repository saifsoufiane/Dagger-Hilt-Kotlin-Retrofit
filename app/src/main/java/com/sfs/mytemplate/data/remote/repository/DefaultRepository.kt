package com.sfs.mytemplate.data.remote.repository

import com.sfs.mytemplate.data.remote.api.CurrencyApi
import com.sfs.mytemplate.data.remote.response.CurrencyResponse
import com.sfs.mytemplate.utils.Resource
import javax.inject.Inject


class DefaultRepository @Inject constructor(
    private val api: CurrencyApi
) : MainRepository {
    override suspend fun getRates(base: String): Resource<CurrencyResponse> {

        return try {
            val response = api.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null)
                Resource.Success(result)
            else

                Resource.Error(response.message())

        } catch (e: Exception) {
            Resource.Error(e.message ?: "error was found")
        }
    }
}