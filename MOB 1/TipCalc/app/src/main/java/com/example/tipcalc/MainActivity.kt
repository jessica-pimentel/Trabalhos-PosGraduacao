package com.example.tipcalc

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    var currentBillTotal: Double = 0.0
    var currentCustomPercent: Int = 18
    var divideCurrentCustomPerson: Double = 0.0
    var divideCurrentoTotalPerson: Int = 1

    lateinit var tipTenEditText: EditText
    lateinit var tipFifteenEditText: EditText
    lateinit var tipTwentyEditText: EditText

    lateinit var totalTenEditText: EditText
    lateinit var totalFifteenEditText: EditText
    lateinit var totalTwentyEditText: EditText

    lateinit var billEditText: EditText
    lateinit var tipCustomEditText: EditText
    lateinit var totalCustomEditText: TextView
    lateinit var totalDividePerson: TextView
    lateinit var customTipTextview: TextView
    lateinit var dividePersonInput: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tableLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tipTenEditText = findViewById(R.id.tipTenEditText)
        tipFifteenEditText = findViewById(R.id.tipFifteenEditText)
        tipTwentyEditText = findViewById(R.id.tipTwentyEditText)

        totalTenEditText = findViewById(R.id.totalTenEditText)
        totalFifteenEditText = findViewById(R.id.totalFifteenEditText)
        totalTwentyEditText = findViewById(R.id.totalTwentyEditText)

        billEditText = findViewById(R.id.billEditText)
        tipCustomEditText = findViewById(R.id.tipCustomEditText)
        totalCustomEditText = findViewById(R.id.totalCustomEditText)
        dividePersonInput = findViewById(R.id.dividePersonInput)
        totalDividePerson = findViewById(R.id.totalDividePerson)
        customTipTextview = findViewById(R.id.customTipTextview)

        val customSeekBar = findViewById<SeekBar>(R.id.customSeekBar)
        currentCustomPercent = customSeekBar.progress
        billEditText.addTextChangedListener(billTextWatcher)
        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener)

        val divideSeekbar = findViewById<SeekBar>(R.id.divideSeekBar)
        divideCurrentoTotalPerson = divideSeekbar.progress
        divideSeekbar.setOnSeekBarChangeListener(divideSeekBarListener)
    }

    @SuppressLint("DefaultLocale")
    fun updateStandard() {
        val tenPercentTip = currentBillTotal * 0.10
        val fifteenPercentTip = currentBillTotal * 0.15
        val twentyPercentTip = currentBillTotal * 0.20

        val tenPercentTotal = currentBillTotal + tenPercentTip
        val fifteenPercentTotal = currentBillTotal + fifteenPercentTip
        val twentyPercentTotal = currentBillTotal + twentyPercentTip

        tipTenEditText.setText(String.format("%.2f", tenPercentTip))
        tipFifteenEditText.setText(String.format("%.2f", fifteenPercentTip))
        tipTwentyEditText.setText(String.format("%.2f", twentyPercentTip))

        totalTenEditText.setText(String.format("%.2f", tenPercentTotal))
        totalFifteenEditText.setText(String.format("%.2f", fifteenPercentTotal))
        totalTwentyEditText.setText(String.format("%.2f", twentyPercentTotal))
    }

    @SuppressLint("DefaultLocale")
    fun updateCustom() {
        customTipTextview.text = "$currentCustomPercent%"
        val customTipAmount = currentBillTotal * (currentCustomPercent * .01)
        val customTotalAmount = currentBillTotal + customTipAmount

        tipCustomEditText.setText(String.format("%.2f", customTipAmount))
        totalCustomEditText.setText(String.format("%.2f", customTotalAmount))
    }

    @SuppressLint("DefaultLocale")
    fun updateDivideCustom() {
        dividePersonInput.text = "$divideCurrentoTotalPerson pessoas"
        val customTipAmount = currentBillTotal * (currentCustomPercent * .01)
        val customTotalAmount = currentBillTotal + customTipAmount
        val customTotalDivideAmount = customTotalAmount / divideCurrentoTotalPerson

        totalDividePerson.text = String.format("%.2f", customTotalDivideAmount)
    }

    private val customSeekBarListener = object: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            currentCustomPercent = progress
            updateCustom()
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }
        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }

    private val billTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            try {
                currentBillTotal = s.toString().toDouble()
            } catch (e: NumberFormatException) {
                currentBillTotal = 0.0
            }
            updateStandard()
            updateCustom()
        }
        override fun afterTextChanged(s: Editable?) {
        }
    }

    private val divideSeekBarListener = object: SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            divideCurrentoTotalPerson = progress
            updateDivideCustom()
        }
        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }
        override fun onStopTrackingTouch(seekBar: SeekBar?) {
        }
    }
}