package com.tiooooo.gmtrading.model

data class Currency(
    var i: Converter? = null,
    var v: Converter? = null,
    var x: Converter? = null,
    var l: Converter? = null,
    var c: Converter? = null,
    var d: Converter? = null,
    var m: Converter? = null,
)

data class Converter(
    var key: String,
    var value: Int,
)

data class Items(
    var gold: ConverterItem? = null,
    var silver: ConverterItem? = null,
    var iron: ConverterItem? = null,
)

data class ConverterItem(
    var key: String,
    var value: Float,
)