package com.example.t1golnaz
import kotlin.random.Random.Default.nextInt
import java.io.Serializable
import java.util.*

class Reserve : Serializable {
    var reservationId: String
    var carType:String
    var numberofHours:Int
    var insuranceRequired:Boolean
    var subtotal : Double=0.0 // Rental Rate + Car Insurance

    var salesTax: Double=0.0 //13% of the subtotal
    var total:Double=0.0

    constructor( cType:String,hours:Int,insurance:Boolean ) {
        carType = cType
        numberofHours = hours
        insuranceRequired = insurance
        reservationId = carType.substring(0, 3).toUpperCase(Locale.ROOT) + "-" + (100..999).random().toString()

        if (hours <= 4) {
            subtotal = if(carType=="SUV")
                hours * 7.0
            else
                hours * 5.0

            if (insurance)
                subtotal += 0.5 * hours
            salesTax = 13 * subtotal / 100
            total = subtotal + salesTax
        }
        else {
            val days = hours / 24 as Int + 1
            subtotal = days * 50.0
            if (insurance)
                subtotal += 0.5 * hours
            salesTax = 13 * subtotal / 100
            total = subtotal + salesTax
        }
    }
    override fun toString():String{
        var strResult:String=""
        strResult+="Reservation Id: $reservationId \n"
        strResult+="Car type: $carType \n"
        strResult+="Number of hours: $numberofHours \n"
            if(numberofHours>4)
                strResult+="The reservation is for ${numberofHours / 24 as Int + 1} day(s) \n"
            strResult += if(insuranceRequired)
                "Insurance is required \n"
            else
                "Insurance is not required \n"
            strResult+="Subtotal : $subtotal \n"
            strResult+="Sales tax (13%): $salesTax \n"
            strResult+="Total: $total"
            return strResult
        }
}