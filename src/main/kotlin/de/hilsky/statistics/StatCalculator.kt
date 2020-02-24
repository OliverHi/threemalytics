package de.hilsky.statistics

import de.hilsky.domain.*
import de.hilsky.statistics.calculators.*
import kotlin.streams.toList

class StatCalculator (
        private val messages: List<ChatMessage>,
        private val targetUser: ThreemaUser?) {

    private val messageAmountCalculator: MessageAmountCalculator
    private val messageLengthCalculator: MessageLengthCalculator
    private val imagesAmountCalculator: ImagesAmountCalculator
    private val mostUsedWordsCalculator: MostUsedWordsCalculator
    private val messagePerDayCalculator: MessagePerDayCalculator
    private val messagePerDayOfWeekCalculator: MessagePerDayOfWeekCalculator
    private val messagePerHourOfDayCalculator: MessagePerHourOfDayCalculator
    private val emojiAmountCalculator: EmojiAmountCalculator

    private val userStatistic: Statistic

    init {
        messageAmountCalculator = MessageAmountCalculator(getSentMessages(), getReceivedMessages())
        messageLengthCalculator = MessageLengthCalculator(getSentTextMessages(), getReceivedTextMessages())
        imagesAmountCalculator = ImagesAmountCalculator(getSentMessages(), getReceivedMessages())
        mostUsedWordsCalculator = MostUsedWordsCalculator(getSentTextMessages(), getReceivedTextMessages())
        messagePerDayCalculator = MessagePerDayCalculator(getSentMessages(), getReceivedMessages())
        messagePerDayOfWeekCalculator = MessagePerDayOfWeekCalculator(getSentMessages(), getReceivedMessages())
        messagePerHourOfDayCalculator = MessagePerHourOfDayCalculator(getSentMessages(), getReceivedMessages())
        emojiAmountCalculator = EmojiAmountCalculator(getSentTextMessages(), getReceivedTextMessages())

        userStatistic = Statistic(
                messageAmountCalculator.calculateSentValue(), messageAmountCalculator.calculateReceivedValue(),
                messageLengthCalculator.calculateSentValue(), messageLengthCalculator.calculateReceivedValue(),
                imagesAmountCalculator.calculateSentValue(), imagesAmountCalculator.calculateReceivedValue(),
                mostUsedWordsCalculator.calculateSentValue(), mostUsedWordsCalculator.calculateReceivedValue(),
                messagePerDayCalculator.calculateSentValue(), messagePerDayCalculator.calculateReceivedValue(),
                messagePerDayOfWeekCalculator.calculateSentValue(), messagePerDayOfWeekCalculator.calculateReceivedValue(),
                messagePerHourOfDayCalculator.calculateSentValue(), messagePerHourOfDayCalculator.calculateReceivedValue(),
                emojiAmountCalculator.calculateSentValue(), emojiAmountCalculator.calculateReceivedValue()
        )
    }

    private fun getSentMessages() = messages.stream()
            .filter { message -> message.direction == MessageDirection.OUTGOING }
            .toList()

    private fun getReceivedMessages() = messages.stream()
            .filter { message -> message.direction == MessageDirection.INCOMING }
            .toList()

    private fun getSentTextMessages() = messages.stream()
            .filter { message -> message.direction == MessageDirection.OUTGOING }
            .filter { message -> message.type == MessageType.TEXT }
            .toList()

    private fun getReceivedTextMessages() = messages.stream()
            .filter { message -> message.direction == MessageDirection.INCOMING }
            .filter { message -> message.type == MessageType.TEXT }
            .toList()

    override fun toString(): String {
        return "Messages from ${targetUser?.nickName} / ${targetUser?.firstName} ${targetUser?.lastName} have these stats: \n" +
                "Sent: ${userStatistic.messagesSent} with an average of ${userStatistic.averageMessageLengthSent} characters \n" +
                "Received: ${userStatistic.messagesReceived} with an average of ${userStatistic.averageMessageLengthReceived} characters \n" +
                "Sent ${userStatistic.imagesSent} images and received ${userStatistic.imagesReceived} images \n" +
                "The most used words sent were ${userStatistic.mostUsedWordsSent} \n" +
                "The most used words received were ${userStatistic.mostUsedWordsReceived} \n" +
                "Sent most messages on ${userStatistic.messagesPerDaySent} \n" +
                "Received most messages on ${userStatistic.messagesPerDayReceived} \n" +
                "Sent messages on these weekdays: ${userStatistic.messagesPerDayOfWeekSent} \n" +
                "Received messages on these weekdays: ${userStatistic.messagesPerDayOfWeekReceived} \n" +
                "Sent messages on these times: ${userStatistic.messagesPerHourOfDaySent} \n" +
                "Received messages on these times: ${userStatistic.messagesPerHourOfDayReceived} \n" +
                "Most sent emojis: ${userStatistic.mostUsedEmojisSent} \n" +
                "Most received emojis: ${userStatistic.mostUsedEmojisReceived}"
    }
}