package de.hilsky.statistics.calculators

import de.hilsky.domain.ChatMessage
import java.util.regex.Pattern
import java.util.stream.Collectors

class MostUsedWordsCalculator(
        sentMessages: List<ChatMessage>,
        receivedMessages: List<ChatMessage>
): BaseStatisticsCalculator<Map<String, Long>>(sentMessages, receivedMessages) {

    private val MIN_WORD_LENGTH = 4

    override fun calculateValue(input: List<ChatMessage>): Map<String, Long> {
        return input.stream()
                .map { message -> message.content }
                .flatMap { message -> Pattern.compile(" ").splitAsStream(message) }
                .filter { message -> message.length >= MIN_WORD_LENGTH }
                .collect(Collectors.groupingBy({ string -> string.toString()}, Collectors.counting()))
                .toList()
                .sortedBy { (_, amount) -> (amount) }
                .reversed()
                .take(10)
                .toMap()
    }

}