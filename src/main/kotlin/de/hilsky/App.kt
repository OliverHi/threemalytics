package de.hilsky

import de.hilsky.loader.CSVLoader
import de.hilsky.statistics.StatCalculator

class App

fun main(args: Array<String>) {
    val loader = CSVLoader()
    val contacts = loader.contacts

    for (contact in contacts) {
        val messagesFromUser = loader.loadMessagesForUser(contact)
        val statCalculator = StatCalculator(messagesFromUser, contact)
        println(statCalculator)
        println("\n+-+-+-+-+-+-+-+-+-+\n")
    }
}
