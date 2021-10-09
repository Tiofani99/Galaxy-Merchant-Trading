package com.tiooooo.gmtrading.util

class InputConverter {
    inner class ParseLine(val type: Type, val pattern: String)

    private val lineParser: Array<ParseLine?> = arrayOfNulls(4)

    fun getLineType(line: String): Type {
        var result = Type.NO_MATCH
        var matched = false
        var i = 0
        while (i < lineParser.size && !matched) {
            if (line.matches(lineParser[i]!!.pattern.toRegex())) {
                matched = true
                result = lineParser[i]!!.type
            }
            i++
        }
        return result
    }

    companion object {
        var patternAssigned = "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$"
        var patternCredits = "^([A-Za-z]+)([A-Za-z\\s]*) is ([0-9]+) ([c|C]redits)$"
        var patternHowMuch = "^how much is (([A-Za-z\\s])+)\\?$"
        var patternHowMany = "^how many [c|C]redits is (([A-Za-z\\s])+)\\?$"
    }

    enum class Type {
        ASSIGNMENT,
        CREDITS,
        HOW_MUCH,
        HOW_MANY,
        NO_MATCH
    }

    init {
        lineParser[0] = ParseLine(Type.ASSIGNMENT, patternAssigned)
        lineParser[1] = ParseLine(Type.CREDITS, patternCredits)
        lineParser[2] = ParseLine(Type.HOW_MUCH, patternHowMuch)
        lineParser[3] = ParseLine(Type.HOW_MANY, patternHowMany)
    }
}