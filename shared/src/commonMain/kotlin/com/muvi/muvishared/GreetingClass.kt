package com.muvi.muvishared

import kotlin.js.ExperimentalJsExport
import kotlin.js.JsExport

@ExperimentalJsExport
@JsExport
class GreetingClass {
    fun greeting(): String {
        return "Hello World!"
    }
}

fun greeting(): String {
    return "Hello World!"
}