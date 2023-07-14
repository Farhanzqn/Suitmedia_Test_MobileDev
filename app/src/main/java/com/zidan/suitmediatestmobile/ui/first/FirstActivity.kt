package com.zidan.suitmediatestmobile.ui.first

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
import com.zidan.suitmediatestmobile.R
import com.zidan.suitmediatestmobile.ViewModelFactory
import com.zidan.suitmediatestmobile.databinding.ActivityFirstBinding
import com.zidan.suitmediatestmobile.model.UserPreference
import com.zidan.suitmediatestmobile.ui.main.MainViewModel
import com.zidan.suitmediatestmobile.ui.second.SecondActivity


private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class FirstActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstBinding
    private lateinit var viewModel: FirstViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        initViewModel()
        initAction()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore), this)
        )[FirstViewModel::class.java]
    }

    private fun initAction() {
        binding.nextButton.setOnClickListener {
            val user = binding.inputName.text.toString()
            if (user.isBlank()) {
                binding.inputName.error = getString(R.string.insert_your_name)
            } else {
                viewModel.getUser(user)
                navigateToWelcomeActivity()
            }
        }

        binding.checkButton.setOnClickListener {
            val sentence = binding.inputPalindrome.text.toString()
            val palindrome = viewModel.palindrome(sentence)
            displayPalindromeMessage(palindrome)
        }
    }

    private fun navigateToWelcomeActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun displayPalindromeMessage(isPalindrome: Boolean) {
        val message = if (isPalindrome) {
            getString(R.string.is_palindrome)
        } else {
            getString(R.string.not_palindrome)
        }
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
}