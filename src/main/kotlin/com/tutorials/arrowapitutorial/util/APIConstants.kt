package com.tutorials.arrowapitutorial.util

class APIConstants {

    companion object {
        //API
        const val SCHEME = "https"
        const val BASE_URL = "financialmodelingprep.com"
        const val GATEWAY = "api"
        const val VERSION_3 = "v3"
        const val API_KEY = "ADD your API key here"
        const val PARAM_API_KEY = "apikey"

        //Historical price
        const val HISTORICAL_PRICE_FULL = "historical-price-full"

        //ExceptionHandling
        const val NO_DATA_FOUND =
            "No data found - please ensure to provide a correct path and valid request parameters "
    }
}