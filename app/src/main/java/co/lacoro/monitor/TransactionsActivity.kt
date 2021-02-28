package co.lacoro.monitor

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TransactionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transactions_activity)

        val smsDataReader = SMSDataReader(contentResolver)
        val recyclerView = findViewById<RecyclerView>(R.id.transactions_list)
        val noDataYetTextVew = findViewById<TextView>(R.id.transaction_data)

        findViewById<Button>(R.id.show_sms_data).setOnClickListener {
            val hasReadSmsPermission: Int = PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_SMS)
            if (hasReadSmsPermission == PackageManager.PERMISSION_GRANTED) {
                val smsList = smsDataReader.readAll()
                val adapter = SMSAdapter(smsList)
                recyclerView.adapter = adapter
                recyclerView.layoutManager = LinearLayoutManager(this)
                noDataYetTextVew.isVisible = false
            }else{
                checkPermissions()
            }
        }

    }

    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val hasReadSmsPermission: Int = PermissionChecker.checkSelfPermission(this, Manifest.permission.READ_SMS)
            if (hasReadSmsPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_SMS), REQUEST_CODE_ASK_PERMISSIONS)
            }
        }
    }
}