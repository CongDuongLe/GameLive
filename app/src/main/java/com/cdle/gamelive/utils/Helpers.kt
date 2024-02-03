package com.cdle.gamelive.utils

fun findHtmlTags(input: String): List<String> {
    val regex = Regex("<[^>]+>")
    return regex.findAll(input).map { it.value }.toList()
}

fun extractTextFromHtmlString(html: String): String {
    val regex = Regex(">[^<]+<")
    val matches = regex.findAll(html)
    val extractedText = StringBuilder()

    for (match in matches) {
        val matchValue = match.value
        val cleanedText = matchValue.substring(1, matchValue.length - 1).trim().replace("\\n", "")
        if (cleanedText.isNotEmpty()) {
            extractedText.append("$cleanedText ")
        }
    }

    return extractedText.toString().trim()
}

