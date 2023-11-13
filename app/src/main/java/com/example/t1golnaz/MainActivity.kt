package com.example.t1golnaz

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startOverButton: Button = findViewById(R.id.btnStartOver)

        startOverButton.setOnClickListener {
            val hoursFromUI:EditText=findViewById(R.id.etRentHours)
            hoursFromUI.setText("")

            val rentTypeFromUI : RadioGroup = findViewById(R.id.rgRentType)
            rentTypeFromUI.clearCheck()

            val carInsuranceFromUI : Switch = findViewById(R.id.swInsurance)
            carInsuranceFromUI.isChecked = false

            val errorFromUI:TextView=findViewById(R.id.tvErrorMsg)
            errorFromUI.text=""

        }

        val reserveButton: Button = findViewById(R.id.btnReserve)
        reserveButton.setOnClickListener{
            val hoursFromUI:EditText=findViewById(R.id.etRentHours)
            val rentTypeFromUI : RadioGroup = findViewById(R.id.rgRentType)
            val carInsuranceFromUI:Switch =findViewById(R.id.swInsurance)

            val errorFromUI:TextView=findViewById(R.id.tvErrorMsg)

            val selectedRadioButtonId:Int = rentTypeFromUI.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                errorFromUI.text = "please select the car type"
                return@setOnClickListener
            }

            if(hoursFromUI.text.isEmpty()) {
                errorFromUI.text = "Hours can not be empty. please insert a number. at least 1"
                return@setOnClickListener
            }

            var stringHours= hoursFromUI.text.toString()
            var hours :Int? = Integer.parseInt(stringHours)

            if( hours==null){
                errorFromUI.text = "You can reserve the car for 1 hour or more"
                return@setOnClickListener
            }
            if ( hours<1) {
                errorFromUI.text = "You can reserve the car for 1 hour or more"
                return@setOnClickListener
            }

            val selectedRadioButton: RadioButton = findViewById(selectedRadioButtonId)
            val reserveCar1:Reserve=Reserve(selectedRadioButton.text.toString(),hours,carInsuranceFromUI.isChecked)
            Log.d("Receipt"," ${reserveCar1.toString()} ")

            val intent = Intent(this, ReceiptScreen::class.java)

            intent.putExtra("EXTRA_RESERVE", reserveCar1)

            hoursFromUI.setText("")
            rentTypeFromUI.clearCheck()
            carInsuranceFromUI.isChecked = false
            errorFromUI.text=""

            startActivity(intent)

        }

    }
}