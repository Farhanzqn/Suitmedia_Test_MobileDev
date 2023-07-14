package com.zidan.suitmediatestmobile.ui.main

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.Preference
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zidan.suitmediatestmobile.R
import com.zidan.suitmediatestmobile.ViewModelFactory
import com.zidan.suitmediatestmobile.data.adapter.ListAdapter
import com.zidan.suitmediatestmobile.data.adapter.LoadingStateAdapter
import com.zidan.suitmediatestmobile.databinding.ActivityMainBinding
import com.zidan.suitmediatestmobile.model.UserPreference


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val toolbar: androidx.appcompat.widget.Toolbar = binding. toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar. setNavigationOnClickListener {
            onBackPressed()
        }

        initializeViewModel()
        initializeAction()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore), this)
        )[MainViewModel::class.java]
    }

    private fun initializeAction() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListAdapter { selectedUser -> viewModel.saveSelectedUser(selectedUser) }
        binding.rvUser.adapter = listUserAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                listUserAdapter.retry()
            }
        )
        viewModel.users.observe(this) { pagingData ->
            listUserAdapter.submitData(lifecycle, pagingData)
        }
    }
}