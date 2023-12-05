package com.assignment.myapplicationtrial.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.adapter.People
import com.assignment.myapplicationtrial.adapter.PeopleAdapter
import com.assignment.myapplicationtrial.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val peopleName = resources.getStringArray(R.array.people)
        val peopleNim = resources.getStringArray(R.array.nim)
        val people = ArrayList<People>()
        for (i in peopleName.indices) {
            val person = People(peopleName[i], peopleNim[i])
            people.add(person)
        }
        binding.rvPeopleContrib.layoutManager = LinearLayoutManager(this)
        binding.rvPeopleContrib.adapter = PeopleAdapter(people)
    }
}