package com.muvi.shared.data.di

import com.muvi.shared.data.util.Rout
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val dataModule = module {
    single {
        HttpClient() {
            install(Logging) {
//                logger = KtorLogger()
                level = LogLevel.ALL
            }
            install(DefaultRequest) {
                url(Rout.BASE_URL)
                header("X-Device-Platform", "sharedsdk")
                    header("X-App-Version", "1.0.0")
                    header("Content-Type", "application/json")
                    header("Accept-Language", "en")
                    header("X-Device-Id", "deviceId")
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    explicitNulls = false
                    coerceInputValues = true
                    isLenient = true
                })
            }
        }
    }
}