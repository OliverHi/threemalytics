package de.hilsky.statistics.calculators

import de.hilsky.domain.ChatMessage
import java.util.stream.Collectors

class MessagePerHourOfDayCalculator(
        sentMessages: List<ChatMessage>,
        receivedMessages: List<ChatMessage>
): BaseStatisticsCalculator<Map<Any?, Long>>(sentMessages, receivedMessages) {

    override fun calculateValue(input: List<ChatMessage>): Map<Any?, Long> {
        return input.stream()
                .map { message -> message.createdAt.hour }
                .collect(Collectors.groupingBy({hour -> hour}, Collectors.counting()))
                .toMap()
    }

}