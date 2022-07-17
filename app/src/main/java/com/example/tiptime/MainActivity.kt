package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener { calculate() }
    }

    private fun calculate() {
        val input = binding.costOfService.text.toString()
        val price = input.toDoubleOrNull()

        if (price == null || price == 0.0) {
            showResult(0.0)
            return
        }

        val percentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.2
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var cost = price * percentage
        val isRoundUp = binding.roundUpSwitch.isChecked
        if (isRoundUp)
            showResult(ceil(cost))
        else
            showResult(cost)

    }

    private fun showResult(d: Double) {
        binding.tipResult.setText("Amount to tip: $$d")
    }
}