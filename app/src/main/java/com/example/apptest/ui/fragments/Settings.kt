package com.example.apptest.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.apptest.MainActivity
import com.example.apptest.R
import com.example.apptest.activites.RegisterActivity
import com.example.apptest.utilits.AUTH
import com.example.apptest.utilits.replaceActivity
import com.example.apptest.utilits.replaceFragment

class Settings : Base(R.layout.fragment_settings) {

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.s_btn_exit -> {
                AUTH.signOut()
                (activity as MainActivity).replaceActivity(RegisterActivity())
            }
            R.id.s_menu_change_name -> replaceFragment(ChangeName())
        }
        return true
    }
}