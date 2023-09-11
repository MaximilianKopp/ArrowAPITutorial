package com.tutorials.arrowapitutorial.model

import java.math.BigDecimal

data class HistoricalStockQuote(
    var date: String? = null,
    var open: BigDecimal? = null,
    var high: BigDecimal? = null,
    var low: BigDecimal? = null,
    var close: BigDecimal? = null,
    var adjClose: BigDecimal? = null,
    var volume: Long? = null,
    var unadjustedVolume: Long? = null,
    var change: BigDecimal? = null,
    var changePercent: BigDecimal? = null,
    var vwap: BigDecimal? = null,
    var label: String? = null,
    var changeOverTime: BigDecimal? = null,
)
