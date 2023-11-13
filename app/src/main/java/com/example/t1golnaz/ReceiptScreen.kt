package com.example.t1golnaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.Serializable

class ReceiptScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt_screen)

        val objectFromScreen1: Serializable? = intent.getSerializableExtra("EXTRA_RESERVE")

        val errorFromUI: TextView =findViewById(R.id.tvErrorMsg)
        errorFromUI.text=""

        val reserveFromUI:TextView=findViewById(R.id.tvReserveInfo)
        reserveFromUI.text=""

        if (objectFromScreen1 == null) {
            errorFromUI.text="Error:Object not Found"
        } else {
            val reservedCar:Reserve? = objectFromScreen1 as? Reserve
            if (reservedCar == null) {
                errorFromUI.text="Error: Conversion Error"
            }
            else {
                reserveFromUI.text=reservedCar.toString()
            }
        }



    }
}