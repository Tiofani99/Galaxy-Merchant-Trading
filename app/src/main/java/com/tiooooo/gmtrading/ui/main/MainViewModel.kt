package com.tiooooo.gmtrading.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiooooo.gmtrading.model.Converter
import com.tiooooo.gmtrading.model.ConverterItem
import com.tiooooo.gmtrading.model.Currency
import com.tiooooo.gmtrading.model.Items
import com.tiooooo.gmtrading.util.Convert
import com.tiooooo.gmtrading.util.Convert.ERROR_MESSAGE
import com.tiooooo.gmtrading.util.Convert.NO_IDEA
import com.tiooooo.gmtrading.util.Helper.getValue
import com.tiooooo.gmtrading.util.InputConverter
import com.tiooooo.gmtrading.util.InputConverter.Type.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val inputConverter = InputConverter()
    private val currency = Currency()
    private val items = Items()

    private val _resultText = MutableLiveData<String>()
    val resultText get() = _resultText

    fun manageInput(input: String) = viewModelScope.launch {
        when (inputConverter.getLineType(input)) {
            ASSIGNMENT -> manageCurrency(input.lowercase())
            CREDITS -> manageActualPrice(input.lowercase())
            HOW_MUCH -> managePrice(input.lowercase().split("how much is ").last())
            HOW_MANY -> manageItems(input.lowercase().split("how many credits is ").last())
            else -> _resultText.value = NO_IDEA
        }

    }

    private fun manageCurrency(text: String) = viewModelScope.launch {
        getCurrency(text.split(" "))
    }

    private fun getCurrency(textSplit: List<String>) = viewModelScope.launch {
        val curr = textSplit.first()
        var isValid = true
        when (textSplit.last()) {
            "i" -> currency.i = Converter(curr, Convert.VALUE_I)
            "v" -> currency.v = Converter(curr, Convert.VALUE_V)
            "x" -> currency.x = Converter(curr, Convert.VALUE_X)
            "l" -> currency.l = Converter(curr, Convert.VALUE_L)
            "c" -> currency.c = Converter(curr, Convert.VALUE_C)
            "d" -> currency.d = Converter(curr, Convert.VALUE_D)
            "m" -> currency.m = Converter(curr, Convert.VALUE_M)
            else -> isValid = false
        }

        _resultText.value = if (isValid) "Ok, Saved $curr as ${
            textSplit.last().uppercase()
        }" else "Can't convert currency"
    }

    private fun manageActualPrice(text: String) = viewModelScope.launch {
        val actual = getActualItemPrice(text)
        _resultText.value =
            if (actual != "false") "Ok, saved $actual price" else "Can't saved price"
    }

    private fun getActualItemPrice(text: String): String {
        text.split(" is ").let {
            var item = ""
            var cur = ""
            it[0].split(" ").let { listItem ->
                listItem.forEachIndexed { index, s ->
                    if (index != listItem.size - 1) cur += "$s "
                    else item += s
                }
            }
            val hardPrice = it[1].split(" ").first()
            val actualPrice = hardPrice.toFloat() / getPrice(cur.dropLast(1)).toFloat()
            val converterItem = ConverterItem(item, actualPrice)
            var isValid = true
            when (item.lowercase()) {
                "gold" -> items.gold = converterItem
                "silver" -> items.silver = converterItem
                "iron" -> items.iron = converterItem
                else -> isValid = false
            }
            return if (isValid) item else "false"
        }
    }

    private fun getPrice(text: String): Int {
        var total = 0
        var lastValue = 0
        text.split(" ").forEach {
            lastValue = if (it.getValue() > lastValue && lastValue != 0) {
                total -= lastValue
                total += it.getValue() - lastValue
                it.getValue()
            } else {
                total += it.getValue()
                it.getValue()
            }
        }
        return total
    }

    private fun managePrice(text: String) = viewModelScope.launch {
        val validText = text.replace("?", "")
        val total = getPrice(validText)
        _resultText.value = if (total != -1) "$validText is $total" else ERROR_MESSAGE
    }

    private fun manageItems(text: String) = viewModelScope.launch {
        val validText = text.replace("?", "")
        var item = ""
        var cur = ""
        validText.split(" ").let { listItem ->
            listItem.forEachIndexed { index, s ->
                if (index != listItem.size - 1) cur += "$s "
                else item += s
            }
        }

        Log.d(TAG, "manageItems: $validText")

        item.getPrice().let { itemPrice ->
            val total = if (itemPrice != -1f) {
                getPrice(cur.dropLast(1)).toFloat().let { price ->
                    if (price != -1f) itemPrice * price else price
                }
            } else itemPrice

            _resultText.value =
                if (total != -1f) "$validText is ${total.getValue()} Credits" else ERROR_MESSAGE
        }

    }

    fun String.getValue(): Int {
        val price = when (this) {
            currency.i?.key -> currency.i?.value
            currency.v?.key -> currency.v?.value
            currency.x?.key -> currency.x?.value
            currency.l?.key -> currency.l?.value
            currency.c?.key -> currency.c?.value
            currency.d?.key -> currency.d?.value
            currency.m?.key -> currency.m?.value
            else -> 0
        }
        return price ?: 0
    }

    private fun String.getPrice(): Float {
        val price = when (this) {
            items.gold?.key -> items.gold?.value
            items.silver?.key -> items.silver?.value
            items.iron?.key -> items.iron?.value
            else -> 0f
        }
        return price ?: 0f
    }


    //Untuk kebutuhan testing
    fun manageInputTesting(input: String): String {
        return when (inputConverter.getLineType(input)) {
            ASSIGNMENT -> manageCurrencyTesting(input.lowercase())
            CREDITS -> manageActualPriceTesting(input.lowercase())
            HOW_MUCH -> managePriceTesting(input.lowercase().split("how much is ").last())
            HOW_MANY -> manageItemsTesting(input.lowercase().split("how many credits is ").last())
            else -> NO_IDEA
        }
    }

    private fun manageCurrencyTesting(text: String): String {
        return getCurrencyTesting(text.split(" "))
    }

    private fun getCurrencyTesting(textSplit: List<String>): String {
        val curr = textSplit.first()
        var isValid = true
        when (textSplit.last()) {
            "i" -> currency.i = Converter(curr, Convert.VALUE_I)
            "v" -> currency.v = Converter(curr, Convert.VALUE_V)
            "x" -> currency.x = Converter(curr, Convert.VALUE_X)
            "l" -> currency.l = Converter(curr, Convert.VALUE_L)
            "c" -> currency.c = Converter(curr, Convert.VALUE_C)
            "d" -> currency.d = Converter(curr, Convert.VALUE_D)
            "m" -> currency.m = Converter(curr, Convert.VALUE_M)
            else -> isValid = false
        }

        return if (isValid) "Ok, Saved $curr as ${
            textSplit.last().uppercase()
        }" else "Can't convert currency"
    }

    private fun manageActualPriceTesting(text: String):String {
        val actual = getActualItemPrice(text)
        return if (actual != "false") "Ok, saved $actual price" else "Can't saved price"
    }

    private fun managePriceTesting(text: String):String {
        val validText = text.replace("?", "")
        val total = getPrice(validText)
        return if (total != -1) "${validText}is $total" else ERROR_MESSAGE
    }

    private fun manageItemsTesting(text: String):String {
        val validText = text.replace("?", "")
        var item = ""
        var cur = ""
        validText.split(" ").let { listItem ->
            listItem.forEachIndexed { index, s ->
                if (index != listItem.size - 1) cur += "$s "
                else item += s
            }
        }
        item = validText.split(" ").let {
            it.dropLast(1).let { newItem ->
                newItem[newItem.lastIndex]
            }
        }.toString()


        item.getPrice().let { itemPrice ->
            val total = if (itemPrice != -1f) {
                getPrice(cur.dropLast(1)).toFloat().let { price ->
                    if (price != -1f) itemPrice * price else price
                }
            } else itemPrice

            return if (total != -1f) "${validText}is ${total.getValue()} Credits" else ERROR_MESSAGE
        }

    }

    companion object {
        const val TAG = "MainViewModel"
    }


}