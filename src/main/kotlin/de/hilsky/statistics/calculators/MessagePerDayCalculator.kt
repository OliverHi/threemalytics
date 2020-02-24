package de.hilsky.statistics.calculators

import de.hilsky.domain.ChatMessage
import java.util.stream.Collectors

class MessagePerDayCalculator(
        sentMessages: List<ChatMessage>,
        receivedMessages: List<ChatMessage>
): BaseStatisticsCalculator<Map<Any?, Long>>(sentMessages, receivedMessages) {

    override fun calculateValue(input: List<ChatMessage>): Map<Any?, Long> {
        return input.stream()
                .map { message -> message.createdAt.toLocalDate() }
                .collect(Collectors.groupingBy({ day -> day}, Collectors.counting()))
                .toList()
                .sortedBy { (_, amount) -> (amount) }
                .reversed()
                .take(10)
                .toMap()
    }

}