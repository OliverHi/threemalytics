package de.hilsky.statistics.calculators

import de.hilsky.domain.ChatMessage
import de.hilsky.domain.MessageType
import de.hilsky.statistics.calculators.BaseStatisticsCalculator

class ImagesAmountCalculator(
        sentMessages: List<ChatMessage>,
        receivedMessages: List<ChatMessage>
): BaseStatisticsCalculator<Long>(sentMessages, receivedMessages) {

    override fun calculateValue(input: List<ChatMessage>): Long {
        return  input.stream()
                .filter { message -> message.type == MessageType.IMAGE }
                .count()
    }
}