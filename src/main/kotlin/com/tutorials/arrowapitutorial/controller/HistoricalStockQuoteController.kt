package com.tutorials.arrowapitutorial.controller

import com.tutorials.arrowapitutorial.service.APIRequestService
import com.tutorials.arrowapitutorial.model.HistoricalStockQuote
import com.tutorials.arrowapitutorial.util.APIConstants
import okhttp3.HttpUrl
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HistoricalStockQuoteController(
    private val apiRequestService: APIRequestService<HistoricalStockQuote>
) {

    @GetMapping("/historical", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getHistorical(
        @RequestParam("symbol") symbol: String
    ): ResponseEntity<Any> {
        val httpUrl: HttpUrl = apiRequestService.getHttpUrl()
            .addEncodedPathSegment(APIConstants.VERSION_3)
            .addEncodedPathSegment(APIConstants.HISTORICAL_PRICE_FULL)
            .addEncodedPathSegment(symbol)
            .build()

        return apiRequestService.getData(httpUrl, HistoricalStockQuote::class.java).fold(
            { error ->
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error.message)
            },
            { stockList ->
                if (stockList.isNotEmpty()) {
                    ResponseEntity.ok(mapOf("historicalStockQuote" to stockList))
                } else {
                    ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                }
            }
        )
    }
}
