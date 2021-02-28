package co.lacoro.monitor

import android.content.ContentResolver
import android.provider.Telephony
import java.lang.Long
import java.util.*

const val REQUEST_CODE_ASK_PERMISSIONS = 555
class SMSDataReader(
    private val contentResolver: ContentResolver
) {


    fun readAll():List<SMS> {
        val cursor = contentResolver.query(Telephony.Sms.CONTENT_URI, null, null, null, null)
        val listOfMessages = arrayListOf<SMS>()
        if (cursor != null) {
           val totalSMS = cursor.count
            if (cursor.moveToFirst()) {
                for (j in 0 until totalSMS) {
                    val smsDate: String = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.DATE))
                    val number: String = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.ADDRESS))
                    val body: String = cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.BODY))
                    val dateFormat = Date(Long.valueOf(smsDate))
                    val type = when (cursor.getString(cursor.getColumnIndexOrThrow(Telephony.Sms.TYPE)).toInt()) {
                        Telephony.Sms.MESSAGE_TYPE_INBOX -> SMSType.INBOX
                        Telephony.Sms.MESSAGE_TYPE_SENT -> SMSType.SENT
                        Telephony.Sms.MESSAGE_TYPE_OUTBOX -> SMSType.OUTBOX
                        else -> SMSType.UNKNOWN
                    }

                    println("Date:$smsDate Number:$number Body: $body DateFormat: $dateFormat Type: $type")
                    listOfMessages.add(SMS(date = dateFormat, number= number, body = body, type = type))

                    cursor.moveToNext()
                }
            }
            cursor.close()
        }

        return listOfMessages
    }
}