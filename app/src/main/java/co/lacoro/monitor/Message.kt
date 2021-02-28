package co.lacoro.monitor

import java.util.*

data class SMS(
    val date: Date,
    val number: String,
    val body: String,
    val type: SMSType
)
enum class SMSType(val color: String) {
    INBOX("inbox"),
    SENT("sent"),
    OUTBOX("outbox"),
    UNKNOWN("unknown")

}