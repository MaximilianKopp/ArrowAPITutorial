package com.tutorials.arrowapitutorial

import arrow.core.Either
import com.tutorials.arrowapitutorial.controller.HistoricalStockQuoteController
import com.tutorials.arrowapitutorial.model.HistoricalStockQuote
import com.tutorials.arrowapitutorial.service.APIRequestService
import io.mockk.every
import io.mockk.mockk
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class HistoricalStockQuoteControllerTest {

    private lateinit var controller: HistoricalStockQuoteController
    private lateinit var apiRequestService: APIRequestService<HistoricalStockQuote>

    @BeforeEach
    fun setUp() {
        apiRequestService = mockk()
        controller = HistoricalStockQuoteController(apiRequestService)
    }

    @Test
    fun `test getHistorical with successful response`() {
        val symbol = "AAPL"
        val mockResponse = listOf(HistoricalStockQuote(/* your mock data here */))
        val httpUrl = "https://example.com".toHttpUrlOrNull() // Replace with your URL

        every {
            apiRequestService.getData(httpUrl!!, HistoricalStockQuote::class.java)
        } returns Either.Right(mockResponse)

        val result = controller.getHistorical(symbol)
        assertEquals(HttpStatus.OK, result.statusCode)
    }
}
