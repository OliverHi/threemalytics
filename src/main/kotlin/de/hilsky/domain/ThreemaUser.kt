package de.hilsky.domain

data class ThreemaUser(
        val id: String,
        val firstName: String = "",
        val lastName: String = "",
        val nickName: String = ""
)