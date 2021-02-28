package co.lacoro.monitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SMSAdapter (private val smsList: List<SMS>) : RecyclerView.Adapter<SMSAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val bodyMsgTextView = itemView.findViewById<TextView>(R.id.body_msg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SMSAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.transaction_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: SMSAdapter.ViewHolder, position: Int) {
        val transaction = smsList[position]
        holder.bodyMsgTextView.text = transaction.body

    }

    override fun getItemCount(): Int = smsList.size


}