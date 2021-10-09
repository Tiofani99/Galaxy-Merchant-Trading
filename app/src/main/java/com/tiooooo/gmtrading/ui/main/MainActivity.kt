package com.tiooooo.gmtrading.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.tiooooo.gmtrading.databinding.ActivityMainBinding
import com.tiooooo.gmtrading.util.Helper.getGreetingMessage
import com.tiooooo.gmtrading.util.Helper.getPlainText


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private var exitTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnSubmit.setOnClickListener {
                tvAnswer.isVisible = validate()
                if (validate()) {
                    viewModel.manageInput(edtQuery.getPlainText())
                    inputQuery.error = null
                } else inputQuery.error = "Required"
            }

            tvGreeting.text = getGreetingMessage()

            viewModel.resultText.observe(this@MainActivity, {
                tvAnswer.text = it
            })
        }

    }

    private fun validate(): Boolean {
        return binding.edtQuery.getPlainText().isNotEmpty()
    }

    override fun onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "Press once again to exit", Toast.LENGTH_SHORT).show()
            exitTime = System.currentTimeMillis()
        } else {
            finish()
        }

    }

}