package com.aliraza.expense

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.expense_item.view.*

class ExpensAdapater(var expenseList: List<Expense>)  :
    RecyclerView.Adapter<ExpensAdapater.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.vExpense.text = expenseList[position].amount
        holder.itemView.vDate.text = expenseList[position].monthYear
//        val monthYear: String,
//        val category: String,
//        val amount: String,
//        val description: String,
//        val date: String
        holder.itemView.setOnClickListener {
            val int = Intent(holder.itemView.context,DetailActivity::class.java)
            int.putExtra("monthYear",expenseList[position].monthYear)
            int.putExtra("category",expenseList[position].category)
            int.putExtra("amount",expenseList[position].amount)
            int.putExtra("description",expenseList[position].description)
            int.putExtra("date",expenseList[position].date)
            holder.itemView.context.startActivity(int)

        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}