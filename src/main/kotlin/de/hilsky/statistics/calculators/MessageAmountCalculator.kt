package de.hilsky.statistics.calculators

import de.hilsky.domain.ChatMessage
import de.hilsky.statistics.calculators.BaseStatisticsCalculator

class MessageAmountCalculator(
        sentMessages: List<ChatMessage>,
        receivedMessages: List<ChatMessage>
): BaseStatisticsCalculator<Long>(sentMessages, receivedMessages) {

    override fun calculateValue(input: List<ChatMessage>): Long {
        return input.stream().count()
    }

}