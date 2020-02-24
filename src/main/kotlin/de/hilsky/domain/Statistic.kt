package de.hilsky.domain

data class Statistic (
        var messagesSent: Long,
        var messagesReceived: Long,
        var averageMessageLengthSent: Double,
        var averageMessageLengthReceived: Double,
        var imagesSent: Long,
        var imagesReceived: Long,
        var mostUsedWordsSent: Map<String, Long>,
        var mostUsedWordsReceived: Map<String, Long>,
        var messagesPerDaySent: Map<Any?, Long>,
        var messagesPerDayReceived: Map<Any?, Long>,
        var messagesPerDayOfWeekSent: Map<Any?, Long>,
        var messagesPerDayOfWeekReceived: Map<Any?, Long>,
        var messagesPerHourOfDaySent: Map<Any?, Long>,
        var messagesPerHourOfDayReceived: Map<Any?, Long>,
        var mostUsedEmojisSent: Map<String, Long>,
        var mostUsedEmojisReceived: Map<String, Long>
) {
}