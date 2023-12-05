package com.assignment.myapplicationtrial.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.myapplicationtrial.databinding.ItemPersonBinding

class PeopleAdapter(private val people: ArrayList<People>): RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    class ViewHolder(itemBinding: ItemPersonBinding): RecyclerView.ViewHolder(itemBinding.root) {
        private val tvName = itemBinding.tvName
        private val tvNim = itemBinding.tvNim

        fun bind(person: People) {
            tvName.text = person.name
            tvNim.text = person.nim
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemPersonBinding =
            ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = people.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentPerson = people[position]
        holder.bind(currentPerson)
    }

}