package com.example.geliculon

data class Chat(
    var id: String = "",
    var name: String = "",
    var users: List<String> = emptyList()
)
