package de.hilsky.statistics.calculators

import de.hilsky.domain.ChatMessage
import de.hilsky.statistics.calculators.BaseStatisticsCalculator

class MessageLengthCalculator(
        sentMessages: List<ChatMessage>,
        receivedMessages: List<ChatMessage>
): BaseStatisticsCalculator<Double>(sentMessages, receivedMessages) {

    override fun calculateValue(input: List<ChatMessage>): Double {
        return  input.stream()
                .map { message -> message.content.length }
                .mapToInt { x -> x }
                .average()
                .orElse(0.0)
    }

}