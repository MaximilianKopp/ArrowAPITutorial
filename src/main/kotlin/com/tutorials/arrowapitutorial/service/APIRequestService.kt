package com.tutorials.arrowapitutorial.service

import arrow.core.Either
import arrow.core.left
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.type.CollectionType
import com.tutorials.arrowapitutorial.errorhandling.ResourceNotFoundException
import com.tutorials.arrowapitutorial.model.HistoricalStockQuote
import com.tutorials.arrowapitutorial.util.APIConstants
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class APIRequestService<M>(
    @Qualifier("objectMapper")
    private val objectMapper: ObjectMapper,
    private val okHttpClient: OkHttpClient
) : HttpService() {

    val log: Logger = LoggerFactory.getLogger(this::class.java.name)

    fun getData(
        httpUrl: HttpUrl,
        clazz: Class<M>
    ): Either<Throwable, List<M>> {
        return try {
            log.info("Fetch ${clazz.simpleName} => ${httpUrl.encodedPathSegments}")
            val response: Response = fetchData(httpUrl, okHttpClient)
            val data = response.body?.string()
            if (!response.isSuccessful) {
                ResourceNotFoundException(APIConstants.NO_DATA_FOUND).left()
            } else {
                Either.catch {
                    val jsonData = determineCollectionType(data, clazz)
                    val listType: CollectionType =
                        objectMapper.typeFactory.constructCollectionType(ArrayList::class.java, clazz)
                    objectMapper.readValue(jsonData, listType)
                }
            }
        } catch (ex: Exception) {
            ex.left()
        }
    }

    private fun determineCollectionType(data: String?, clazz: Class<M>): String {
        return if (clazz.simpleName == HistoricalStockQuote::class.java.simpleName) {
            val jsonNode = objectMapper.readTree(data)
            jsonNode.get("historical").toString()
        } else {
            data ?: throw ResourceNotFoundException(APIConstants.NO_DATA_FOUND)
        }
    }
}
