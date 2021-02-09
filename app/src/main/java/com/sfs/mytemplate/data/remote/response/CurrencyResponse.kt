package com.sfs.mytemplate.data.remote.response

data class CurrencyResponse(
    val base: String,
    val date: String,
    val rates: Rates
)