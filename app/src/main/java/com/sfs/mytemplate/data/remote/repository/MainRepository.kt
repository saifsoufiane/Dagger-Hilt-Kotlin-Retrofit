package com.sfs.mytemplate.data.remote.repository

import com.sfs.mytemplate.data.remote.response.CurrencyResponse
import com.sfs.mytemplate.utils.Resource

interface MainRepository {
    suspend fun getRates(base: String): Resource<CurrencyResponse>

}