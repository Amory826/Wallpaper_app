package com.example.wallpaper.model

data class ModelLanguage(val language: String, val countryCode: String) {
    companion object {
        fun getDefaultLanguage() = ModelLanguage(
            language = "English",
            countryCode = "en"
        )
    }
}