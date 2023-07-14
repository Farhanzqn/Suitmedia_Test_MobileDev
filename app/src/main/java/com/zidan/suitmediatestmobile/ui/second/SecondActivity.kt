package com.zidan.suitmediatestmobile.ui.second

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.zidan.suitmediatestmobile.R
import com.zidan.suitmediatestmobile.ViewModelFactory
import com.zidan.suitmediatestmobile.databinding.ActivitySecondBinding
import com.zidan.suitmediatestmobile.model.UserPreference
import com.zidan.suitmediatestmobile.ui.main.MainActivity


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private lateinit var viewModel: SecondViewModel

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val toolbar: androidx.appcompat.widget.Toolbar = binding. toolbar
        toolbar.setNavigationIcon(R.drawable.ic_back)
        toolbar. setNavigationOnClickListener {
            onBackPressed()
        }

        initializeViewModel()
        initializeActions()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore), this)
        )[SecondViewModel::class.java]
    }

    private fun initializeActions() {
        viewModel. getGreeting(). observe(this) { data ->
            binding.tvUser.text = data.user
            binding.tvSelectedUser.text = data.selectedUser
        }

        binding.chooseButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}