package com.assignment.myapplicationtrial.manager

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat.startActivity
import com.assignment.myapplicationtrial.R
import com.assignment.myapplicationtrial.activity.AboutActivity

class ToolbarManager(private val activity: AppCompatActivity) {
    private val toolbar: Toolbar by lazy {
        activity.findViewById(R.id.toolbar)
    }

    init {
        activity.setSupportActionBar(toolbar)
        toolbar.setOnMenuItemClickListener {
            handleMenuItemClick(it)
            true
        }
    }

    private fun handleMenuItemClick(item: MenuItem) {
        when (item.itemId) {
            R.id.about -> {
                activity.startActivity(Intent(activity, AboutActivity::class.java))
            }
        }
    }
    fun onCreateOptionsMenu(menu: Menu) {
        activity.menuInflater.inflate(R.menu.menu_options, menu)
    }
}