package com.example.ktortesting

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}