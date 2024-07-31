package com.muvi.muvishared

fun <K, V> Map<K, V>?.deepEquals(other: Map<K, V>?): Boolean {
    if (this === other) return true
    if (this == null || other == null) return false
    if (this.size != other.size) return false

    for ((key, value1) in this) {
        val value2 = other[key]
        if (value1 != value2) {
            return false
        }
    }

    return true
}
