package de.hilsky.domain

import java.time.LocalDateTime

data class ChatMessage(
        val content: String = "",
        val createdAt: LocalDateTime,
        val type: MessageType = MessageType.UNKNOWN,
        val direction: MessageDirection
)

enum class MessageType(type: String) {
    TEXT("TEXT"),
    IMAGE("IMAGE"),
    LOCATION("LOCATION"),
    AUDIO("AUDIO"),
    VIDEO("VIDEO"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun fromString(msgType: String): MessageType {
            return when(msgType) {
                "TEXT" -> TEXT
                "IMAGE" -> IMAGE
                "LOCATION" -> LOCATION
                "AUDIO" -> AUDIO
                "VIDEO" -> VIDEO
                else -> UNKNOWN
            }
        }
    }
}

enum class MessageDirection(direction: String) {
    OUTGOING("OUTGOING"),
    INCOMING("INCOMING")
}