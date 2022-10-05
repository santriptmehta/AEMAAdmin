package com.blankspace.aemaadmin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.blankspace.aemaadmin.R
import com.blankspace.aemaadmin.issue_description
import com.blankspace.aemaadmin.model.Electrical

class electrical_adapter(private val userList:ArrayList<Electrical>, private val context: Context): RecyclerView.Adapter<electrical_adapter.ElectricalMyViewHolder>(){

    class ElectricalMyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val author_name : TextView = itemView.findViewById(R.id.post_author_name)
        val author_defect : TextView = itemView.findViewById(R.id.defect_post_text)
        val author_hoste_no : TextView = itemView.findViewById(R.id.author_hostel_no)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectricalMyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view_recycler,parent,false)
        return ElectricalMyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ElectricalMyViewHolder, position: Int) {
        holder.author_name.text = "by ${userList[position].name}"
        holder.author_defect.text = userList[position].defect
        holder.author_hoste_no.text = "Hostel no. ${userList[position].hostel_no}"

        holder?.itemView?.setOnClickListener {
            val intent = Intent(context, issue_description::class.java)
            intent.putExtra("author_email",userList[position].email)
            intent.putExtra("author_name",userList[position].name)
            intent.putExtra("author_hostel_no",userList[position].hostel_no)
            intent.putExtra("description",userList[position].description)
            intent.putExtra("author_roll",userList[position].roll_no)
            intent.putExtra("author_issue_title",userList[position].defect)

            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }
}