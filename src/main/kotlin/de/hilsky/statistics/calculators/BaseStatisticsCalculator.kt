package de.hilsky.statistics.calculators

import de.hilsky.domain.ChatMessage

abstract class BaseStatisticsCalculator<T>(
        private val sentMessages: List<ChatMessage>,
        private val receivedMessages: List<ChatMessage>
) {

    abstract fun calculateValue(input: List<ChatMessage>) : T

    fun calculateReceivedValue() : T {
        return calculateValue(receivedMessages)
    }

    fun calculateSentValue() : T {
        return calculateValue(sentMessages)
    }
}