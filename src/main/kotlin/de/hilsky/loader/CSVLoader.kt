package de.hilsky.loader

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import de.hilsky.domain.ChatMessage
import de.hilsky.domain.MessageDirection
import de.hilsky.domain.MessageType
import de.hilsky.domain.ThreemaUser
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import kotlin.streams.toList

class CSVLoader {
    private val backupRootPath =  "/threema-backup_20200224"
    val rawContacts: List<List<String>>
    var contacts: List<ThreemaUser>

    init {
        rawContacts = csvReader().readAll(javaClass.getResource("$backupRootPath/contacts.csv").readText())
        contacts = rawContacts
                .drop(1).stream() // remove header
                .map { contact -> ThreemaUser(contact[0], contact[5], contact[6], contact[7]) }
                .toList()
    }

    fun printContacts() {
        for (threemaUser in contacts) {
            println("Contact ${threemaUser.id} is actually ${threemaUser.firstName} ${threemaUser.lastName} - nickname: ${threemaUser.nickName}")
        }
    }

    fun loadMessagesForUser(user: ThreemaUser?): List<ChatMessage> {
        if (user == null) {
            println("No data found for empty user")
            return emptyList()
        }

        val rawMessages = csvReader().readAll(javaClass.getResource("$backupRootPath/message_${user.id}.csv").readText())

        return rawMessages
                .drop(1).stream() // drop header
                .map { rawMessage -> ChatMessage(
                        rawMessage[10],
                        LocalDateTime.ofInstant(Instant.ofEpochMilli(rawMessage[7].toLong()), ZoneId.systemDefault()),
                        MessageType.fromString(rawMessage[9]),
                        if (rawMessage[2] == "1") MessageDirection.OUTGOING else MessageDirection.INCOMING
                    ) }
                .toList()
    }
}